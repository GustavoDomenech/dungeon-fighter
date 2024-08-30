package game;

import java.util.concurrent.ThreadLocalRandom;
import config.Settings;

public class Trap {
    private int damage;
	private boolean isRandom;

    public Trap(boolean isRandom) {
        if (isRandom) {
            this.damage = ThreadLocalRandom.current().nextInt(0, Settings.MAX_TRAP_DAMAGE);
        } else {
            this.damage = 1;
        }
    }

    public int attack(Character c) {
        c.takeDamage(this.damage);
		return this.damage;
    }

}
