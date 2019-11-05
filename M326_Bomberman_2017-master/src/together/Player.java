package together;

import protocol.Direction;

public class Player {
    private String connectionId;
    private String name;
    private int x;
    private int y;
    private boolean isDead;
    private int points = 0;

    /**
     * Constructor for client
     *
     * @param name
     * @param x
     * @param y
     */
    public Player(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.isDead = false;
    }

    /**
     * Constructor for server
     *
     * @param connectionId
     * @param name
     * @param x
     * @param y
     */
    public Player(String connectionId, String name, int x, int y) {
        this.connectionId = connectionId;
        this.name = name;
        this.x = x;
        this.y = y;
        this.isDead = false;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public String getName() {
        return name;
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                x = x - 1;
                break;
            case RIGHT:
                x = x + 1;
                break;
            case UP:
                y = y - 1;
                break;
            case DOWN:
                y = y + 1;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}