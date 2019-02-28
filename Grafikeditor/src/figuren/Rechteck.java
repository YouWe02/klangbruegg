package figuren;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public class Rechteck extends Figur{

	private Integer breite;
	private Integer hoehe;
	
	public Rechteck() {}
	
	public Rechteck(int breite, int hoehe, int xPos, int yPos) {
		super(xPos, yPos);
		this.breite = breite;
		this.hoehe = hoehe;
	}
	
	public Rechteck(int breite, int hoehe, int xPos, int yPos, Color color) {
		super(xPos, yPos, color);
		this.breite = breite;
		this.hoehe = hoehe;
	}

	
	@Override
	public void zeichne(Graphics g) {
		g.setColor(color);
		g.drawRect(xPos, yPos, breite, hoehe);
	}

	public Integer getBreite() {
		return breite;
	}

	public Integer getHoehe() {
		return hoehe;
	}
	
	
}
