package bank;

public class Kunde {

	private String name;
	private String vorname;
	private int kontonummer;
	
	public Kunde(String name, String vorname, int kontonummer) {
		this.name = name;
		this.vorname = vorname;
		this.kontonummer = kontonummer;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public int getKontonummer() {
		return kontonummer;
	}

	public void setKontonummer(int kontonummer) {
		this.kontonummer = kontonummer;
	}
	
}
