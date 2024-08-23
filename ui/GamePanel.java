package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
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

import game.NewGame;
import game.Hero;
import config.Settings;

public class GamePanel {
	private static Font f = Settings.FONT;
    private static JPanel pGame = new JPanel(new BorderLayout());
    private static JPanel leftPanel = new JPanel(new BorderLayout());
    private static JPanel boardPanel = new JPanel(new GridLayout(5, 10));
    private static JPanel paddedBoardPanel = new JPanel(new BorderLayout());
    private static JPanel labelPanel = new JPanel();
    private static JPanel buttonPanel = new JPanel();
    private static JButton btnMoveHero = new JButton("Movimentar herói");
    private static JButton btnHint = new JButton("Dica");
    private static JButton btnBack = new JButton("Sair");
	private static JLabel lblHealth = new JLabel();
	private static JLabel lblAttack = new JLabel();
	private static JLabel lblDefense = new JLabel();
	private static JLabel lblElixir = new JLabel();
	private static HashMap<String, JButton> boardButtons = new HashMap<>();
	private static HashMap<String, String> actorPositions;
	private static Hero h;

    static {
		/* ***************************************************************************
		 * *							"MAIN" DA CLASSE							 *
		 * ***************************************************************************/
		NewGame ng = new NewGame("Warrior", "Aragorn");
		h = new Hero("Warrior", "Aragorn");
		actorPositions = ng.getActorPositions();
		/* setup dos paines subjacentes */
		setupLabelPanel();
        setupButtonPanel();
        setupLeftPanel();
        setupBoardPanel();
		/* plano de fundo */
        pGame.setBackground(Color.WHITE);
		/* adiciona os dois paineis ao painel do jogo */
        pGame.add(leftPanel, BorderLayout.WEST);
        pGame.add(paddedBoardPanel, BorderLayout.CENTER);
    }

    private static void setupLabelPanel() {		
		/* ***************************************************************************
		 * *			PAINEL DE STATS DO JOGADOR (SUPERIOR)						 *
		 * ***************************************************************************/
		/* texto */
		lblHealth.setText(" " + h.getHealthPoints());
 		lblAttack.setText("󰓥 " + h.getAttackPoints());
		lblDefense.setText("󰒘 " + h.getDefensePoints());
		lblElixir.setText(" " + h.getNumberOfElixirs());
		/* fonte do texto */
        lblHealth.setFont(f.deriveFont(48f));
        lblAttack.setFont(f.deriveFont(36f));
        lblDefense.setFont(f.deriveFont(36f));
        lblElixir.setFont(f.deriveFont(36f));
		/* plano de fundo do painel */
        labelPanel.setBackground(Color.WHITE);
		/* layout do painel */
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		/* padding de 80px entre a borda superior e o texto */
        labelPanel.add(Box.createVerticalStrut(80));
		/* posicionamento do texto no painel*/
        labelPanel.add(lblHealth);
        labelPanel.add(lblAttack);
        labelPanel.add(lblDefense);
        labelPanel.add(lblElixir);
    }

    private static void setupButtonPanel() {
		/* ***************************************************************************
		 * *					PAINEL DE BOTÕES (INFERIOR)						 	 *
		 * ***************************************************************************/
		/* fonte dos botões */
        btnMoveHero.setFont(f);
        btnHint.setFont(f);
        btnBack.setFont(f);
		/* plano de fundo do painel */
        buttonPanel.setBackground(Color.WHITE);
		/* layout do painel */
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		/* alinhamento a esquerda e preenchimento horizontal total dos botões */
		btnMoveHero.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnMoveHero.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnMoveHero.getMinimumSize().height));
		btnHint.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnHint.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHint.getMinimumSize().height));
		btnBack.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnBack.getMinimumSize().height));
		/* posicionamento dos botõeses no painel */
        buttonPanel.add(btnMoveHero);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnHint);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnBack);
        buttonPanel.add(Box.createVerticalStrut(10));
    }

    private static void setupLeftPanel() {
		/* ***************************************************************************
		 * *							PAINEL À ESQUERDA 							 *
		 * ***************************************************************************/
		/* plano de fundo do painel */
        leftPanel.setBackground(Color.WHITE);
		/* tamanho do painel */
        leftPanel.setPreferredSize(new Dimension(250, leftPanel.getPreferredSize().height));
        leftPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		/* posicionamento dos paineis de botões e de texto no painel à esquerda */
        leftPanel.add(labelPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
	
	private static void setupBoardPanel() {
		/* ***************************************************************************
		 * *                      PAINEL DO TABULEIRO                                *
		 * ***************************************************************************/
		/* plano de fundo */
		boardPanel.setBackground(Color.WHITE);
		paddedBoardPanel.setBackground(Color.WHITE);
		/* padding do tabuleiro */
		paddedBoardPanel.setBorder(new EmptyBorder(80, 20, 80, 80));
		/* tamanho fixo dos botões */
		int buttonSize = 50; // Adjust size as needed
		/* posicionamento do painel com padding no painel do tabuleiro */
		paddedBoardPanel.add(boardPanel, BorderLayout.CENTER);
		/* posicionamento dos botões (células do tabuleiro) no painel do tabuleiro */
		for (int i = 0; i < 50; i++) {
			JButton boardButton = new JButton();
			boardButton.setFont(f);
			/* coloquei um tamanho pra poder mudar o tamanho dos sprites */
			boardButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
			/* i / 10 = linha 
			 * 1 % 10 = coluna
			 * linha + coluna ser par/impar garante que na proxima linha, o padrão inverte */
			if ((i / 10 + i % 10) % 2 == 0) {
				boardButton.setBackground(Color.WHITE);
			} else {
				boardButton.setBackground(Color.BLACK);
			}
			/* adiciona ao HashMap que associa cada botão a uma linha, coluna */
			String position = (i / 10) + "," + (i % 10);
			boardButtons.put(position, boardButton);
			/* adiciona o botão ao painel do tabuleiro */
			boardPanel.add(boardButton);
		}
		/* posiciona os sprites do hashmap no tabuleiro */
		for (Map.Entry<String, String> entry : actorPositions.entrySet()) {
			/* ex. key = "BOSS" */
			String key = entry.getKey();
			/* ex. pos = "2,4" */
			String pos = entry.getValue();
			JButton button = boardButtons.get(pos);
			/* tamanho do sprite dentro do botão */
			Image sprite = getSpriteForKey(key).getScaledInstance(buttonSize + 60,
																  buttonSize + 60,
																  Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(sprite));
		}
	}
	

	private static Image getSpriteForKey(String key) {
		Image sprite = null;
		/* associa cada tipo de key no HashMap dos atores do jogo a um sprite */
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
				if (key.startsWith("TRAP_STATIC")) {
					sprite = Settings.SPRITE_STATIC_TRAP;
				} else if (key.startsWith("TRAP_RANDOM")) {
					sprite = Settings.SPRITE_RANDOM_TRAP;
				} else if (key.startsWith("ELIXIR")) {
					sprite = Settings.SPRITE_ELIXIR;
				} else if (key.startsWith("TRAP_INVISIBLE")) {
					sprite = Settings.SPRITE_INVISIBLE_TRAP;
				}
				break;
		}
		return sprite;
	}
    
	public static JPanel getPanel() { return pGame; }
}

