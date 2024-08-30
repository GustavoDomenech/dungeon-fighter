package ui;

import javax.swing.JFrame;

import config.Settings;

public class Window {
    private static JFrame f = new JFrame("Dungeon Fighter");
    
    public static void init() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* CardLayout contem os paineis do jogo */
        f.setLayout(MainPanel.getCardLayout());
		/* adiciona o MainPanel ao frame */
        f.add(MainPanel.getPanel());
		/* menu como painel inicial */
        MainPanel.showMenu();
        /* geometria da janela etc */
		f.pack();
        f.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

