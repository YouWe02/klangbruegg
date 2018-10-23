package objects;

public class Cell {

	private String inhalt;
	private boolean bombe;
	private Integer bombsAround;
	private boolean aufgedeckt = false;

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public boolean isBombe() {
		return bombe;
	}

	public void setBombe(boolean bombe) {
		this.bombe = bombe;
	}

	public Integer getBombsAround() {
		return bombsAround;
	}

	public void setBombsAround(int bombsAround) {
		this.bombsAround = bombsAround;
	}

	public boolean isAufgedeckt() {
		return aufgedeckt;
	}

	public void setAufgedeckt(boolean aufgedeckt) {
		this.aufgedeckt = aufgedeckt;
	}



}
