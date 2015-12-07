package mmo.tools;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class DialogPane extends JOptionPane {
	private static final long serialVersionUID = 5270605433282497781L;

	public DialogPane() {
		// TODO Auto-generated constructor stub
	}

	public DialogPane(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DialogPane(Object message, int messageType) {
		super(message, messageType);
		// TODO Auto-generated constructor stub
	}

	public DialogPane(Object message, int messageType, int optionType) {
		super(message, messageType, optionType);
		// TODO Auto-generated constructor stub
	}

	public DialogPane(Object message, int messageType, int optionType, Icon icon) {
		super(message, messageType, optionType, icon);
		// TODO Auto-generated constructor stub
	}

	public DialogPane(Object message, int messageType, int optionType, Icon icon, Object[] options) {
		super(message, messageType, optionType, icon, options);
		// TODO Auto-generated constructor stub
	}

	public DialogPane(Object message, int messageType, int optionType, Icon icon, Object[] options, Object initialValue) {
		super(message, messageType, optionType, icon, options, initialValue);
		// TODO Auto-generated constructor stub
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(getRootFrame(), message);
	}
}
