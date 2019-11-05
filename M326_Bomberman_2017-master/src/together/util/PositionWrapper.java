package together.util;

/**
 * Class that holds and X and Y integer which together represent a coordinate on the map.
 */
public class PositionWrapper {
    private int x;
    private int y;

    public PositionWrapper(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * @return y
     */
    public int getY() {
        return y;
    }
}
