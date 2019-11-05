package network.client;

import network.Message;
import protocol.client2server.JoinGame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerProxyImpl extends ServerProxy {

    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * Konstruktor. Initialisiert das neue ServerProxy-Objekt mit der Referenz auf das Empfängerobjekt.
     *
     * @param clientApplication Das Empfängerobjekt des Bomberman-Clients für Nachrichten.
     */
    public ServerProxyImpl(ClientApplicationInterface clientApplication) {
        super(clientApplication);
        connectToServer("127.0.0.1", 54321, "Player 1");
    }

    private void connectToServer(String serverIP, int serverPort, String playerName) {
        try {
            System.out.println("Connecting with Server");
            this.clientSocket = new Socket(serverIP, serverPort);
            this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            this.inputStream = new ObjectInputStream(clientSocket.getInputStream());

            this.outputStream.writeObject(new JoinGame(playerName));
            this.inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void send(Message message) {

    }
}
