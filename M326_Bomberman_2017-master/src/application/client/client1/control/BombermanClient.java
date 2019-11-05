package application.client.client1.control;

import application.client.client1.model.Game;
import application.client.client1.view.BombermanPanel;
import application.client.client1.view.BombermanFrame;
import network.client.ServerProxy;
import network.client.ServerProxyStub;

public class BombermanClient {

	public static void main(String[] args) {
		new BombermanClient();
	}
	
	private BombermanClient() {
		Dispatcher dispatcher = new Dispatcher();
		ServerProxy serverProxy = new ServerProxyStub(dispatcher);
        Game game = new Game();
		BombermanPanel panel = new BombermanPanel();
		new BombermanFrame(panel);
		ControlFactory.instantiate(serverProxy, game, panel);
	}

}
