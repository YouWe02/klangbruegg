package application.client.client1.model;

public class Player extends together.Player {
    private String name;
    private int x;
    private int y;

    public Player(String playerName) {
        super(playerName, 0, 0);
    }

    public Player(String playerName, int initialX, int initialY){
        this(playerName);
        x = initialX;
        y = initialY;

    }

    public boolean isYourName(String playerName){
        return name.equals(playerName);
    }

    public void setPosition(int initialX, int initialY){
        x = initialX;
        y = initialY;
    }
}
