package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.Settings;

public class MenuPanel {
	private static Font f = Settings.FONT;
	private static int w = Settings.WINDOW_WIDTH;
	private static int h = Settings.WINDOW_HEIGHT;
	private static Dimension buttonSize = new Dimension(400, 50);
	private static JButton btnNewGame = new JButton("Jogar");
	private static JButton btnDebug = new JButton("DEBUG");
	private static JButton btnExit = new JButton("Sair");

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

	static {
		btnNewGame.setPreferredSize(buttonSize);
		btnDebug.setPreferredSize(buttonSize);
		btnExit.setPreferredSize(buttonSize);

		btnNewGame.setFont(f);
		btnDebug.setFont(f);
		btnExit.setFont(f);
		
		pMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		
		pMenu.add(Box.createVerticalStrut(150));
		pMenu.add(btnNewGame, gbc);
		pMenu.add(btnDebug, gbc);
		pMenu.add(btnExit, gbc);
	
		setupButtonListeners();
	}

	private static void setupButtonListeners() {
		ActionListener newGameButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				SelectHeroPanel.setupSelectHeroPanel(false);
				MainPanel.showSelectHero();
			}
        };

		ActionListener debugButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				SelectHeroPanel.setupSelectHeroPanel(true);
				MainPanel.showSelectHero();
			}
        };

		btnNewGame.addActionListener(newGameButtonListener);
		btnDebug.addActionListener(debugButtonListener);
		btnExit.addActionListener(e -> System.exit(0));
	}

	public static JPanel getPanel() { return pMenu; }
}

