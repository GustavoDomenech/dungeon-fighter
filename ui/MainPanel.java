package ui;

import javax.swing.JPanel;
import java.awt.CardLayout;

import ui.GamePanel;

public class MainPanel {
    private static CardLayout clMain = new CardLayout();
    private static JPanel pMain = new JPanel(clMain);

    static {
        pMain.add(MenuPanel.getPanel(), "Menu");
        pMain.add(GamePanel.getPanel(), "Game");
    }

    public static CardLayout getCardLayout() { return clMain; }

    public static JPanel getPanel() { return pMain; }

    public static void showMenu() { clMain.show(pMain, "Menu"); }

    public static void showGame() { clMain.show(pMain, "Game"); }
}

