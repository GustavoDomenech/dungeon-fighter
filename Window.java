import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Window {
    private static final int WINDOW_WIDTH = 1080;
    private static final int WINDOW_HEIGHT = 720;

    public static void init() {
        JFrame frame = new JFrame("Minha Primeira Janela com Fundo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
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
                    g.drawImage(backgroundImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                if (backgroundImage != null) {
                    return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
                } else {
                    return super.getPreferredSize();
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        JButton btnNewGame = new JButton("Jogar");
        JButton btnDebug = new JButton("DEBUG");
        JButton btnExit = new JButton("Sair");

        panel.add(btnNewGame, gbc);
        panel.add(btnDebug, gbc);
        panel.add(btnExit, gbc);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

