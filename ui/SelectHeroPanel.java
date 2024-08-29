package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.Settings;
import ui.GamePanel;

public class SelectHeroPanel {
    private static Font f = Settings.FONT;
    private static JPanel pSelectHero = new JPanel(new BorderLayout());
    private static JPanel upperPanel = new JPanel();
    private static JPanel middlePanel = new JPanel();
    private static JPanel lowerPanel = new JPanel();
    private static JLabel lblName = new JLabel("Nome do Herói:");
    private static JTextField txtName = new JTextField(20);
    private static JButton btnWarrior = new JButton();
    private static JButton btnPaladin = new JButton();
    private static JButton btnBarbarian = new JButton();
    private static JButton btnHealth = new JButton("Distribuir em Saúde");
    private static JButton btnDefense = new JButton("Distribuir em Defesa");
    private static JButton btnAttack = new JButton("Distribuir em Ataque");
    private static JLabel lblPointsRemaining = new JLabel();
    private static int totalPointsToDistribute = Settings.X;
    private static int pointsInHealth;
    private static int pointsInAttack;
    private static int pointsInDefense;
    private static int[] pointsToDistribute;
    private static String heroClass;
    private static String heroName;
	private static boolean debugIsOn;

    public static void setupSelectHeroPanel(boolean debug) {
        debugIsOn = debug;

		setupUpperPanel();
        setupMiddlePanel();
        setupLowerPanel();

        pSelectHero.setBackground(Color.white);
        pSelectHero.add(upperPanel, BorderLayout.NORTH);
        pSelectHero.add(middlePanel, BorderLayout.CENTER);
        pSelectHero.add(lowerPanel, BorderLayout.SOUTH);

        setupButtonListeners();
    }

    private static void setupUpperPanel() {
        lblName.setFont(f);
        txtName.setFont(f.deriveFont(24f));

        upperPanel.setBackground(Color.white);
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        upperPanel.add(lblName);
        upperPanel.add(Box.createVerticalStrut(10));
        upperPanel.add(txtName);
        upperPanel.setPreferredSize(new Dimension(pSelectHero.getWidth(), pSelectHero.getHeight() / 5));
    }

    private static void setupMiddlePanel() {
        Image warriorImage = Settings.SPRITE_WARRIOR.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        Image paladinImage = Settings.SPRITE_PALADIN.getScaledInstance(200, 400, Image.SCALE_SMOOTH);
        Image barbarianImage = Settings.SPRITE_BARBARIAN.getScaledInstance(200, 400, Image.SCALE_SMOOTH);

        btnWarrior.setIcon(new ImageIcon(warriorImage));
        btnPaladin.setIcon(new ImageIcon(paladinImage));
        btnBarbarian.setIcon(new ImageIcon(barbarianImage));

        btnWarrior.setFont(f);
        btnPaladin.setFont(f);
        btnBarbarian.setFont(f);

        middlePanel.setLayout(new GridLayout(1, 3, 10, 0));
        middlePanel.setBackground(Color.white);
        middlePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        middlePanel.add(btnWarrior);
        middlePanel.add(btnPaladin);
        middlePanel.add(btnBarbarian);
        middlePanel.setPreferredSize(new Dimension(pSelectHero.getWidth(), pSelectHero.getHeight() * 3 / 5));
    }

    private static void setupLowerPanel() {
        updateStatButtons();
        updatePointsRemainingLabel();

        lblPointsRemaining.setFont(f.deriveFont(18f));
        lblPointsRemaining.setHorizontalAlignment(JLabel.CENTER);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(Color.white);
        labelPanel.add(lblPointsRemaining, BorderLayout.CENTER);

        Dimension buttonSize = new Dimension(150, 50);
        btnHealth.setPreferredSize(buttonSize);
        btnDefense.setPreferredSize(buttonSize);
        btnAttack.setPreferredSize(buttonSize);

        btnHealth.setFont(f);
        btnDefense.setFont(f);
        btnAttack.setFont(f);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(btnAttack);
        buttonPanel.add(btnDefense);
        buttonPanel.add(btnHealth);

        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.add(labelPanel, BorderLayout.NORTH);
        lowerPanel.add(buttonPanel, BorderLayout.CENTER);
        lowerPanel.setPreferredSize(new Dimension(pSelectHero.getWidth(), pSelectHero.getHeight() / 5));

        btnHealth.addActionListener(e -> distributePoints("health"));
        btnDefense.addActionListener(e -> distributePoints("defense"));
        btnAttack.addActionListener(e -> distributePoints("attack"));
    }

    private static void distributePoints(String stat) {
        if (totalPointsToDistribute > 0) {
            switch (stat) {
                case "health" -> pointsInHealth++;
                case "defense" -> pointsInDefense++;
                case "attack" -> pointsInAttack++;
            }
            totalPointsToDistribute--;
            updateStatButtons();
            updatePointsRemainingLabel();
        }
    }

    private static void updateStatButtons() {
        btnHealth.setText("Saúde " + pointsInHealth);
        btnDefense.setText("Defesa " + pointsInDefense);
        btnAttack.setText("Ataque " + pointsInAttack);
    }

    private static void updatePointsRemainingLabel() {
        lblPointsRemaining.setText("Pontos restantes: " + totalPointsToDistribute);
    }

    private static void setupButtonListeners() {
        ActionListener heroSelectionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();

                if (sourceButton == btnWarrior) {
                    heroClass = "HERO_WARRIOR";
                } else if (sourceButton == btnPaladin) {
                    heroClass = "HERO_PALADIN";
                } else if (sourceButton == btnBarbarian) {
                    heroClass = "HERO_BARBARIAN";
                }

                if (!txtName.getText().trim().isEmpty()) {
                    heroName = txtName.getText().trim();
                    startGame();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Nome inválido",
                            "Nome Inválido",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        btnWarrior.addActionListener(heroSelectionListener);
        btnPaladin.addActionListener(heroSelectionListener);
        btnBarbarian.addActionListener(heroSelectionListener);
    }

    private static void startGame() {
        MainPanel.showGame();
        String heroClass = SelectHeroPanel.getHeroClass();
        String heroName = SelectHeroPanel.getHeroName();
        int[] pointsToDistribute = SelectHeroPanel.getPointsToDistribute();

        GamePanel.initializeGame(heroClass, heroName, debugIsOn, pointsToDistribute);
    }

    public static String getHeroClass() {
        return heroClass;
    }

    public static String getHeroName() {
        return heroName;
    }

    public static int[] getPointsToDistribute() {
        int[] p = {pointsInHealth, pointsInAttack, pointsInDefense};
        return p;
    }

    public static JPanel getPanel() {
        return pSelectHero;
    }
}

