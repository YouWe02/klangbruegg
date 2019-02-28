package figuren;

import java.awt.Color;
import java.awt.Graphics;

public class Linie extends Figur {

	private Integer xEnde;
	private Integer yEnde;
	
	public Linie() {}

	
	public Linie(int xEnde, int yEnde, int xPos, int yPos) {
		super(xPos, yPos);
		this.xEnde = xEnde;
		this.yEnde = yEnde;
	}
	
	public Linie(int xEnde, int yEnde, int xPos, int yPos, Color color) {
		super(xPos, yPos, color);
		this.xEnde = xEnde;
		this.yEnde = yEnde;
	}

	
	@Override
	public void zeichne(Graphics g) {
		g.setColor(color);
		g.drawLine(xPos, yPos, xEnde, yEnde);

	}


	public Integer getxEnde() {
		return xEnde;
	}


	public Integer getyEnde() {
		return yEnde;
	}
	
	

}
