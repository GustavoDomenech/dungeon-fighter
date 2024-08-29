package ui;

import javax.swing.JFrame;

import config.Settings;

public class Window {
    private static JFrame f = new JFrame("Dungeon Fighter");
    
    public static void init() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(MainPanel.getCardLayout());
        f.add(MainPanel.getPanel());
        
		MainPanel.showMenu();
		
		f.pack();
        f.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

