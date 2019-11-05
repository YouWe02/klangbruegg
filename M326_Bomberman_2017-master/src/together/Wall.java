package together;


public class Wall extends Tile {

    private boolean destroyable;

    public Wall(boolean isDestroyable){
        this.destroyable = isDestroyable;
    }

    public boolean isDestroyable() {
        return destroyable;
    }
}