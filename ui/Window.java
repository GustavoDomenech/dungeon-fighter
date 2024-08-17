package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicToolTipUI;
import javax.swing.AbstractButton;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import config.Settings;

public class Window {
	/* window geometry */
	private static int w = Settings.WINDOW_WIDTH;
	private static int h = Settings.WINDOW_HEIGHT;
	/* main panel */
	private static CardLayout clMain = MainPanel.getCardLayout();
	private static JPanel pMain = MainPanel.getPanel();
	/* menu panel */
	private static JPanel pMenu = MenuPanel.getPanel();
	private static JButton btnNewGame = MenuPanel.getBtn("Jogar");
	private static JButton btnDebug = MenuPanel.getBtn("DEBUG");
	private static JButton btnExit = MenuPanel.getBtn("Sair");
	/* game panel */
	private static JPanel pGame = GamePanel.getPanel();

    public static void init() {
        JFrame f = new JFrame("Dungeon Fighter");
		/* main */
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clMain.show(pMain, "Menu");
        f.add(pMain);
		f.pack();
        f.setVisible(true);
		/* button action listeners */
		btnNewGame.addActionListener(e -> clMain.show(pMain, "Game"));
		btnExit.addActionListener(e -> System.exit(0));
    }
}

