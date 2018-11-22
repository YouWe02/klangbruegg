package bank_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bank.Konto;
import bank.Kunde;
import bank.TestKonto;

public class kontoTest {

	private Konto testKonto;
	private Kunde testKunde;
	
	@Before
	public void setUp() {
		testKunde = new Kunde("Test", "Kunde", 987654321);
		testKonto = new Konto(1, testKunde);
	}
	
	@Test
	public void einzahlenTest() {
		assertEquals(0, testKonto.getSaldo(), 0);
		testKonto.einzahlen(100.5);
		assertEquals(100.5, testKonto.getSaldo(), 0);
	}
	
	@Test
	public void verzinsenTest() {
		assertEquals(0, testKonto.getSaldo(), 0);
		testKonto.einzahlen(100);
		testKonto.verzinsen(365);
		assertEquals(101, testKonto.getSaldo(), 0.1	);
		
		testKonto.einzahlen(50000);
		testKonto.verzinsen(365);
		assertEquals(50351.505, testKonto.getSaldo(), 0.1);
		
		testKonto.einzahlen(1000000);
		testKonto.verzinsen(365);
		assertEquals(1050351.505, testKonto.getSaldo(), 0.1);
	}

}
