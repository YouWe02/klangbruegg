package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import control.EditorControl;
import figuren.*;

@SuppressWarnings("serial")
public final class EditorPanel extends JPanel{
	private EditorControl editorControl;
	private Figur figur;

	EditorPanel(EditorControl control) {
		editorControl = control;
		MouseEventAdapter mAdapter = new MouseEventAdapter(editorControl, this);
		addMouseListener(mAdapter);
		addMouseMotionListener(mAdapter);
	}

	// Die paintComponent()-Methode wird automatisch aufgerufen, wenn irgendwer die
	// repaint()-
	// Methode beim EditorFrame oder beim EditorPanel aufruft.
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		editorControl.allesNeuZeichnen(g);
	}
}

class MouseEventAdapter extends MouseAdapter {
	EditorPanel editorPanel;
	EditorControl editorControl;

	public MouseEventAdapter(EditorControl editorControl, EditorPanel editorPanel) {
		this.editorControl = editorControl;
		this.editorPanel = editorPanel;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		editorControl.setErsterPunkt(e.getPoint());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		editorControl.erzeugeFigurMitZweitemPunkt(e.getPoint());
		editorPanel.repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		editorControl.zeichne(e.getPoint());
		editorPanel.repaint();
	}
}