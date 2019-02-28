package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.EditorControl;

@SuppressWarnings("serial")
public final class EditorFrame extends JFrame{
	private EditorControl editorControl = new EditorControl();

	public EditorFrame(int breite, int hoehe) {
		erzeugeUndSetzeEditorPanel();
		fensterEinmitten(breite, hoehe);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		KeyEventAdapter keyAdapter = new KeyEventAdapter(editorControl, this);
		addKeyListener(keyAdapter);
		setVisible(true);
	}

	private void erzeugeUndSetzeEditorPanel() {
		JPanel panel = new EditorPanel(editorControl);
		setContentPane(panel);
	}

	private void fensterEinmitten(int breite, int hoehe) {
		Dimension bildschirmGroesse = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle fensterAusschnitt = new Rectangle();
		fensterAusschnitt.width = breite;
		fensterAusschnitt.height = hoehe;
		fensterAusschnitt.x = (bildschirmGroesse.width - fensterAusschnitt.width) / 2;
		fensterAusschnitt.y = (bildschirmGroesse.height - fensterAusschnitt.height) / 2;
		setBounds(fensterAusschnitt);
	}

	
}

class KeyEventAdapter extends KeyAdapter{
	EditorControl editorControl;
	EditorFrame editorFrame;
	public KeyEventAdapter (EditorControl editorControl, EditorFrame editorFrame) {
		this.editorControl = editorControl;
		this.editorFrame = editorFrame;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 's') {
			String name = JOptionPane.showInputDialog("Bitte Name der Zeichnung angeben");
			editorControl.speichern(name);
		} else if (e.getKeyChar() == 'x') {
			String name = JOptionPane.showInputDialog("Bitte Name der Zeichnung angeben");
			editorControl.laden(name);
			editorFrame.repaint();
		} else if (e.getKeyChar() == 'c') {
			editorControl.allesLoeschen();
			editorFrame.repaint();
		}else {
			editorControl.setFigurTyp(e.getKeyChar());
		}
	}
}

