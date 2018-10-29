package controller;

import exceptions.BombOnFieldException;
import objects.Field;

public class GameLoop {
	Benutzereingabe benutzereingabe = new Benutzereingabe();

	Field field = new Field(10, 10, 6);

	public void run() {
		field.createField();

		while (true) {
			try {
				field.printField();
				field.checkField(benutzereingabe.getInteger('X') - 1, benutzereingabe.getInteger('Y') - 1);
				if (field.isWon()) {
					field.printField();
					field.zeigeSchlussmeldung(true);
					break;
				}

			} catch (BombOnFieldException e) {
				field.revealField();
				field.printField();
				field.zeigeSchlussmeldung(false);
				break;
			}
		}

	}

}
