package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;

import figuren.*;

public final class EditorControl {
	private Gruppe zeichnung = new Gruppe();
	private char figurTyp = 'l';
	private Point ersterPunkt;
	private Point zweiterPunkt;
	private Figur figurPlaceholder;
	private Color color = Color.BLACK;

	public void allesNeuZeichnen(Graphics g) {
		zeichnung.zeichne(g);

	}

	public void allesLoeschen() {
		zeichnung.allesLoeschen();
	}

	public void setFigurTyp(char figurTyp) {
		if (figurTyp == 's') {
			String name = JOptionPane.showInputDialog("Bitte Name der Datei angeben");
			speichern(name);
		} else if (figurTyp == 'x') {
			String name = JOptionPane.showInputDialog("Bitte Name der Datei angeben");
			laden(name);
		}
		else if (figurTyp == 'y') {
			color = Color.YELLOW;
		}
		else if (figurTyp == 'o') {
			color = Color.ORANGE;
		}
		else if (figurTyp == 'g') {
			color = Color.GREEN;
		}
		else if (figurTyp == 'p') {
			color = Color.PINK;
		} 
		else if (figurTyp == 'b'){
			color = Color.BLACK;
		}else {
			this.figurTyp = figurTyp;
		}
	}

	public void setErsterPunkt(Point ersterPunkt) {
		this.ersterPunkt = ersterPunkt;
	}

	public void erzeugeFigurMitZweitemPunkt(Point zweiterPunkt) {
		int hoehe = Math.abs(zweiterPunkt.y - ersterPunkt.y);
		int breite = Math.abs(zweiterPunkt.x - ersterPunkt.x);
		int xPosEnde = zweiterPunkt.x;
		int yPosEnde = zweiterPunkt.y;
		int xPos = ersterPunkt.x;
		int yPos = ersterPunkt.y;
		if (zweiterPunkt.y < ersterPunkt.y && figurTyp != 'l' && figurTyp != 'k') {
			yPos = zweiterPunkt.y;
		}
		if (zweiterPunkt.x < ersterPunkt.x && figurTyp != 'l' && figurTyp != 'k') {
			xPos = zweiterPunkt.x;
		}
		switch (figurTyp) {
		case 'r':
			Rechteck rechteck = new Rechteck(breite, hoehe, xPos, yPos, color);
			zeichnung.hinzufuegen(rechteck);
			break;
		case 'l':
			Linie figur = new Linie(xPosEnde, yPosEnde, xPos, yPos, color);
			zeichnung.hinzufuegen(figur);
			break;
		case 'k':
			int radius = (int) Math.floor(Math.sqrt(Math.pow(breite, 2) + Math.pow(hoehe, 2)) / 2);
			Kreis kreis = new Kreis(radius * 2, xPos - radius, yPos - radius, color);
			zeichnung.hinzufuegen(kreis);
			break;
		default:
			System.out.println(figurTyp
					+ " ist keiner Form zugewiesen. \nVerfügbare Formen: \nr: Rechteck \nk: Kreis \nl: Linie \ne: Elipse");
		}

	}

	public void speichern(String filename) {
		zeichnung.save(filename);
	}

	public void laden(String filename) {
		try {
			zeichnung = zeichnung.read(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void zeichne(Point zweiterPunkt) {
		int hoehe = Math.abs(zweiterPunkt.y - ersterPunkt.y);
		int breite = Math.abs(zweiterPunkt.x - ersterPunkt.x);
		int xPosEnde = zweiterPunkt.x;
		int yPosEnde = zweiterPunkt.y;
		int xPos = ersterPunkt.x;
		int yPos = ersterPunkt.y;
		if (zweiterPunkt.y < ersterPunkt.y && figurTyp != 'l' && figurTyp != 'k') {
			yPos = zweiterPunkt.y;
		}
		if (zweiterPunkt.x < ersterPunkt.x && figurTyp != 'l' && figurTyp != 'k') {
			xPos = zweiterPunkt.x;
		}
		if (zeichnung.enthaelt(figurPlaceholder)) {
			zeichnung.entfernen(figurPlaceholder);;
		}
		switch (figurTyp) {
		case 'r':
			figurPlaceholder = new Rechteck(breite, hoehe, xPos, yPos, color);
			zeichnung.hinzufuegen(figurPlaceholder);
			break;
		case 'l':
			figurPlaceholder = new Linie(xPosEnde, yPosEnde, xPos, yPos, color);
			zeichnung.hinzufuegen(figurPlaceholder);
			break;
		case 'k':
			int radius = (int) Math.floor(Math.sqrt(Math.pow(breite, 2) + Math.pow(hoehe, 2)) / 2);
			figurPlaceholder = new Kreis(radius * 2, xPos - radius, yPos - radius, color);
			zeichnung.hinzufuegen(figurPlaceholder);
			break;
		default:
			System.out.println(
					figurTyp + " ist keiner Form zugewiesen. \nVerfügbare Formen: \nr: Rechteck \nk: Kreis \nl: Linie");
		}
	}

}
