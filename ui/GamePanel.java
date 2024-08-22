package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GamePanel {
    private static JPanel pGame = new JPanel(new BorderLayout());
    private static JPanel leftPanel = new JPanel(new BorderLayout());
    private static JPanel boardPanel = new JPanel(new GridLayout(5, 10));
    private static JPanel paddedBoardPanel = new JPanel(new BorderLayout());
    private static JPanel labelPanel = new JPanel();
    private static JPanel buttonPanel = new JPanel();
    private static JButton btnMoveHero = new JButton("Movimentar herói");
    private static JButton btnHint = new JButton("Dica");
    private static JButton btnBack = new JButton("Sair");
    private static JLabel lblHealth = new JLabel("  100");
    private static JLabel lblAttack = new JLabel("󰓥 60");
    private static JLabel lblDefense = new JLabel("󰒘 30");
	private static JLabel lblElixir = new JLabel(" 3");

    static {
        Font f = null;
        
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, Files.newInputStream(Paths.get("assets/fonts/FiraCodeNerdFont-Bold.ttf")));
            f = f.deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        /* ************************************************************************************
         * *                          PAINEL DOS STATS DO HERÓI                               *
         * ************************************************************************************/
        /* fonte dos textos */
        lblHealth.setFont(f.deriveFont(48f));
        lblAttack.setFont(f.deriveFont(36f));
        lblDefense.setFont(f.deriveFont(36f));
		lblElixir.setFont(f.deriveFont(36f));
        /* cor do plano de fundo */
        labelPanel.setBackground(Color.WHITE);
        /* layout */
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		labelPanel.add(Box.createVerticalStrut(80));
        labelPanel.add(lblHealth);
        labelPanel.add(lblAttack);
        labelPanel.add(lblDefense);
		labelPanel.add(lblElixir);
        /* *************************************************************************************
         * *                          PAINEL DE BOTÕES (INFERIOR)                              *
         * ************************************************************************************/
        /* fonte dos textos */
        btnMoveHero.setFont(f);
        btnHint.setFont(f);
        btnBack.setFont(f);
        /* layout */
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        /* alinhamento centralizado  */
        btnMoveHero.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnHint.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(JButton.CENTER_ALIGNMENT);
        /* máximo tamanho horizontal possível */
        btnMoveHero.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnMoveHero.getMinimumSize().height));
        btnHint.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHint.getMinimumSize().height));
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnBack.getMinimumSize().height));
        /* cria o espaço em branco entre o painel superior e o inferior */
        buttonPanel.add(Box.createVerticalGlue());         // espaço antes dos botões
        buttonPanel.add(btnMoveHero);            
        buttonPanel.add(Box.createVerticalStrut(10));    // espaço entre botões
        buttonPanel.add(btnHint);
        buttonPanel.add(Box.createVerticalStrut(10));    // espaço entre botões
        buttonPanel.add(btnBack);
        buttonPanel.add(Box.createVerticalGlue());         // espaço depois dos botões
        /* ****************************************************************************************
         * *                          PAINEL A ESQUERDA                                           *
         * ****************************************************************************************/
        /* cor do plano de fundo do painel */
        leftPanel.setBackground(Color.WHITE);
        /* tamanho do painel a esquerda */
        leftPanel.setPreferredSize(new Dimension(250, leftPanel.getPreferredSize().height));
		/* espaço em branco a esquerda */
        leftPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        /* adiciona o painel de stats do jogador na parte superior do painel à esquerda */
        leftPanel.add(labelPanel, BorderLayout.NORTH);
        /* adiciona o painel de botões na parte inferior do painel à direita */
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        /* ****************************************************************************************
         * *                          PAINEL PRINCIPAL                                            *
         * ****************************************************************************************/
        /* cor do plano de fundo */
        pGame.setBackground(Color.WHITE);
        /* adiciona o painel a esquerda ao painel principal do jogo */
        pGame.add(leftPanel, BorderLayout.WEST);
        /* ****************************************************************************************
         * *                          PAINEL DO TABULEIRO                                         *
         * ****************************************************************************************/
        /* cor do plano de fundo */
        boardPanel.setBackground(Color.WHITE);
        paddedBoardPanel.setBackground(Color.WHITE);
        /* adiciona uma borda pro painel onde fica o tabuleiro */
        paddedBoardPanel.setBorder(new EmptyBorder(80, 20, 80, 80));
        /* adiciona o painel com padding ao painel onde fica o tabuleiro */
        paddedBoardPanel.add(boardPanel, BorderLayout.CENTER);
        /* adiciona as células do tabuleiro ao painel com padding */
        for (int i = 0; i < 50; i++) {
            JButton boardButton = new JButton();
            boardButton.setFont(f);
            if ((i / 10 + i % 10) % 2 == 0) {
                boardButton.setBackground(Color.WHITE);
            } else {
                boardButton.setBackground(Color.BLACK);
                boardButton.setForeground(Color.WHITE);
            }
            boardPanel.add(boardButton);
        }
        /* adiciona o painel com padding ao painel principal do jogo */
        pGame.add(paddedBoardPanel, BorderLayout.CENTER);
    }

    public static JPanel getPanel() { return pGame; }

    public static JButton getBtn(String btnName) {
        switch (btnName) {
            case "Sair":
                return btnBack;
            case "Movimentar herói":
                return btnMoveHero;
            case "Dica":
                return btnHint;
            default:
                return null;
        }
    }
}

