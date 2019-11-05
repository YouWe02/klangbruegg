package application.server;


import application.server.util.Logger;
import network.Message;
import protocol.client2server.ClientMessage;
import protocol.client2server.DropBomb;
import protocol.client2server.JoinGame;
import protocol.client2server.MovePlayer;

/**
 * Class to handle incoming messages for a game.
 */
class MessageHandler {
    private Game game;
    private Logger logger;

    /**
     * Sets class variables
     * @param game for which the messages will be handled
     */
    MessageHandler(Game game) {
        this.logger = Logger.getInstance();
        this.game = game;
    }

    /**
     * Takes a wrapped message and handles it
     *
     * @param wrappedMessage holds a message and a connectionId
     */
    void handleMessage(WrappedMessage wrappedMessage) {
        Message message = wrappedMessage.getMessage();
        String connectionId = wrappedMessage.getConnectionId();
        if (!game.isOver()) {
            if (message instanceof ClientMessage) {
                if (message instanceof DropBomb) {
                    this.game.dropBomb(((DropBomb) message).getPositionX(), ((DropBomb) message).getPositionY());
                } else if (message instanceof MovePlayer) {
                    logger.debug("Received MovePlayer message");
                } else if (message instanceof JoinGame) {
                    this.game.addPlayer(((JoinGame) message).getPlayerName(), connectionId);
                }
            } else {
                logger.error("Received unhandled message");
            }
        } else {
            logger.debug("Message received even though the game is over");
        }
    }
}
