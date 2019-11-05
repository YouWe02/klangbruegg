package application.client.client1.control;

import application.client.client1.model.Game;
import application.client.client1.view.BombermanPanel;
import network.client.ServerProxy;

public class Control {
    ServerProxy serverProxy;
    Game game;
    BombermanPanel view;

    public Control(ServerProxy serverProxy, Game game, BombermanPanel view){
        this.serverProxy = serverProxy;
        this.game = game;
        this.view = view;
    }
}
