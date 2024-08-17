package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicToolTipUI;
import javax.swing.AbstractButton;
import java.awt.Window;
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

public class MainPanel {
	private static CardLayout clMain = new CardLayout();
	private static JPanel pMain = new JPanel(clMain);
	
	static {
        // Add menu and game panels to the CardLayout-managed panel
        pMain.add(MenuPanel.getPanel(), "Menu");
        pMain.add(GamePanel.getPanel(), "Game");
    }

	public static CardLayout getCardLayout() {
        return clMain;
    }

    public static JPanel getPanel() {
        return pMain;
    }
}
