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

				field.checkField(x - 1, y - 1);
				if (field.isWon()) {
					field.printField();
					System.out.println("Du hast gewonnen.");
					break;
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
		int i;
		while (true) {
			try {
				i = sc.nextInt();
				if (i < 0 || 1 > 10) {
					throw new InputMismatchException();

				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Die Eingabe ist nicht Valid");

			}
		}

		return i;

	}

}
