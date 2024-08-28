package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Hero;
import game.Monster;
import ui.MainPanel;
import ui.GamePanel;
import config.Settings;

public class BattlePanel {
    private static Font font = Settings.FONT;
    private static JPanel pBattle = new JPanel(new BorderLayout());
    private static JPanel leftPanel = new JPanel();
    private static JPanel upperPanel = new JPanel(new BorderLayout());
    private static JPanel lowerPanel = new JPanel(new BorderLayout());
    private static JPanel infoPanel = new JPanel();
    private static JPanel actionPanel = new JPanel();
    private static JButton btnAttack = new JButton("Atacar");
    private static JButton btnUseElixir = new JButton("Ingerir Elixir");
    private static JButton btnSpecialAbility = new JButton("Usar Habilidade Especial");
    private static JButton btnEscape = new JButton("Fugir da Batalha");
    private static JLabel lblHero = new JLabel();
    private static JLabel lblMonster = new JLabel();
	private static JLabel lblBoss = new JLabel();
    private static JLabel lblBattleStatus = new JLabel();
    private static JLabel lblElixirs = new JLabel();
    private static Hero h;
    private static Monster m;

    public static void initializeBattle(Hero hero, Monster monster) {
        h = hero;
        m = monster;
        setupBattlePanel();
    }

    private static void setupBattlePanel() {
        setupUpperPanel();
        setupInfoPanel();
        setupActionPanel();
        setupLeftPanel();
        setupLowerPanel();
        
        pBattle.setBackground(Color.white);
        pBattle.add(leftPanel, BorderLayout.WEST);
        pBattle.add(upperPanel, BorderLayout.CENTER);
        pBattle.add(lowerPanel, BorderLayout.SOUTH);
        
        lowerPanel.add(infoPanel, BorderLayout.CENTER);
        lowerPanel.add(actionPanel, BorderLayout.EAST);

        setupButtonListeners();
        updateElixirButtonState();
    }

    private static void setupUpperPanel() {
		JLabel lblHeroSprite = new JLabel(new ImageIcon(
					GamePanel.getSpriteForKey(h.getHeroClass()).getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		JLabel lblVersus = new JLabel(new ImageIcon(
					Settings.VERSUS.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		JLabel lblMonster = new JLabel(new ImageIcon(
				GamePanel.getSpriteForKey("MONSTER_" + 
				m.getMonsterType()).getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		JLabel lblBoss = new JLabel(new ImageIcon(
				GamePanel.getSpriteForKey("BOSS").getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		
        JPanel spritesPanel = new JPanel();
		
		System.out.println(m.getMonsterType());
		
        spritesPanel.setLayout(new BoxLayout(spritesPanel, BoxLayout.X_AXIS));
        spritesPanel.add(Box.createHorizontalGlue());
        spritesPanel.add(lblHeroSprite);
        spritesPanel.add(Box.createHorizontalStrut(10));
        spritesPanel.add(lblVersus);
        spritesPanel.add(Box.createHorizontalStrut(10));
		spritesPanel.add(lblMonster);
        spritesPanel.add(Box.createHorizontalGlue());
        
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(spritesPanel, BorderLayout.CENTER);
    }

    private static void setupInfoPanel() {
        lblHero.setText("Heroi: " + h.getName() + " - Saude: " + h.getHealthPoints());
        lblHero.setFont(font.deriveFont(18f));
        lblMonster.setText("Monstro: " + m.getName() + " - Saude: " + m.getHealthPoints());
        lblMonster.setFont(font.deriveFont(18f));
        lblBattleStatus.setText("Prepare-se para a batalha");
        lblBattleStatus.setFont(font.deriveFont(18f));
        lblElixirs.setText("Elixires: " + h.getNumberOfElixirs());
        lblElixirs.setFont(font.deriveFont(18f));

        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(lblHero);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblMonster);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblBattleStatus);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblElixirs);
    }

    private static void setupActionPanel() {
        btnAttack.setFont(font);
        btnAttack.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnAttack.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnAttack.getMinimumSize().height));
        btnUseElixir.setFont(font);
        btnUseElixir.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnUseElixir.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnUseElixir.getMinimumSize().height));
        btnSpecialAbility.setFont(font);
        btnSpecialAbility.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnSpecialAbility.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnSpecialAbility.getMinimumSize().height));
        btnEscape.setFont(font);
        btnEscape.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnEscape.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnEscape.getMinimumSize().height));

        actionPanel.setBackground(Color.WHITE);
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setPreferredSize(new Dimension(400, actionPanel.getPreferredSize().height));

        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(btnAttack);
        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(btnUseElixir);
        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(btnSpecialAbility);
        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(btnEscape);
    }

    private static void setupLeftPanel() {
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(actionPanel, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(200, pBattle.getHeight()));
    }

    private static void setupLowerPanel() {
        infoPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        lowerPanel.setBackground(Color.WHITE);
        lowerPanel.setLayout(new BorderLayout());
    }

    private static void setupButtonListeners() {
        ActionListener attackButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (m.getRoundsPassed()) {
                    case 1:
                        m.increaseRoundsPassed();
                        break;
                    case 2:
                        h.useSpecialAbility(m, true);
                        break;
                    default:
                        break;
                }

                int heroAttack = h.getAttackPoints() + ThreadLocalRandom.current().nextInt(0, Settings.W);
                int monsterDefense = m.getDefensePoints() + ThreadLocalRandom.current().nextInt(0, Settings.W);
                int damage = heroAttack - monsterDefense;

                if (damage > 0) {
                    int damageGiven = m.takeDamage(damage);
                    lblBattleStatus.setText(h.getName() + " deu " + damageGiven + " de dano em " + m.getName());
                } else {
                    int damageTaken = h.takeDamage(-damage);
                    lblBattleStatus.setText(m.getName() + " deu " + damageTaken + " de dano em " + h.getName());
                }
                lblMonster.setText("Monstro: " + m.getName() + " - Saude: " + m.getHealthPoints());
                
				if (m.getHealthPoints() <= 0) {
                    if (m.isBoss()) {
                        JOptionPane.showMessageDialog(pBattle, "Você venceu!");
						GamePanel.labelPanel.removeAll();
						GamePanel.labelPanel.repaint();
						GamePanel.boardPanel.removeAll();
						GamePanel.boardPanel.repaint();
                        MainPanel.showMenu();
                    } else {
                        JOptionPane.showMessageDialog(pBattle, "Você venceu!");
						m.wasKilled();
						GamePanel.labelPanel.removeAll();
						GamePanel.labelPanel.repaint();
						GamePanel.boardPanel.removeAll();
						GamePanel.boardPanel.repaint();
						GamePanel.setupGamePanel();
						MainPanel.showGame();
                        h.earnRandomStatPoints();
                    }
                } else {
					lblBattleStatus.setText(m.getName() + " está se preparando para atacar.. ");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    int monsterAttack = m.getAttackPoints() + ThreadLocalRandom.current().nextInt(0, Settings.W);
                    int heroDefense = h.getDefensePoints() + ThreadLocalRandom.current().nextInt(0, Settings.W);
                    damage = monsterAttack - heroDefense;

                    if (damage > 0) {
                        int damageGiven = h.takeDamage(damage);
                        lblBattleStatus.setText(m.getName() + " deu " + damageGiven + " de dano em " + h.getName());
                    } else {
                        int damageTaken = m.takeDamage(-damage);
                        lblBattleStatus.setText(h.getName() + " deu " + damageTaken + " de dano em " + m.getName());
                    }
                    lblHero.setText("Heroi: " + h.getName() + " - Saude: " + h.getHealthPoints());

                    if (h.getHealthPoints() <= 0) {
						GamePanel.labelPanel.removeAll();
						GamePanel.labelPanel.repaint();
						GamePanel.boardPanel.removeAll();
						GamePanel.boardPanel.repaint();
                        JOptionPane.showMessageDialog(pBattle, "GAME OVER");
                        MainPanel.showMenu();
                    }
                }
            }
        };

        ActionListener useElixirButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (h.getNumberOfElixirs() > 0) {
                    int healAmount = h.useElixir();
                    lblHero.setText("Heroi: " + h.getName() + " - Saude: " + h.getHealthPoints());
                    lblElixirs.setText("Elixires: " + h.getNumberOfElixirs());
                    lblBattleStatus.setText(h.getName() + " usou um elixir e restaurou " + healAmount + " de saude");
                    updateElixirButtonState();
                }
            }
        };

        ActionListener specialAbilityButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String abilityMessage = "";
                int specialDamage = 0;

                switch (h.getHeroClass()) {
                    case "HERO_WARRIOR":
                        int increasedDefensePoints = h.useSpecialAbility(m, false);
                        abilityMessage = h.getName() + " aumentou sua defesa em " + increasedDefensePoints + " pontos";
                        m.increaseRoundsPassed();
                        m.toggleUsedSpecialAbilityOn();
                        break;
                    case "HERO_PALADIN":
                        abilityMessage = h.getName() + " recuperou " + h.useSpecialAbility(m, true) + " de saude";
                        break;
                    case "HERO_BARBARIAN":
                        specialDamage = h.useSpecialAbility(m, false);
                        abilityMessage = h.getName() + " causou " + specialDamage + " de dano extra";
                        break;
                    default:
                        break;
                }

                lblBattleStatus.setText(abilityMessage);
                lblHero.setText("Heroi: " + h.getName() + " - Saude: " + h.getHealthPoints());
                lblMonster.setText("Monstro: " + m.getName() + " - Saude: " + m.getHealthPoints());
                if (specialDamage > 0) {
                    int damageGiven = m.takeDamage(specialDamage);
                    lblBattleStatus.setText(h.getName() + " causou " + damageGiven + " de dano em " + m.getName());
                }
                if (m.getHealthPoints() <= 0) {
                    if (m.isBoss()) {
                        JOptionPane.showMessageDialog(pBattle, "Você venceu!");
						GamePanel.labelPanel.removeAll();
						GamePanel.labelPanel.repaint();
						GamePanel.boardPanel.removeAll();
						GamePanel.boardPanel.repaint();
						GamePanel.setupGamePanel();
                        MainPanel.showMenu();
                    } else {
                        JOptionPane.showMessageDialog(pBattle, "Você venceu!");
                        m.wasKilled();
						GamePanel.labelPanel.removeAll();
						GamePanel.labelPanel.repaint();
						GamePanel.boardPanel.removeAll();
						GamePanel.setupGamePanel();
						GamePanel.boardPanel.repaint();
                        h.earnRandomStatPoints();
                        MainPanel.showGame();
                    }
                }
            }
        };

        ActionListener escapeButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				GamePanel.labelPanel.removeAll();
				GamePanel.labelPanel.repaint();
				GamePanel.boardPanel.removeAll();
				GamePanel.boardPanel.repaint();
				GamePanel.setupGamePanel();
                MainPanel.showGame();
            }
        };

        btnAttack.addActionListener(attackButtonListener);
        btnUseElixir.addActionListener(useElixirButtonListener);
        btnSpecialAbility.addActionListener(specialAbilityButtonListener);
        btnEscape.addActionListener(escapeButtonListener);
    }

    private static void updateElixirButtonState() {
        btnUseElixir.setEnabled(h.getNumberOfElixirs() > 0);
    }

    public static JPanel getPanel() { return pBattle; }
}
