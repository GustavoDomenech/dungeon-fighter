package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicToolTipUI;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import config.Settings;

public class MenuPanel {
	/* window geometry */
	private static int w = Settings.WINDOW_WIDTH;
	private static int h = Settings.WINDOW_HEIGHT;
	/* panel layout */
	private static JPanel pMenu = new JPanel() {
		private Image backgroundImage;

		{
			try {
				backgroundImage = ImageIO.read(new File("assets/background.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, w, h, this);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			if (backgroundImage != null) {
				return new Dimension(w, h);
			} else {
				return super.getPreferredSize();
			}
		}
	};
	/* buttons */
	private static JButton btnNewGame = new JButton("Jogar");
	private static JButton btnDebug = new JButton("DEBUG");
	private static JButton btnExit = new JButton("Sair");

	static {
		/* button layout */
		pMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		/* add buttons to menu panel */
		pMenu.add(btnNewGame, gbc);
		pMenu.add(btnDebug, gbc);
		pMenu.add(btnExit, gbc);
	}

	public static JPanel getPanel() {
        return pMenu;
    }

    public static JButton getBtn(String btnName) {
        switch (btnName) {
			case "Jogar":
				return btnNewGame;
			case "DEBUG":
				return btnDebug;
			case "Sair":
				return btnExit;
		}

		return btnExit;
    }
}

