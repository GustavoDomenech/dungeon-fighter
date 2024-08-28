package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import game.NewGame;
import game.Hero;
import game.Trap;
import game.Monster;
import ui.MenuPanel;
import ui.BattlePanel;
import config.Settings;

public class GamePanel {
    private static Font f = Settings.FONT;
	private static boolean debugIsOn;
    private static JPanel pGame = new JPanel(new BorderLayout());
    private static JPanel leftPanel = new JPanel(new BorderLayout());
    public static JPanel boardPanel = new JPanel(new GridLayout(5, 10));
    private static JPanel paddedBoardPanel = new JPanel(new BorderLayout());
    public static JPanel labelPanel = new JPanel();
    private static JPanel buttonPanel = new JPanel();
    private static JToggleButton btnMoveHero = new JToggleButton("Movimentar herói");
    private static JButton btnHint = new JButton("Dica");
    private static JButton btnBack = new JButton("Sair");
    private static JLabel lblHealth = new JLabel();
    private static JLabel lblAttack = new JLabel();
    private static JLabel lblDefense = new JLabel();
    private static JLabel lblElixir = new JLabel();
    private static HashMap<String, JButton> boardButtons = new HashMap<>();
    private static HashMap<String, String> actorPositions;
	private static NewGame ng;
    private static Hero h;
	private static Monster m1;
	private static Monster m2;
	private static Monster m3;
	private static Monster m4;
	private static Monster m5;
	private static Monster b;
	private static int hintCounter = 0;
	private static String heroKey;
	private static String[] monsterNames = Settings.MONSTER_NAMES;

	public static void initializeGame() {
		debugIsOn = MenuPanel.debugIsOn;
		/* TODO a heroKey tem q vir do painel SelectHero */
		/* TODO cópia do HashMap das posições pro menu "Reiniciar" ou "Novo Jogo"*/
		heroKey = "HERO_WARRIOR";
        ng = new NewGame("HERO_WARRIOR", "Aragorn");
        h = new Hero("HERO_WARRIOR", "Aragorn");
		m1 = new Monster(monsterNames[0], "1");
		m2 = new Monster(monsterNames[1], "2");
		m3 = new Monster(monsterNames[2], "3");
		m4 = new Monster(monsterNames[3], "4");
		b = new Monster(Settings.BOSS_NAME, "BOSS");
        actorPositions = ng.getActorPositions();
        setupGamePanel();
    }

    public static void setupGamePanel() {
        setupLabelPanel();
        setupButtonPanel();
        setupLeftPanel();
        setupBoardPanel();
        pGame.setBackground(Color.white);
        pGame.add(leftPanel, BorderLayout.WEST);
        pGame.add(paddedBoardPanel, BorderLayout.CENTER);
        setupButtonListeners();
    }

    private static void setupLabelPanel() {
        /* definição e formatação do texto */
		lblHealth.setText(" " + h.getHealthPoints() + "/" + Settings.HERO_HEALTH_POINTS);
        lblHealth.setFont(f.deriveFont(48f));
        lblAttack.setText("󰓥 " + h.getAttackPoints());
        lblAttack.setFont(f.deriveFont(36f));
        lblDefense.setText("󰒘 " + h.getDefensePoints());
        lblDefense.setFont(f.deriveFont(36f));
        lblElixir.setText(" " + h.getNumberOfElixirs());
        lblElixir.setFont(f.deriveFont(36f));
		/* layout do painel */
        labelPanel.setBackground(Color.WHITE);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(Box.createVerticalStrut(80));
        labelPanel.add(lblHealth);
        labelPanel.add(lblAttack);
        labelPanel.add(lblDefense);
        labelPanel.add(lblElixir);
    }

    private static void setupButtonPanel() {
        /* formatação e alinhamento dos botões */
		btnMoveHero.setFont(f);
        btnMoveHero.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnMoveHero.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnMoveHero.getMinimumSize().height));
        btnHint.setFont(f);
        btnHint.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnHint.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHint.getMinimumSize().height));
        btnBack.setFont(f);
        btnBack.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnBack.getMinimumSize().height));
		/* layout do painel */
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(btnMoveHero);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnHint);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnBack);
        buttonPanel.add(Box.createVerticalStrut(10));
    }

    private static void setupLeftPanel() {
		/* layout do painel */
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(250, leftPanel.getPreferredSize().height));
        leftPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        leftPanel.add(labelPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void setupBoardPanel() {
        int buttonSize = 50;
		
		/* layout do painel */
        boardPanel.setBackground(Color.WHITE);
        paddedBoardPanel.setBackground(Color.WHITE);
        paddedBoardPanel.setBorder(new EmptyBorder(80, 20, 80, 80));
        paddedBoardPanel.add(boardPanel, BorderLayout.CENTER);	
		/* botões do painel */
        for (int i = 0; i < 50; i++) {
            JButton boardButton = new JButton();
            boardButton.setFont(f);
            boardButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
            if ((i / 10 + i % 10) % 2 == 0) {
                boardButton.setBackground(Color.WHITE);
            } else {
                boardButton.setBackground(Color.BLACK);
            }
            String position = (i / 10) + "," + (i % 10);
            boardButtons.put(position, boardButton);
            boardPanel.add(boardButton);
        }
		/* atualiza o painel */
        updateBoardIcons();
    }

    public static void updateBoardIcons() {
		/* remove todos os sprites */
        for (Map.Entry<String, JButton> entry : boardButtons.entrySet()) {
            entry.getValue().setIcon(null);
        }
		/* posiciona os novos sprites */
        for (Map.Entry<String, String> entry : actorPositions.entrySet()) {
            String key = entry.getKey();
            String pos = entry.getValue();
            JButton button = boardButtons.get(pos);
            Image sprite = getSpriteForKey(key).getScaledInstance(110, 110, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(sprite));
        }
    }

    public static Image getSpriteForKey(String key) {
        Image sprite = null;

        switch (key) {
            case "HERO_WARRIOR":
                sprite = Settings.SPRITE_WARRIOR;
                break;
            case "HERO_PALADIN":
                sprite = Settings.SPRITE_PALADIN;
                break;
            case "HERO_BARBARIAN":
                sprite = Settings.SPRITE_BARBARIAN;
                break;
			case "MONSTER_1":
				sprite = Settings.SPRITE_MONSTER_1;
				break;
			case "MONSTER_2":
				sprite = Settings.SPRITE_MONSTER_2;
				break;
			case "MONSTER_3":
				sprite = Settings.SPRITE_MONSTER_3;
				break;
			case "MONSTER_4":
				sprite = Settings.SPRITE_MONSTER_4;
				break;
            case "BOSS":
                sprite = Settings.SPRITE_BOSS;
                break;
            default:
				if (key.startsWith("MONSTER_INVISIBLE_1")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_MONSTER_1;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_MONSTER_1;
					}
				} else if (key.startsWith("MONSTER_INVISIBLE_2")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_MONSTER_2;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_MONSTER_2;
					}
				} else if (key.startsWith("MONSTER_INVISIBLE_3")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_MONSTER_3;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_MONSTER_3;
					}
				} else if (key.startsWith("MONSTER_INVISIBLE_4")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_MONSTER_4;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_MONSTER_4;
					}
				} else if (key.startsWith("TRAP_STATIC")) {
                    sprite = Settings.SPRITE_STATIC_TRAP;
                } else if (key.startsWith("TRAP_RANDOM")) {
                    sprite = Settings.SPRITE_RANDOM_TRAP;
                } else if (key.startsWith("TRAP_INVISIBLE_STATIC")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_STATIC_TRAP;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_TRAP;
					}
                } else if (key.startsWith("TRAP_INVISIBLE_RANDOM")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_RANDOM_TRAP;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_TRAP;
					}
                } else if (key.startsWith("ELIXIR_INVISIBLE")) {
					if (debugIsOn) {
						sprite = Settings.SPRITE_ELIXIR;
					} else {
						sprite = Settings.SPRITE_INVISIBLE_ELIXIR;
					}
				}
                break;
        }
        return sprite;
    }

    private static String getPositionFromButton(JButton button) {
        for (Map.Entry<String, JButton> entry : boardButtons.entrySet()) {
            if (entry.getValue().equals(button)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void moveHero(String heroKey, String newPosition) {
		boolean trapTriggered = false;
		boolean monsterTriggered = false;
		String trapKey = null;
		String monsterKey = null;
		String elixirKey = null;
		Trap t = null;
		Monster m = null;

		for (Map.Entry<String, String> entry : actorPositions.entrySet()) {
			String key = entry.getKey();
			String position = entry.getValue();

			if (position.equals(newPosition)) {
				if (key.startsWith("TRAP_INVISIBLE_RANDOM") || key.startsWith("TRAP_RANDOM")) {
					t = new Trap(true);
					trapTriggered = true;
					trapKey = key;
					break;
				} else if (key.startsWith("TRAP_INVISIBLE_STATIC") || key.startsWith("TRAP_STATIC")) {
					t = new Trap(false);
					trapTriggered = true;
					trapKey = key;
					break;
				} else if (key.startsWith("MONSTER_INVISIBLE_1")) {
					m = m1;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("MONSTER_INVISIBLE_2")) {
					m = m2;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("MONSTER_INVISIBLE_3")) {
					m = m3;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("MONSTER_INVISIBLE_4")) {
					m = m4;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("MONSTER_1")) {
					m = m1;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("MONSTER_2")) {
					m = m2;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("MONSTER_3")) {
					m = m3;
					monsterTriggered = true;
					monsterKey = key;
					break; 
				} else if (key.startsWith("MONSTER_4")) {
					m = m4;
					monsterTriggered = true;
					monsterKey = key;
					break;
				} else if (key.startsWith("BOSS")) {
					m = b;
					monsterTriggered = true;
					break;
				} else if (key.startsWith("ELIXIR")) {
					elixirKey = key;

					JOptionPane.showMessageDialog(null, 
						"Você encontrou um elixir!", 
						"Elixir encontrado", 
						JOptionPane.INFORMATION_MESSAGE);
	
					actorPositions.remove(elixirKey);
					h.foundElixir();
					lblElixir.setText(" " + h.getNumberOfElixirs());
					labelPanel.repaint();
					labelPanel.revalidate();
					actorPositions.put(heroKey, newPosition);
					return;
				}
			}
		}

		if (trapTriggered && t != null) {
			int damageTaken = t.attack(h);

			JOptionPane.showMessageDialog(null, 
				"Você tomou " + damageTaken + " de dano de uma armadilha!", 
				"Dano recebido por armadilha", 
				JOptionPane.INFORMATION_MESSAGE);

			actorPositions.remove(trapKey);
		}

		if (monsterTriggered) {
			BattlePanel.initializeBattle(h, m);
			MainPanel.showBattle();
		}

		lblHealth.setText(" " + h.getHealthPoints() + "/" + Settings.HERO_HEALTH_POINTS);
		labelPanel.repaint();
		labelPanel.revalidate();
		actorPositions.put(heroKey, newPosition);

		if (m.isDead()) {
			actorPositions.remove(monsterKey);
			actorPositions.put(heroKey, newPosition);
		} else {
			revealMonster(monsterKey);
		}

	}

	private static void revealTrap() {
		String keyToRemove = null;
		String keyToAdd = null;
		String positionToAdd = null;

		for (Map.Entry<String, String> entry : actorPositions.entrySet()) {
			String key = entry.getKey();
			String position = entry.getValue();
			int randomFactor = ThreadLocalRandom.current().nextInt(0, 13);

			if (key.startsWith("TRAP_INVISIBLE_STATIC") && randomFactor == 1) {
				keyToAdd = key.replace("TRAP_INVISIBLE_STATIC", "TRAP_STATIC");
				positionToAdd = position;
				keyToRemove = key;
				break;
			} else if (key.startsWith("TRAP_INVISIBLE_RANDOM")) {
				keyToAdd = key.replace("TRAP_INVISIBLE_RANDOM", "TRAP_RANDOM");
				positionToAdd = position;
				keyToRemove = key;
				break;
			}
		}

		if (keyToRemove != null) {
			actorPositions.remove(keyToRemove);
			actorPositions.put(keyToAdd, positionToAdd);
		}
	}

	private static void revealMonster(String invisibleMonsterKey) {
		String keyToRemove = null;
		String keyToAdd = null;
		String positionToAdd = null;

		for (Map.Entry<String, String> entry : actorPositions.entrySet()) {
			String key = entry.getKey();
			String position = entry.getValue();

			if (key.equals(invisibleMonsterKey)) {
				String newMonsterKey = key.replace("INVISIBLE_", "");
				keyToAdd = newMonsterKey;
				positionToAdd = position;
				keyToRemove = key;
				break;
			}
		}

		if (keyToRemove != null) {
			actorPositions.remove(keyToRemove);
			actorPositions.put(keyToAdd, positionToAdd);
		}
	}

    private static void setupButtonListeners() {
        ActionListener boardButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if (btnMoveHero.isSelected()) {
					JButton sourceButton = (JButton) e.getSource();
					String clickedPos = getPositionFromButton(sourceButton);
					String heroPos = actorPositions.get(heroKey);

					if (heroPos != null && isAdjacent(heroPos, clickedPos)) {
						moveHero(heroKey, clickedPos);
						updateBoardIcons();
					}
				}
			}
        };
		ActionListener hintButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if (hintCounter < 3) {
					revealTrap();
					updateBoardIcons();
					hintCounter++;
					if (hintCounter >= 3) {
						btnHint.setEnabled(false);
					}
				}
			}
		};
		ActionListener backButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				btnHint.setEnabled(true);
				hintCounter = 0;
				labelPanel.removeAll();
				labelPanel.repaint();
				boardPanel.removeAll();
				boardPanel.repaint();	
				MainPanel.showMenu();
			}
        };

        for (Map.Entry<String, JButton> entry : boardButtons.entrySet()) {
            entry.getValue().addActionListener(boardButtonListener);
        }
		
		btnHint.addActionListener(hintButtonListener);
		/* TODO fazer um menu de "Reiniciar" ou "Novo Jogo" */
        btnBack.addActionListener(backButtonListener);
    }

	private static boolean isAdjacent(String currentPos, String targetPos) {
        String[] current = currentPos.split(",");
        String[] target = targetPos.split(",");
        int currentX = Integer.parseInt(current[0].trim());
        int currentY = Integer.parseInt(current[1].trim());
        int targetX = Integer.parseInt(target[0].trim());
        int targetY = Integer.parseInt(target[1].trim());

        return (Math.abs(currentX - targetX) == 1 && currentY == targetY) ||
               (Math.abs(currentY - targetY) == 1 && currentX == targetX);
    }

	public static void disableHintButton() {
		btnHint.setEnabled(false);
	}

    public static JPanel getPanel() { return pGame; }
}

