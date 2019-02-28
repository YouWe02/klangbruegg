package figuren;

import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window.Type;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import control.Gruppe;
import util.ClassConverter;

public abstract class Figur {

	protected Integer xPos;
	protected Integer yPos;
	protected boolean farbig = false;
	protected Color color;

	public Figur() {
	}

	public Figur(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public Figur(int xPos, int yPos, Color color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.farbig = true;
		this.color = color;
	}


	public ArrayList<String> getAttributes() throws Exception {
		ArrayList<String> returnVal = new ArrayList<String>();
		returnVal.add("#");
		returnVal.add(getClass().getName());
		for (Class<?> c = this.getClass(); c != null; c = c.getSuperclass()) {
			Field[] attr = c.getDeclaredFields();
			for (Field field : attr) {
				field.setAccessible(true);
				String name = field.getName();
				String value = String.valueOf(field.get(this));
				returnVal.add(name + ":" + value);		
				field.setAccessible(false);
			}
		}
		return returnVal;
	}

	public void save(String filename) {
		try {
			PrintWriter writer = new PrintWriter(filename+".txt", "UTF-8");
			getAttributes().forEach((line) -> writer.println(line));
			writer.close();
		} catch (Exception e) {
			System.out.println("Es gab einen Fehler beim Speichern.");
			e.printStackTrace();
		}
	}
	
	public Figur read(String pathname) throws Exception{
		int figurCounter = -1;
		ArrayList<String> figureVals = (ArrayList<String>) Files.readAllLines(Paths.get(pathname+".txt"));
		
		for(int i = 0; i < figureVals.size(); i++) {
			String actualVal = figureVals.get(i);
			if(actualVal.equals("#")) {
				figurCounter++;
			} else if(!actualVal.contains(":")) {
				Class<?> className = Class.forName(actualVal);
			} else {
				Class<? extends Figur> classOfObject = this.getClass();
				Field field = ClassConverter.getDeclaredFieldOfClassAndSuperclass(actualVal.split(":")[0], classOfObject);
				field.setAccessible(true);
				Class<?> type = field.getType();
				field.set(this, ClassConverter.createInstance(type, actualVal.split(":")[1]));
				field.setAccessible(false);
			}
		}
		
		return this;
	}
	
	public abstract void zeichne(Graphics g);

	public Integer getxPos() {
		return xPos;
	}

	public Integer getyPos() {
		return yPos;
	}

	public boolean isFarbig() {
		return farbig;
	}

	public Color getColor() {
		return color;
	}
	
	
	
}
