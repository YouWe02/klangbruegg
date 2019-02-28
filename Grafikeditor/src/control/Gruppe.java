package control;

import java.awt.Graphics;
import java.awt.List;
import java.io.Console;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import figuren.Figur;
import util.ClassConverter;

public class Gruppe extends Figur {

	private ArrayList<Figur> figuren;

	public Gruppe(ArrayList<Figur> figuren, int xPos, int yPos) {
		super(xPos, yPos);
		this.figuren = figuren;
	}

	public Gruppe() {
		this.figuren = new ArrayList<Figur>();
	}


	@Override
	public void save(String filename) {
		try {
			PrintWriter writer = new PrintWriter(filename + ".txt", "UTF-8");
			ArrayList<String> writeVals = getAttributes();
			writeVals.remove(2);
			writeVals.forEach((line) -> writer.println(line));
			figuren.forEach((figur) -> {
				try {
					figur.getAttributes().forEach((line) -> writer.println(line));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			writer.close();
		} catch (Exception e) {
			System.out.println("Es gab einen Fehler beim Speichern.");
			e.printStackTrace();
		}
	}

	@Override
	public Gruppe read(String filename) throws Exception {
		clear();
		int figurCounter = -2;
		ArrayList<String> figureVals = (ArrayList<String>) Files.readAllLines(Paths.get(filename + ".txt"));

		for (int i = 0; i < figureVals.size(); i++) {
			String actualVal = figureVals.get(i);
			if (actualVal.equals("#")) {
				figurCounter++;
			} else if (!actualVal.contains(":")) {
				Class<? extends Figur> className = (Class<? extends Figur>) Class.forName(actualVal);
				if (figurCounter >= 0) {
					Constructor<? extends Figur> ctr = className.getDeclaredConstructor();
					this.figuren.add(ctr.newInstance());
				}
			} else {
				if (figurCounter == -1) {
					Field field = ClassConverter.getDeclaredFieldOfClassAndSuperclass(actualVal.split(":")[0],
							this.getClass());
					field.setAccessible(true);
					Class<?> type = field.getType();
					field.set(this, ClassConverter.createInstance(type, actualVal.split(":")[1]));
					field.setAccessible(false);
				} else {
					Class<? extends Figur> classOfObject = figuren.get(figurCounter).getClass();
					Field field = ClassConverter.getDeclaredFieldOfClassAndSuperclass(actualVal.split(":")[0],
							classOfObject);
					field.setAccessible(true);
					Class<?> type = field.getType();
					field.set(figuren.get(figurCounter), ClassConverter.createInstance(type, actualVal.split(":")[1]));
					field.setAccessible(false);
				}
			}
		}
		return this;
	}

	public void clear() {
		this.xPos = null;
		this.yPos = null;
		this.figuren.clear();
	}
	
	@Override
	public void zeichne(Graphics g) {
			for (Figur f : figuren) {
				f.zeichne(g);
		}
	}
	

	public void hinzufuegen(Figur figur) {
		figuren.add(figur);
	}
	
	public void entfernen(Figur figur) {
		figuren.remove(figur);
	}
	
	public boolean enthaelt(Figur figur) {
		return figuren.contains(figur);
	}


	public void allesLoeschen() {
		figuren.clear();
	}
	
	public void loeschen(Figur f) {
		int index = figuren.indexOf(f);
		figuren.remove(index);
	}

}
