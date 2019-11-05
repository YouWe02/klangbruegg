package protocol.server2client;

import network.Message;
import together.Map;

public class Update implements Message {
  private Map map;

  public Update(Map map) {
    this.map = map;
  }

  public Map getMap() {
    return map;
  }
}
