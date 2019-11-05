package application.server.factories;

import network.Message;
import protocol.Direction;
import protocol.server2client.*;
import together.Bomb;
import together.Map;
import together.Player;

/**
 * @author Tobias Maier <tma109970@iet-gibb.ch>
 */
public class MessageFactory {

    private static MessageFactory instance;

    private MessageFactory() {
    }

    public static MessageFactory getInstance() {
        if (instance == null) {
            synchronized (MessageFactory.class) {
                if (instance == null) {
                    instance = new MessageFactory();
                }
            }
        }
        return instance;
    }


    public Message makeBombDropped(Bomb bomb) {
        return new BombDropped(bomb.getId(), bomb.getX(), bomb.getY());
    }

    public Message makeBombExplosion(Bomb bomb) {
        return new BombExploded(bomb.getId());
    }

    public Message makeErrorMessage(String error) {
        return new ErrorMessage(error);
    }

    public Message makeGameOver(Player winner, String[] scoreboard) {
        return new GameOver(winner.getName(), scoreboard);
    }

    public Message makePlayerHit(Player player) {
        return new PlayerHit(player.getName());
    }

    public Message makePlayerJoined(Player player) {
        return new PlayerJoined(player.getName(), player.getX(), player.getY());
    }

    public Message makePlayerMoved(Player player, Direction direction) {
        return new PlayerMoved(player.getName(), direction);
    }

    public Message makeStartGame(Map map) {
        return new StartGame(map);
    }

    public Message makeUpdate(Map map) {
        return new Update(map);
    }


}
