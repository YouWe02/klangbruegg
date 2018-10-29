package objects;

import java.util.Random;

import exceptions.BombOnFieldException;

/**
 * Diese Klasse repräsentiert das Feld vom Minesweeper.
 * 
 * @author Elizar Pongracz
 *
 */
public class Field {

	Random random = new Random();
	int randomIntRange;
	Cell[][] feld;


	/**
	 * Konstruktor
	 * 
	 * @param x      Anzahl Felder - X Achse
	 * @param y      Anzahl Felder - Y Achse
	 * @param random Schwierigkeitsgrad. Je geringer die Zahl, umso mehr Bomben wird
	 *               es geben.
	 */
	public Field(int x, int y, int random) {
		this.feld = new Cell[x][y];
		this.randomIntRange = random;
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
					feld[i][j].setBombe(true);
				} else {
					feld[i][j].setBombe(false);
				}

			}
		}
		countNeightbours();
	}

	/**
	 * Zählt die Bomben um ein Feld & speichert dies in der BombsAround Variable vom
	 * Feld.
	 */
	public void countNeightbours() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld.length; j++) {
				int count = 0;

				if (inField(i - 1, j) && feld[i - 1][j].isBombe()) {
					count++;
				}

				if (inField(i + 1, j) && feld[i + 1][j].isBombe()) {
					count++;
				}

				if (inField(i, j - 1) && feld[i][j - 1].isBombe()) {
					count++;
				}

				if (inField(i, j + 1) && feld[i][j + 1].isBombe()) {
					count++;
				}

				if (inField(i + 1, j + 1) && feld[i + 1][j + 1].isBombe()) {
					count++;
				}

				if (inField(i + 1, j - 1) && feld[i + 1][j - 1].isBombe()) {
					count++;
				}

				if (inField(i - 1, j + 1) && feld[i - 1][j + 1].isBombe()) {
					count++;
				}

				if (inField(i - 1, j - 1) && feld[i - 1][j - 1].isBombe()) {
					count++;
				}

				feld[i][j].setBombsAround(count);
			}
		}

	}

	/**
	 * Überprüft, ob das Spiel gewonnen wurde
	 * 
	 * @return true --> win false--> no win
	 */
	public boolean isWon() {
		boolean isWon = true;
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld.length; j++) {
				if (feld[i][j].getInhalt().equals("?")) {
					isWon = false;
				}
			}
		}
		return isWon;
	}

	/**
	 * Checkt ob eine Bombe auf dem Feld ist und die Nachbaren fragt es dann auch.
	 * 
	 * @param x
	 * @param y
	 * @throws BombOnFieldException
	 */
	public void checkField(Integer x, Integer y) throws BombOnFieldException {

		if (feld[x][y].isBombe()) {
			feld[x][y].setInhalt("X");
			throw new BombOnFieldException();
		} else if (feld[x][y].getBombsAround() == 0) {
			feld[x][y].setInhalt(" ");

			revealNeightbours(x, y);
		} else {
			feld[x][y].setInhalt(feld[x][y].getBombsAround().toString());

		}

	}

	/**
	 * Deckt die Nachbaren auf fals muss.
	 * 
	 */
	public void revealNeightbours(Integer x, Integer y) throws BombOnFieldException {

		if (inField(x, y) && inField(x, y) && feld[x][y].isAufgedeckt() == false) {
			feld[x][y].setAufgedeckt(true);
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

	/**
	 * Schaut ob die neuen Plätze gültig sind
	 * 
	 */
	public void checkIfReveal(Integer x, Integer y) throws BombOnFieldException {
		if (inField(x, y)) {
			checkField(x, y);

		}

	}

	/**
	 * Methode um zu verhindern das eine Exception geworfen wird.
	 * 
	 */
	public boolean inField(Integer x, Integer y) {
		return x > -1 && x < feld.length && y > -1 && y < feld.length;
	}

	/**
	 * Deckt das Feld auf wenn das Spiel fertig ist.
	 * 
	 */
	public void revealField() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld.length; j++) {
				if (feld[i][j].isBombe() && feld[i][j].getInhalt() != "X") {
					feld[i][j].setInhalt("x");
				}

			}
		}

	}

	/**
	 * Printet das Feld für den Benutzer
	 * 
	 */
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

	/**
	 * Printet am Ende ob man gewonnen oder verloren hat.
	 * 
	 */
	public void zeigeSchlussmeldung(boolean won) {
		if (won) {
			System.out.println("Du hast gewonnen.");
		} else {
			System.out.println("Sie haben eine Bombe getroffen und somit veloren");
		}
	}

}
