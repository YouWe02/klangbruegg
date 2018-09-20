package bank;

public class Konto {
	
	private float zinssatz;
	private double saldo;
	private Kunde inhaber;

	public Konto(float zinssatz, Kunde inhaber) {
		this.zinssatz = zinssatz;
		this.inhaber = inhaber;
	}
	
	public void einzahlen(double betrag) {
		saldo += betrag;
	}
	
	public void verzinsen(int laufzeit) {
		zinssatzBerechnen();
		saldo += saldo * zinssatz * laufzeit / 365; 
	}

	public void zinssatzBerechnen() {
		if(saldo < 50000) {
			zinssatz = 0.01f;
		}
		else if(saldo > 50000 && saldo < 500000) {
			zinssatz = 0.005f;
		}
		else {
			zinssatz = 0;
		}
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public Kunde getInhaber() {
		return inhaber;
	}

	public void setInhaber(Kunde inhaber) {
		this.inhaber = inhaber;
	}
}
