package application.client.client1.control;

import application.client.client1.model.Game;
import application.client.client1.model.Tile;
import application.client.client1.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.server2client.StartGame;

public class StartGameControl extends Control {
    public StartGameControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void startGame(StartGame message){
        Tile[][] gameField = (Tile[][]) message.getMap();
    }
}
