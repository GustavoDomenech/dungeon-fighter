package config;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Settings {
	/* enunciado settings */
	public static final int X = 5; 	// recebe X pontos para distribuir entre seus atributos.
	public static final int Y = 10; // os pontos de vida serão Y + saúde
	public static final int E = 3; 	// capacidade da bolsa
	public static final int V = 10; // um elixir é um preparo que dá V pontos de vida
	public static final int N = 4; 	// existem N monstros ocultos de pequeno potencial ofensivo 
	public static final int W = 10; // armadilhas tiram pontos de vida aleatoriamente entre (0 a W)
	/* application settings */
	public static Font FONT;
	public static Image BACKGROUND;
	public static Image VERSUS;
	/* window settings */
	public static final int WINDOW_WIDTH = 1488;
    public static final int WINDOW_HEIGHT = 768;
	/* board settings */
	public static final int BOARD_ROWS = 5;
	public static final int BOARD_COLUMNS = 10;
	/* hero settings */
	public static final int HERO_ATTACK_POINTS = 60;
	public static final int HERO_DEFENSE_POINTS = 60;
	public static final int HERO_HEALTH_POINTS = Y + 60;
	public static final int ELIXIR_HEAL_AMOUNT = V;
	/* monster settings */
	public static final String[] MONSTER_NAMES= {"Gerson", "Carlos", "Lorenzo", "Gustavo"};
	/* boss settings */
	public static final String BOSS_NAME = "Rodrigo";
	public static final int BOSS_ATTACK_POINTS = 200;
	public static final int BOSS_DEFENSE_POINTS = 200;
	public static final int BOSS_HEALTH_POINTS = 200;
	/* trap settings */
	public static final int MAX_TRAP_DAMAGE = W;
	/* sprites */
	public static Image SPRITE_WARRIOR;
	public static Image SPRITE_PALADIN;
	public static Image SPRITE_BARBARIAN;
	public static Image SPRITE_MONSTER_1;
	public static Image SPRITE_MONSTER_2;
	public static Image SPRITE_MONSTER_3;
	public static Image SPRITE_MONSTER_4;
	public static Image SPRITE_BOSS;
	public static Image SPRITE_STATIC_TRAP;
	public static Image SPRITE_RANDOM_TRAP;
	public static Image SPRITE_INVISIBLE_TRAP;
	public static Image SPRITE_ELIXIR;
	public static Image SPRITE_INVISIBLE_ELIXIR;
	public static Image SPRITE_INVISIBLE_MONSTER_1;
    public static Image SPRITE_INVISIBLE_MONSTER_2;
    public static Image SPRITE_INVISIBLE_MONSTER_3;
    public static Image SPRITE_INVISIBLE_MONSTER_4;
	static {
		/* image exception handling */
		try {
			BACKGROUND = ImageIO.read(new File("assets/images/background.png"));
			VERSUS = ImageIO.read(new File("sprites/versus.png"));
			SPRITE_WARRIOR = ImageIO.read(new File("sprites/warrior.png"));
			SPRITE_PALADIN = ImageIO.read(new File("sprites/paladin.png"));
			SPRITE_BARBARIAN = ImageIO.read(new File("sprites/barbarian.png"));
			SPRITE_MONSTER_1 = ImageIO.read(new File("sprites/monster_1.png"));
			SPRITE_MONSTER_2 = ImageIO.read(new File("sprites/monster_2.png"));
			SPRITE_MONSTER_3 = ImageIO.read(new File("sprites/monster_3.png"));
			SPRITE_MONSTER_4 = ImageIO.read(new File("sprites/monster_4.png"));
			SPRITE_INVISIBLE_MONSTER_1 = ImageIO.read(new File("sprites/invisible.png"));
			SPRITE_INVISIBLE_MONSTER_2 = ImageIO.read(new File("sprites/invisible.png"));
			SPRITE_INVISIBLE_MONSTER_3 = ImageIO.read(new File("sprites/invisible.png"));
			SPRITE_INVISIBLE_MONSTER_4 = ImageIO.read(new File("sprites/invisible.png"));
			SPRITE_BOSS = ImageIO.read(new File("sprites/boss.jpg"));
			SPRITE_STATIC_TRAP = ImageIO.read(new File("sprites/static_trap.png"));
			SPRITE_RANDOM_TRAP = ImageIO.read(new File("sprites/random_trap.png"));
			SPRITE_ELIXIR = ImageIO.read(new File("sprites/elixir.png"));
			SPRITE_INVISIBLE_TRAP = ImageIO.read(new File("sprites/invisible.png"));
			SPRITE_INVISIBLE_ELIXIR = ImageIO.read(new File("sprites/invisible.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* font exception handling */
		try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, Files.newInputStream(Paths.get("assets/fonts/FiraCodeNerdFont-Bold.ttf"))).deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
	}
}

