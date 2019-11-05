package network.server;

import network.Message;
import protocol.client2server.JoinGame;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerImpl extends Server implements Runnable {

    private ServerSocket serverSocket;
    private HashMap<String, Socket> clients = new HashMap<>();

    /**
     * Konstruktor. Initialisiert das neue Server-Objekt mit der Referenz auf das Empf�ngerobjekt.
     *
     * @param serverApplication Das Empf�ngerobjekt des Bomberman-Servers f�r Nachrichten.
     */
    public ServerImpl(ServerApplicationInterface serverApplication) {
        super(serverApplication);
        createServerSocket(54321);

    }

    private void createServerSocket(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            for (int i = 0; i < 4; i++) {
                Thread clientConnection = new Thread(this);
                clientConnection.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Message message, String connectionId) {

    }

    @Override
    public void broadcast(Message message) {

    }

    @Override
    public void run() {
        try {
            Socket clientSocket = this.serverSocket.accept();
            JoinGame joinMessage = (JoinGame) readInputStream(clientSocket);
            this.clients.put(joinMessage.getPlayerName(), clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object readInputStream(Socket clientSocket) throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
        return inputStream.readObject();
    }

    public void writeOutputStream(Socket clientSocket) throws Exception {

    }
}
