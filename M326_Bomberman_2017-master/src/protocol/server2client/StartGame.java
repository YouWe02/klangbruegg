package protocol.server2client;

import network.Message;
import together.Map;
import together.Tile;

/**
 * Diese Meldung wird vom Server an alle Clients gesendet, sobald gen�gend Spieler angemeldet
 * sind und das Spiel los geht.
 *
 * @author Andres Scheidegger
 */
public class StartGame implements Message {
  private Map map;

  public StartGame(Map map) {
    this.map = map;
  }

  public Tile[][] getMap() {
    return map.getGameField();
  }
}
