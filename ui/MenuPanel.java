package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;

import config.Settings;

public class MenuPanel {
	private static int w = Settings.WINDOW_WIDTH;
	private static int h = Settings.WINDOW_HEIGHT;
	private static Dimension buttonSize = new Dimension(120, 20);
	private static JButton btnNewGame = new JButton("Jogar");
	private static JButton btnDebug = new JButton("DEBUG");
	private static JButton btnExit = new JButton("Sair");
	/* ************************************************************************************
	 * *                          			MENU PANEL 		                              *
	 * ************************************************************************************/
	private static JPanel pMenu = new JPanel() {
		private Image bg = Settings.BACKGROUND;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, w, h, this);
		}

		@Override
		public Dimension getPreferredSize() { return new Dimension(w, h); }
	};
	/* ************************************************************************************
	 * *                          	LAYOUT DOS BOTOES 		                              *
	 * ************************************************************************************/
	static {
		/* tamanho dos botoes */
		btnNewGame.setPreferredSize(buttonSize);
		btnDebug.setPreferredSize(buttonSize);
		btnExit.setPreferredSize(buttonSize);
		/* layout do painel do menu */
		pMenu.setLayout(new GridBagLayout());
		/* layout dos botoes */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		/* adiciona os botoes ao painel do menu */
		pMenu.add(btnNewGame, gbc);
		pMenu.add(btnDebug, gbc);
		pMenu.add(btnExit, gbc);
	}

	public static JPanel getPanel() { return pMenu; }

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

