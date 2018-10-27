package objects;

import java.util.Random;

import org.w3c.dom.css.Counter;

import exceptions.BombOnFieldException;

/**
 * Diese Klasse repräsentiert das Feld vom
 * Minesweeper.
 * @author Y.Weber
 *
 */
public class Field {

	Random random = new Random();
	int randomIntRange;
	Cell[][] feld;
	int x;
	int y;

	/**
	 * Konstruktor
	 * @param x Anzahl Felder - X Achse
	 * @param y Anzahl Felder - Y Achse
	 * @param random Schwierigkeitsgrad. Je geringer die Zahl, umso mehr Bomben wird es geben.
	 */
	public Field(int x, int y, int random) {
		this.feld = new Cell[y][x];
		this.randomIntRange = random;
		this.x = x;
		this.y = y;
	}

	/**
	 * Erstellt das Feld & verteilt Bomben.
	 */
	public void createField() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld.length; j++) {
				Cell cell = new Cell();
				feld[i][j] = cell;
				feld[i][j].setInhalt("?");
				if (random.nextInt(this.randomIntRange) == 0) {
					feld[i][j].setBomb(true);
				}
				feld[i][j].setStatus(Status.NEUTRAL);

			}
		}
		countBombsAround();
	}

	/**
	 * Zählt die Bomben um ein Feld & speichert 
	 * dies in der BombsAround Variable vom Feld.
	 */
	public void countBombsAround() {
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				Cell cell = feld[i][j];
				int counter = 0;
				// j = x , i = y
				//Links Oben
				if(j > 0 && i > 0) {
					if(feld[i - 1][j - 1].isBomb()) {
						counter++;
					}
				}
				//Oben
				if(i > 0 ) {
					if(feld[i - 1][j].isBomb()) {
						counter++;
					}
				}
				//Rechts oben
				if(i > 0 && j < x - 1) {
					if(feld[i - 1][j + 1].isBomb()) {
						counter++;
					}
				}
				//Rechts
				if(j < x - 1) {
					if(feld[i][j + 1].isBomb()) {
						counter++;
					}
				}
				//Rechts unten
				if(j < x -1 && i < y -1) {
					if(feld[i + 1][j + 1].isBomb()) {
						counter++;
					}
				}
				//unten
				if(i < y - 1) {
					if(feld[i + 1][j].isBomb()) {
						counter++;
					}
				}
				//Links unten
				if(j > 0 && i < y - 1) {
					if(feld[i + 1][j - 1].isBomb()) {
						counter++;
					}
				}
				//Links
				if(j > 0) {
					if(feld[i][j - 1].isBomb()) {
						counter++;
					}
				}
				cell.setBombsAround(counter);
			}
		}

	}

	/**
	 * Überprüft, ob das Spiel gewonnen wurde
	 * @return true --> win false--> no win
	 */
	public boolean isWon() {
		boolean isWon = false;
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if (feld[i][j].isBomb() && feld[i][j].getStatus() == Status.MARKED) {
					isWon = true;
				} if (!feld[i][j].isBomb() && feld[i][j].getStatus() == Status.MARKED) {
					isWon = false;
				}
				if(feld[i][j].isBomb() && feld[i][j].getStatus() != Status.MARKED) {
					isWon = false;
				}
			}
		}
		return isWon;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @throws BombOnFieldException
	 */
	public void checkField(Integer x, Integer y) throws BombOnFieldException {

			if (feld[y][x].isBomb()) {
				feld[y][x].setInhalt("X");
				throw new BombOnFieldException();
			} else if (feld[y][x].getBombsAround() == 0) {
				feld[y][x].setInhalt(" ");
				revealNeightbours(x, y);
			} else {
				feld[y][x].setInhalt(feld[y][x].getBombsAround().toString());
			}
	}
	
	public boolean inField(int x, int y) {
		return x >= 0 && x < this.x && y >= 0 && y < this.y;
	}

	public void revealNeightbours(Integer x, Integer y) throws BombOnFieldException {

		if (feld[y][x].getStatus() != Status.OPENED) {
			feld[y][x].setStatus(Status.OPENED);
			checkIfReveal(x - 1, y);
			checkIfReveal(x + 1, y);
			checkIfReveal(x, y - 1);
			checkIfReveal(x, y + 1);
			checkIfReveal(x + 1, y + 1);
			checkIfReveal(x - 1, y - 1);
			checkIfReveal(x + 1, y - 1);
			checkIfReveal(x - 1, y + 1);

		}

	}

	public void checkIfReveal(Integer x, Integer y) throws BombOnFieldException {
		if (x >= 0 && y >= 0 && x < this.x && y < this.y) {
			checkField(x, y);

		}

	}

	public void revealField() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld.length; j++) {
				if (feld[j][i].isBomb()) {
					feld[j][i].setInhalt("x");
				}

			}
		}

	}

	public void printField() {
		System.out.println("____________________________________________");
		System.out.print("   ");
		for (int i = 1; i < feld.length + 1; i++) {

			System.out.print("|");
			if (i < 10) {
				System.out.print("0");
			}

			System.out.print(i + " ");
			if (i == feld.length) {
				System.out.println("|");
			}
		}

		System.out.println("____________________________________________");
		for (int i = 0; i < feld.length; i++) {
			if (i < 9) {
				System.out.print("0");
			}
			System.out.print(i + 1 + " | ");
			for (int j = 0; j < feld.length; j++) {
				System.out.print(feld[i][j].getInhalt() + " | ");
			}
			System.out.println("");

		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public void markField(int x, int y) {
		feld[y][x].setStatus(Status.MARKED);
		feld[y][x].setInhalt("!");
	}
}
