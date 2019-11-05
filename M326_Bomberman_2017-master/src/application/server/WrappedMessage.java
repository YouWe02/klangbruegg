package application.server;

import network.Message;

/**
 * Class that includes a Message as well as the connectionId
 *
 * @author Tobias Maier <tma109970@iet-gibb.ch>
 */
public class WrappedMessage {
    private Message message;
    private String connectionId;

    public WrappedMessage(Message message, String connectionId) {
        this.message = message;
        this.connectionId = connectionId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
}
