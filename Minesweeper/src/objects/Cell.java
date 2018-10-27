package objects;

public class Cell {

	private String inhalt;
	private Status status;
	private Integer bombsAround;
	private boolean bomb = false;

	public boolean isBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getBombsAround() {
		return bombsAround;
	}

	public void setBombsAround(int bombsAround) {
		this.bombsAround = bombsAround;
	}
}
