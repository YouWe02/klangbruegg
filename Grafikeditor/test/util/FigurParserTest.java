package util;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import figuren.Figur;
import figuren.Kreis;
import figuren.Linie;
import figuren.Rechteck;
import shared.FigurDAOStub;

public class FigurParserTest {

	private FigurDAO dao;
	private FigurParser parser;
	
	@Before
	public void setup() {
		dao = new FigurDAOStub();
		parser = new FigurParser(dao);
	}
	
	@Test
	public void test() throws Exception {
		ArrayList<Figur> figuren;
		Rechteck r;
		Kreis k;
		Linie l;
		
		figuren = parser.parse();
		r = (Rechteck)figuren.get(0);
		k = (Kreis)figuren.get(1);
		l = (Linie)figuren.get(2);

		assertEquals(figuren.size(), 3);
		
		//Figur 1
		assertEquals(figuren.get(0).getClass(), Rechteck.class);
		assertEquals(r.getxPos(), Double.valueOf(100), 0.000001);
		assertEquals(r.getyPos(), Double.valueOf(100), 0.000001);
		assertEquals(r.getBreite(), Double.valueOf(50), 0.000001);
		assertEquals(r.getHoehe(), Double.valueOf(100), 0.000001);
		
		//Figur 2
		assertEquals(figuren.get(1).getClass(), Kreis.class);
		assertEquals(k.getxPos(), Double.valueOf(200), 0.000001);
		assertEquals(k.getyPos(), Double.valueOf(200), 0.000001);
		assertEquals(k.getRadius(), Double.valueOf(25), 0.000001);
		
		//Figur 3
		assertEquals(figuren.get(2).getClass(), Linie.class);
		assertEquals(l.getxPos(), Double.valueOf(300), 0.000001);
		assertEquals(l.getyPos(), Double.valueOf(300), 0.000001);
		assertEquals(l.getxEnde(), Double.valueOf(400), 0.000001);
		assertEquals(l.getyEnde(), Double.valueOf(400), 0.000001);
	}

}
