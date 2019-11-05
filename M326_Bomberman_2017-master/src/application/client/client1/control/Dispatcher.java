package application.client.client1.control;

import network.Message;
import network.client.ClientApplicationInterface;
import protocol.server2client.ErrorMessage;
import protocol.server2client.PlayerJoined;
import protocol.server2client.StartGame;

public class Dispatcher implements ClientApplicationInterface {
    @Override
    public void handleMessage(Message message) {
        if(message instanceof PlayerJoined){
            PlayerJoinedControl control = ControlFactory.instance().createPlayerJoinedControl();
            control.playerJoined((PlayerJoined)message);
        } else if(message instanceof ErrorMessage){

        } else if(message instanceof StartGame){
            StartGameControl control = ControlFactory.instance().createStartGameControl();
            control.startGame((StartGame)message);
        }
    }
}
