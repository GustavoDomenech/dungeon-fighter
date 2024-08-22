import java.util.concurrent.ThreadLocalRandom;

import config.Settings;

public class Trap {
	/* TODO adicionar os sprites das traps */
	private int damage;
	private bool isVisible;
	private int row;
	private int column;

	public Trap(bool isRandom, int row, int column) {
		this.isVisible = false;
		this.row = row;
		this.column = column;
		/* TODO posicionar numa coluna aleatoria for loop etc*/

		if (isRandom) {
			this.damage = ThreadLocalRandom.current().nextInt(0, Settings.W + 1);
		} else {
			this.damage = 1;
		}
	}

	public revealTrap() {
		this.isVisible = true;
	}
}
