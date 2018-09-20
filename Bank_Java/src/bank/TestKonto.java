package bank;

public class TestKonto {

	public static void main (String args[]) {
		Kunde peter = new Kunde("müller", "peter", 550213884);
		Kunde fritz = new Kunde("wegmüller", "fritz", 178495612);
		
		Konto konto = new Konto(0.01f, peter);
		Konto konto2 = new Konto(0.01f, fritz);
		
		System.out.println("Der Kunde " + konto.getInhaber().getName() + " hat ein Konto");
		konto.einzahlen(500.75);
		konto.verzinsen(365);
		System.out.println("Das Saldo beträgt " + konto.getSaldo() + " CHF");
		
		System.out.println("Der Kunde " + konto2.getInhaber().getName() + " hat ein Konto");
		konto2.einzahlen(720.5);
		konto2.verzinsen(750);
		System.out.println("Das Saldo beträgt " + konto2.getSaldo() + " CHF");
		
		konto2.einzahlen(50);
		System.out.println("Das Saldo beträgt " + konto2.getSaldo() + " CHF");
		
	}
}
