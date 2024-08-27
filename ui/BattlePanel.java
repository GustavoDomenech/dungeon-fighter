package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
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

import ui.GamePanel;
import game.NewGame;
import game.Hero;
import config.Settings;

public class BattlePanel {
	/* TODO 
	 * O ataque ocorre da seguinte forma: o primeiro ataca sorteando um número
     * de 0 a W e somando esse número ao valor de seu ataque. O segundo se 
     * defende sorteando um número de 0 a W e somando esse número ao valor de 
     * sua defesa. O dano é o valor do ataque do primeiro menos o valor da 
     * defesa do segundo. Se o dano é positivo, o segundo perde vida, caso 
     * contrário, o primeiro que perde.
	 */
}

