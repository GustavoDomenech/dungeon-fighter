package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Dimension;
import java.awt.CardLayout;

public class SelectHeroPanel {
    private static JPanel pSelectHero = new JPanel(new BorderLayout());
    private static JPanel topPanel = new JPanel(new BorderLayout());
    private static JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 linhas, 10px de padding vertical e horizontal
    private static JTextField txtHeroName = new JTextField("Digite o nome do personagem");
    private static JButton btnWarrior = new JButton("Guerreiro");
    private static JButton btnPaladin = new JButton("Paladino");
    private static JButton btnBarbarian = new JButton("Bárbaro");

    static {
        Font f = null;
        
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, Files.newInputStream(Paths.get("assets/fonts/FiraCodeNerdFont-Bold.ttf")));
            f = f.deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        /* ************************************************************************************
         * *                          PAINEL SUPERIOR                                         *
         * ************************************************************************************/
        topPanel.setPreferredSize(new Dimension(200, 50)); // 1/8 da altura total
        txtHeroName.setFont(f.deriveFont(16f));
        topPanel.add(txtHeroName, BorderLayout.CENTER);
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding interno

        /* ************************************************************************************
         * *                          PAINEL DE BOTÕES (INFERIOR)                              *
         * ************************************************************************************/
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding interno
        
        btnWarrior.setFont(f);
        btnPaladin.setFont(f);
        btnBarbarian.setFont(f);

        btnWarrior.setBackground(Color.WHITE);
        btnPaladin.setBackground(Color.WHITE);
        btnBarbarian.setBackground(Color.WHITE);

        buttonPanel.add(btnWarrior);
        buttonPanel.add(btnPaladin);
        buttonPanel.add(btnBarbarian);

        /* ************************************************************************************
         * *                          PAINEL PRINCIPAL                                        *
         * ************************************************************************************/
        pSelectHero.setPreferredSize(new Dimension(200, 400));
        pSelectHero.setBackground(Color.LIGHT_GRAY); // Cor de fundo do painel principal
        pSelectHero.add(topPanel, BorderLayout.NORTH);
        pSelectHero.add(buttonPanel, BorderLayout.CENTER);
    }

    public static JPanel getPanel() {
        return pSelectHero;
    }

    public static void show() {
        JPanel pMain = MainPanel.getPanel();
        pMain.add(pSelectHero, "SelectHero");
        CardLayout cl = (CardLayout) pMain.getLayout();
        cl.show(pMain, "SelectHero");
    }
}

