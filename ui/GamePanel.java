package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class GamePanel {
	private static JPanel pGame = new JPanel();
    private static JButton btnBack = new JButton("Back to Main Menu");

    static {
        /* panel layout */
        pGame.setBackground(Color.BLACK);
        pGame.add(new JLabel("Game Screen"));
        /* panel buttons*/
        pGame.add(btnBack);
    }

	public static JPanel getPanel() {
        return pGame;
    }

    public static JButton getBtn(String btnName) {
		return btnBack;
	}
}
