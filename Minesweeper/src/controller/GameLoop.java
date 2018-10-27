package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.BombOnFieldException;
import objects.Field;

public class GameLoop {

	Scanner sc = new Scanner(System.in);

	Field field = new Field(10, 10, 6);

	public void run() {
		field.createField();

		while (true) {
			try {

				field.printField();
				System.out.println("Geben sie die X Koordinate ein (1-10)");
				int x = getInteger();
				System.out.println("Geben sie die Y Koordinate ein (1-10)");
				int y = getInteger();

				System.out.println("Was wollen Sie mit dem Feld machen? (m=markieren,a=aufdecken)");
				char action = getAction();

				if (action == 'a') {

					field.checkField(x - 1, y - 1);
					if (field.isWon()) {
						field.printField();
						System.out.println("Du hast gewonnen.");
						break;
					}
				} else {
					field.markField(x - 1, y - 1);
					if (field.isWon()) {
						field.printField();
						System.out.println("Du hast gewonnen.");
						break;
					}
				}

			} catch (BombOnFieldException e) {
				field.revealField();
				field.printField();
				System.out.println("Sie haben eine Bombe getroffen und somit veloren");
				break;
			}
		}

	}

	public int getInteger() {
		int integer;
		try {
			integer = Integer.parseInt(sc.nextLine());
			if (integer > 0 && integer < 11) {
				return integer;
			} else {
				System.out.println("Falsche Eingabe, erneut eingeben");
				return getInteger();
			}
		} catch (Exception e) {
			System.out.println("Falsche Eingabe, erneut eingeben");
			return getInteger();
		}

	}

	public char getAction() {
		char action;
		try {
			action = sc.nextLine().charAt(0);
			if (action == 'a' || action == 'm') {
				return action;
			} else {
				System.out.println("Falsche Eingabe, erneut eingeben");
				return getAction();
			}
		} catch (Exception e) {
			System.out.println("Falsche Eingabe, erneut eingeben");
			return getAction();
		}
	}

}
