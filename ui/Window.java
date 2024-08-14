package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import config.Settings;

public class Window {
	private static int w = Settings.WINDOW_WIDTH;
	private static int h = Settings.WINDOW_HEIGHT;

    public static void init() {
		/* Frame */
        JFrame f = new JFrame("Dungeon Fighter");
		/* Main Panel*/	
        CardLayout cardLayout = new CardLayout();
        JPanel pMain = new JPanel(cardLayout);
		/* Menu Panel */
        JPanel pMenu = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(new File("assets/background.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			// entender o override
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, w, h, this);
                }
            }
			// entender o override
            @Override
            public Dimension getPreferredSize() {
				/* Se a imagem de fundo nao carregar por algum motivo
				 * seta o tamanho de janela da função pai */
                if (backgroundImage != null) {
                    return new Dimension(w, h);
                } else {
                    return super.getPreferredSize();
                }
            }
        };
		/* Menu Buttons */
		JButton btnNewGame = new JButton("Jogar");
		JButton btnDebug = new JButton("DEBUG");
		JButton btnExit = new JButton("Sair");
		/* Game Panel */
		JPanel pGame = new JPanel();
        pGame.setBackground(Color.BLACK);
        pGame.add(new JLabel("Game Screen"));
		/* Game Buttons*/
		JButton btnBack = new JButton("Back to Main Menu");
		/* "main" da função init daq pra baixo*/
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// entender como isso funciona (é um layout de botoes)
        pMenu.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
		// Add buttons to menu panel
		pMenu.add(btnNewGame, gbc);
		pMenu.add(btnDebug, gbc);
		pMenu.add(btnExit, gbc);
		// Add buttons to game panel
		/* NAO FUNCIONA */
		//pGame.add(btnBack, gbc);
		// Add menu and game panels to the CardLayout-managed panel
        pMain.add(pMenu, "Menu");
        pMain.add(pGame, "Game");
		/* NAO FUNCIONA */
		//pGame.add(pMain, "Main");
		// Set initial display to the menu panel
		cardLayout.show(pMain, "Menu");
		// Add main panel to the frame
        f.add(pMain);
		f.pack();
        f.setVisible(true);
		// Button action listeners
		btnNewGame.addActionListener(e -> cardLayout.show(pMain, "Game"));
		btnExit.addActionListener(e -> System.exit(0));
		/* NAO FUNCIONA */
		//btnBack.addActionListener(e -> cardLayout.show(pGame, "Main"));
    }
}

