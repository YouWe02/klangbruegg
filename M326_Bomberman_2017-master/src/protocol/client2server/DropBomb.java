package protocol.client2server;

//TODO: Fragen warum X und Y nicht einfach aufgrund des Players gesetzt werden

public class DropBomb extends ClientMessage {
  private int positionX;
  private int positionY;

  public DropBomb(String playerName, int positionX, int positionY) {
    super(playerName);
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }
}
