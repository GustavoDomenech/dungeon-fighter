package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import config.Settings;

public class SelectHeroPanel {
    private static Font f = Settings.FONT;
    private static JPanel pSelectHero = new JPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS));
    private static JPanel topPanel = new JPanel();
    private static JPanel bottomPanel = new JPanel();
    private static JTextField txtName = new JTextField(20);
    private static JButton btnWarrior = new JButton("Guerreiro");
    private static JButton btnPaladin = new JButton("Paladino");
    private static JButton btnBarbarian = new JButton("Barbaro");
    private static JLabel lblName = new JLabel("Nome do Herói:");
    
    public static JPanel getPanel() { return pSelectHero; }

    public static void setupSelectHeroPanel() {
        setupTopPanel();
        setupBottomPanel();
        
        pSelectHero.setBackground(Color.white);
        pSelectHero.add(topPanel);
        pSelectHero.add(bottomPanel);
        
        setupButtonListeners();
    }

    private static void setupTopPanel() {
        lblName.setFont(f);
        txtName.setFont(f);
        
        topPanel.setBackground(Color.white);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.add(lblName);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(txtName);
    }

    private static void setupBottomPanel() {
        btnWarrior.setFont(f);
        btnPaladin.setFont(f);
        btnBarbarian.setFont(f);
        
        btnWarrior.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnWarrior.getMinimumSize().height));
        btnPaladin.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnPaladin.getMinimumSize().height));
        btnBarbarian.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnBarbarian.getMinimumSize().height));
        
        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        bottomPanel.add(btnWarrior);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(btnPaladin);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(btnBarbarian);
    }

    private static void setupButtonListeners() {
        ActionListener heroSelectionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                String heroClass = "";

                if (sourceButton == btnWarrior) {
                    heroClass = "HERO_WARRIOR";
                } else if (sourceButton == btnPaladin) {
                    heroClass = "HERO_PALADIN";
                } else if (sourceButton == btnBarbarian) {
                    heroClass = "HERO_BARBARIAN";
                }

                if (!txtName.getText().trim().isEmpty()) {
                    NewGame.setHero(new Hero(heroClass, txtName.getText().trim()));
                    MainPanel.showGamePanel();
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Por favor, insira um nome para o herói.", 
                        "Nome Inválido", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        btnWarrior.addActionListener(heroSelectionListener);
        btnPaladin.addActionListener(heroSelectionListener);
        btnBarbarian.addActionListener(heroSelectionListener);
    }
}

