package control;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import figuren.Figur;
import figuren.Kreis;
import figuren.Linie;
import figuren.Rechteck;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GruppeTest {

	Gruppe zeichnung;
	ArrayList<Figur> figuren;
	Rechteck r;
	Kreis k;
	Linie l;
	Graphics g;
	
	@Before
	public void setup() {
		r = new Rechteck(50, 100, 100, 100);
		k = new Kreis(25, 200, 200);
		l = new Linie(400, 400, 300, 300);
		figuren = new ArrayList<Figur>();
		figuren.add(r);
		figuren.add(k);
		figuren.add(l);
		zeichnung = new Gruppe(figuren, 0, 0);
		g = Mockito.mock(Graphics.class);
	}
	
	@Test
	public void zeichneTest() {
		zeichnung.zeichne(g);
		Mockito.verify(g, Mockito.times(1)).drawRect(100, 100, 50, 100);
		Mockito.verify(g, Mockito.times(1)).drawLine(300, 300, 400, 400);
		Mockito.verify(g, Mockito.times(1)).drawOval(200, 200, 25, 25);
	}

}
