package together;


import together.util.queue.Queue;
import together.util.PositionWrapper;

public class Map {

    private Tile[][] gameField;
    private Queue<PositionWrapper> playerPositions = new Queue<>();

    public Map(int size) {
        // TODO: Setting static starting positions & loading them into playerPositions Queue
        {
            playerPositions.push(new PositionWrapper(2, 1));
            playerPositions.push(new PositionWrapper(5, 7));
            playerPositions.push(new PositionWrapper(3, 7));
            playerPositions.push(new PositionWrapper(8, 9));
        }
        gameField = new Tile[size][size];
        {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    gameField[i][j] = new Tile();
                }
            }
        }
    }

    public Tile[][] getGameField() {
        return gameField;
    }

    /**
     * Returns a starting position on the map
     *
     * @return return an array with the size of 2
     */
    public PositionWrapper getStartPosition() {
        if (playerPositions.size() > 0) {
            return playerPositions.poll();
        }
        throw new IndexOutOfBoundsException("There is no position remaining in the starting positions");
    }

    public int xSize() {
        return gameField.length;
    }

    public int ySize() {
        return gameField[0].length;
    }
}