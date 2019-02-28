package figuren;

import java.awt.Color;
import java.awt.Graphics;

public class Kreis extends Figur {

	private Integer radius;
	
	public Kreis() {}
	
	public Kreis(int radius, int xPos, int yPos) {
		super(xPos, yPos);
		this.radius = radius;
	}
	
	public Kreis(int radius, int xPos, int yPos, Color color) {
		super(xPos, yPos, color);
		this.radius = radius;
	}

	@Override
	public void zeichne(Graphics g) {
		g.setColor(color);
		g.drawOval(xPos, yPos, radius, radius);

	}

	public Integer getRadius() {
		return radius;
	}
	
	
}
