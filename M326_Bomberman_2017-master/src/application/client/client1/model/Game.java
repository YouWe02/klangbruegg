package application.client.client1.model;

import protocol.server2client.PlayerJoined;
import together.Map;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player myPlayer;
    private List<Player> oponents = new ArrayList<Player>();
    private Tile[][] gameField;

    public Game() {

    }

    public void createMyPlayer(String playerName) {
        myPlayer = new Player(playerName);
    }

    public void playerJoined(PlayerJoined message) {
        String playerName = message.getPlayerName();
        int initialX = message.getInitialPositionX();
        int initialY = message.getInitialPositionY();
        if(myPlayer.isYourName(playerName)){
            myPlayer.setPosition(initialX, initialY);
        } else {
            Player oponent = new Player(playerName, initialX, initialY);
            oponents.add(oponent);
        }
    }

    public List<Player> getAllOponents(){
        return oponents;
    }

    public void setGameField(Tile[][] gameField) {
        this.gameField = gameField;
    }

    public Tile[][] getGameField() {
        return gameField;
    }
}
