package application.server;

import application.server.factories.MessageFactory;
import application.server.util.Logger;
import network.Message;
import network.server.Server;
import together.*;
import together.util.PositionWrapper;
import java.util.UUID;
import java.util.Vector;

/**
 *
 */
public class Game {
    // TODO: javadoc

    private Logger logger;
    private MessageFactory messageFactory;
    private network.server.Server network;
    private Vector<Player> players = new Vector<>();
    private Vector<Bomb> bombs = new Vector<>();
    private Map map;
    private String[] scoreBoard = new String[4];
    private boolean isStarted = false;
    private boolean isOver = false;

    Game(Server network) {
        this.network = network;
        this.logger = Logger.getInstance();
        this.messageFactory = MessageFactory.getInstance();
        // TODO: Get Map from a file
        this.map = new Map(10);
    }

    /**
     * Checks if there are less than 4 players and, if so it adds the player to the game and returns a PlayerJoined message,
     * if the game is already full or the playerName already exists, a error message is returned
     *
     * @param playerName   name of the player trying to log in
     * @param connectionId Socket-connection id
     */
    void addPlayer(String playerName, String connectionId) {
        Message message;
        if (players.size() >= 4) {
            message = messageFactory.makeErrorMessage("Game is full");
            this.network.send(message, connectionId);
        } else if (playerNameAlreadyTaken(playerName)) {
            message = messageFactory.makeErrorMessage("This playername is already taken");
            this.network.send(message, connectionId);
        } else {
            PositionWrapper startPosition = map.getStartPosition();
            int x = startPosition.getX();
            int y = startPosition.getY();
            Player player = new Player(connectionId, playerName, x, y);
            players.add(player);
            message = messageFactory.makePlayerJoined(player);
            this.network.broadcast(message);
        }
        if (players.size() >= 4 && !isStarted) {
            startGame();
        }
    }

    /**
     * @return sum of all players alive
     */
    private int getPlayersAlive() {
        int playersAlive = 0;
        for (Player player : players) {
            if (!player.isDead()) playersAlive++;
        }
        return playersAlive;
    }

    /**
     * Checks if the given playerName is already taken
     *
     * @param playerName PlayerName of the client that is trying to join
     * @return boolean returns if the playerName is already taken
     */
    private boolean playerNameAlreadyTaken(String playerName) {
        for (Player player : players) {
            if (player != null && playerName.equals(player.getName())) {
                return true;
            }
        }
        return false;
    }

    //TODO: Start game logic + method

    /**
     * Broadcasts a StartGameMessage, sends it to the players, then updates the state of the game instance
     */
    private void startGame() {
        Message startGameMessage = messageFactory.makeStartGame(map);
        logger.debug("STARTING GAME");
        this.network.broadcast(startGameMessage);
        this.isStarted = true;
    }

    /**
     * Sets a bomb at the given position if it's set at valid coordinates.
     * After the bomb has been set and a BombDropped message has been returned,
     * the bomb runs in a separate thread, where the bomb exploded chain is handled
     *
     * @param x X-Coordinate of the new bomb
     * @param y Y-Coordinate of the new bomb
     */
    void dropBomb(int x, int y) {
        // TODO: Check if bomb is set at valid coordinate
//        if (!map.isWall(x, y) && x >= 0 && x < map.xSize() && y >= 0 && y < map.ySize()) {
        if (true) {
            for (Bomb currBomb : bombs) {
                if (currBomb.getX() == x && currBomb.getX() == y) {
                    logger.debug("There is already a bomb at this position");
                    return;
                }
            }
            String uuid = UUID.randomUUID().toString();
            Bomb bomb = new Bomb(uuid, x, y);
            Message bombDroppedMessage = messageFactory.makeBombDropped(bomb);
            bombs.add(bomb);
            this.network.broadcast(bombDroppedMessage);
            // Start new thread which explodes the bomb after 2000ms, it then checks if a living player was hit and sends message if so
            new Thread(() -> {
                Message bombExplodedMessage = messageFactory.makeBombExplosion(bomb);
                try {
                    Thread.sleep(Bomb.ExplosionTime);
                    this.network.broadcast(bombExplodedMessage);
                    for (Player player : players) {
                        // Check if a player has been hit by the bomb if he is not already dead
                        if (bomb.inRange(player) && !player.isDead()) {
                            scoreBoard[getPlayersAlive() - 1] = String.format("%s. %s: %s points", getPlayersAlive(), player.getName(), 4 - getPlayersAlive());
                            player.setDead(true);
                            Message playerHitMessage = messageFactory.makePlayerHit(player);
                            this.network.broadcast(playerHitMessage);
                        }
                    }
                    bombs.remove(bomb);
                    // TODO: test if thread safety is achieved (in case not implement WrappedBomb class in which the thread runs and synchronize that class)
                    synchronized (this) {
                        if (getPlayersAlive() <= 1 && !isOver) {
                            gameOver();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * Change instance variables to fit GameOver state
     */
    private void setGameOver() {
        this.isStarted = false;
        this.isOver = true;
    }

    /**
     * Getter for isOver variable
     *
     * @return gameIsOver
     */
    public boolean isOver() {
        return this.isOver;
    }

    // TODO: GameOver

    /**
     * Method to handle GameOver event.
     * Sets game as over, then sets last slot of the Scoreboard list, then creates GameOver message and broadcasts it.
     */
    private void gameOver() {
        this.setGameOver();
        Message gameOverMessage;
        for (Player player : players) {
            if (!player.isDead()) {
                scoreBoard[0] = String.format("1. %s: %s points", player.getName(), 3);
                gameOverMessage = messageFactory.makeGameOver(player, scoreBoard);
                this.network.broadcast(gameOverMessage);
                // TODO: Write winner to HighScore Database
                return;
            }
        }
    }
}
