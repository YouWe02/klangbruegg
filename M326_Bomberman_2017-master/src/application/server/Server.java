package application.server;

import application.server.util.Logger;
import together.util.queue.Queue;
import network.Message;
import protocol.client2server.DropBomb;
import protocol.client2server.JoinGame;
import protocol.server2client.*;

/**
 * Basic Server (GameServer) class
 * <p>
 * Upon creating instantiating this class, the server gets started and is running in a separate thread.
 * In that thread, the server is constantly checking if there is a message in the queue.
 * If there is a message in the queue the server fast forwards it to a MessageHandler instance
 *
 * @author Tobias Maier <tma109970@iet-gibb.ch>
 */
public class Server implements network.server.ServerApplicationInterface, Runnable {
    private network.server.Server network;
    private MessageHandler messageHandler;
    private Queue<WrappedMessage> queue = new Queue<>();
    private Game game;
    private Logger logger;

    /**
     * Main method: for testing purpose's only
     *
     * @param args Application Arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //  Initializieren des Servers
        Server server = new Server();
        // TEST DATEN
        server.queue.push(new WrappedMessage(new JoinGame("Timon"), "Timon_conn"));
        Thread.sleep(2000);
        server.queue.push(new WrappedMessage(new JoinGame("Fabiyan"), "Fabiyan_conn"));
        Thread.sleep(500);
        server.queue.push(new WrappedMessage(new JoinGame("Tobias"), "Tobias_conn"));
        Thread.sleep(500);
        server.queue.push(new WrappedMessage(new JoinGame("Tobias"), "Tobias_conn"));
        Thread.sleep(500);
        server.queue.push(new WrappedMessage(new JoinGame("Petar"), "Petar_conn"));
        Thread.sleep(500);
        server.queue.push(new WrappedMessage(new JoinGame("Ruedi"), "Ruedi_conn"));
        Thread.sleep(1000);
        server.queue.push(new WrappedMessage(new DropBomb("Fabiyan", 2, 2), "Fabiyan_conn"));
        server.queue.push(new WrappedMessage(new DropBomb("Fabiyan", 2, 2), "Fabiyan_conn"));
        server.queue.push(new WrappedMessage(new DropBomb("Fabiyan", 5, 7), "Fabiyan_conn"));
        Thread.sleep(1000);
        server.queue.push(new WrappedMessage(new DropBomb("Fabiyan", 3, 7), "Fabiyan_conn"));
        server.queue.push(new WrappedMessage(new DropBomb("Fabiyan", 9, 9), "Fabiyan_conn"));
        Thread.sleep(3000);
        server.queue.push(new WrappedMessage(new DropBomb("Fabiyan", 0, 0), "Fabiyan_conn"));
        server.queue.push(new WrappedMessage(new JoinGame("Ruedi"), "Ruedi_conn"));
    }

    /**
     * Fills all needed properties with newly created objects.
     * Then it creates a thread and starts it
     */
    public Server() {
        this.logger = Logger.getInstance();
        // TODO: Replace test network implementation with actual network class
        this.network = makeTestNetwork();
        this.game = new Game(this.network);
        this.messageHandler = new MessageHandler(this.game);
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Method to run the queue checking
     */
    public void run() {
        while (true) {
            // princesses the queue if it's not empty
            if (this.queue.size() > 0) {
                messageHandler.handleMessage(this.queue.poll());
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Fast forward message to messageHandler class instance
     *
     * @param message      Das empfangene Nachrichtenobjekt.
     * @param connectionId Die Netzwerkverbindung, �ber welche die Nachricht empfangen wurde
     */
    @Override
    public void handleMessage(Message message, String connectionId) {
        WrappedMessage wrappedMessage = new WrappedMessage(message, connectionId);
        this.queue.push(wrappedMessage);
    }

    /**
     * Returns a network implementation for testing that writes the sent messages to the console
     *
     * @return network testing implementation
     */
    private network.server.Server makeTestNetwork() {
        return new network.server.Server(this) {
            @Override
            public void send(Message message, String connectionId) {
                StringBuilder builder = new StringBuilder();
                builder.append("Message send | ");
                builder.append(message.getClass().getName()).append(" | ");
                builder.append("Connection id ").append(connectionId).append(" | ");
                if (message instanceof ErrorMessage) {
                    builder.append("error: ").append(((ErrorMessage) message).getErrorMessage());
                } else if (message instanceof PlayerJoined) {
                    builder.append("Joined: ").append(((PlayerJoined) message).getPlayerName());
                }
                logger.info(builder.toString());
            }

            @Override
            public void broadcast(Message message) {
                StringBuilder builder = new StringBuilder();
                builder.append("Message broadcast | ");
                builder.append(message.getClass().getName()).append(" | ");
                if (message instanceof BombDropped) {
                    builder.append("Bomb: ").append(((BombDropped) message).getId());
                    builder.append("| X: ").append(((BombDropped) message).getPositionX());
                    builder.append("| Y: ").append(((BombDropped) message).getPositionY());
                } else if (message instanceof BombExploded) {
                    builder.append("Bomb: ").append(((BombExploded) message).getId());
                    builder.append(" exploded");
                } else if (message instanceof PlayerHit) {
                    builder.append("PlayerHit: ").append(((PlayerHit) message).getPlayerName());
                } else if (message instanceof PlayerJoined) {
                    builder.append(String.format("PlayerJoined %s, X %s, Y %s", ((PlayerJoined) message).getPlayerName(), ((PlayerJoined) message).getInitialPositionX(), ((PlayerJoined) message).getInitialPositionY()));
                } else if (message instanceof PlayerMoved) {
                    builder.append(String.format("PlayerMoved %s, Direction: %s", ((PlayerMoved) message).getPlayerName(), ((PlayerMoved) message).getDirection()));
                } else if (message instanceof ErrorMessage) {
                    builder.append(String.format("Error %s", ((ErrorMessage) message).getErrorMessage()));
                } else if (message instanceof GameOver) {
                    builder.append("Game Over! ");
                    builder.append(String.format("Winner is: %s ", ((GameOver) message).getWinnerName()));
                    for (int i = 0; i < ((GameOver) message).getHighscoreList().length; i++) {
                        builder.append(String.format("%s, ", ((GameOver) message).getHighscoreList()[i]));
                    }
                } else if (message instanceof StartGame) {
                    builder.append("Game started!");
                }
                logger.info(builder.toString());
            }
        };
    }
}