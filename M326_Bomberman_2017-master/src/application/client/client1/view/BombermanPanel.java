package application.client.client1.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import application.client.client1.control.ControlFactory;
import application.client.client1.control.JoinGameControl;

public class BombermanPanel extends JPanel {
	private JTextField playerNameTextField = new JTextField();
	private JTextArea messageTextArea = new JTextArea();
	
	public BombermanPanel() {
		setLayout(new BorderLayout());
		playerNameTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinGameControl control = ControlFactory.instance().createJoinGameControl();
				control.joinGame(playerNameTextField.getText());
				playerNameTextField.setEnabled(false);
			}
		});
		add(playerNameTextField, BorderLayout.NORTH);
		messageTextArea.setRows(4);
		messageTextArea.setEditable(false);
		add(messageTextArea, BorderLayout.SOUTH);
	}

    public void displayMessage(String message) {
		messageTextArea.append(message + "\n");
    }
}
