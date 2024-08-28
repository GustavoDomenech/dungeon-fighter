package game;

import java.awt.Image;
import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

import config.Settings;

public class Hero extends Character {
    private String heroClass;
    private int numberOfElixirs;

    public Hero(String heroClass, String name) {
        super(name, Settings.HERO_ATTACK_POINTS, Settings.HERO_DEFENSE_POINTS, Settings.HERO_HEALTH_POINTS);
        this.heroClass = heroClass;
        this.numberOfElixirs = 0;
    }

    public int useElixir() {
        this.healthPoints += Settings.ELIXIR_HEAL_AMOUNT;
        this.numberOfElixirs -= 1;
		return Settings.ELIXIR_HEAL_AMOUNT;
    }

    public int useSpecialAbility(Monster m, boolean usedSpecialAbility) {
        switch (heroClass) {
            case "HERO_WARRIOR":
                if (usedSpecialAbility) {
                    this.defensePoints /= 1.5;
					return this.defensePoints;
                } else {
                    this.defensePoints *= 1.5;
					return this.defensePoints;
                }
            case "HERO_PALADIN":
				int healthPointsBefore = this.healthPoints;
                this.healthPoints = Math.min(this.healthPoints + (int) (1.5 * Settings.HERO_HEALTH_POINTS), Settings.HERO_HEALTH_POINTS);
				return healthPoints - healthPointsBefore;
            case "HERO_BARBARIAN":
				if (usedSpecialAbility) {
					this.attackPoints /= 1.5;
				} else {
					this.attackPoints *= 1.5;
				}
                return this.attackPoints;
        }
		return 0;
    }

	public void earnRandomStatPoints() {
        int points = 15;
		int stat = ThreadLocalRandom.current().nextInt(0, 4);

		switch (stat) {
			case 0:
				this.attackPoints += points;
				break;
			case 1:
				this.defensePoints += points;
				break;
			case 2:
				this.healthPoints += points;
				break;
		}
    }

	public int getNumberOfElixirs() { return numberOfElixirs; }
	
	public void foundElixir() { this.numberOfElixirs++; }

	public String getHeroClass() { return heroClass; }
}
