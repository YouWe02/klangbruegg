package together;

public class Bomb {

    public final static int ExplosionTime = 2000;
    private String id;
    private int x;
    private int y;

    public Bomb(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Check if a player is in the radius of explosion
     *
     * @param player
     * @return if hit by bomb
     */
    public boolean inRange(Player player) {
        return player.getX() == x && player.getY() == y ||
                player.getX() == x - 1 && player.getY() == y ||
                player.getX() == x + 1 && player.getY() == y ||
                player.getX() == x && player.getY() == y - 1 ||
                player.getX() == x && player.getY() == y + 1;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}