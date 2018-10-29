package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Benutzereingabe {
	Scanner sc = new Scanner(System.in);

	public int getInteger(char c) {
		int i;
		if (c == 'X') {
			System.out.println("Geben sie die X Koordinate ein (1-10)");
		} else {
			System.out.println("Geben sie die Y Koordinate ein (1-10)");
		}
		while (true) {
			try {
				i = sc.nextInt();
				if (i > 10 || i < 0) {
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
