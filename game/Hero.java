/* pra gerar uma coluna aleatoria */
//import java.util.concurrent.ThreadLocalRandom;

import config.Settings;

public class Hero extends Character {
	/* TODO adicionar os sprites dos herois */
	private String heroClass;
	private String name;
	private int attackPoints;
	private int defensePoints;
	private int healthPoints;
	private int numberOfElixirs;
	private bool usedSpecialAbility;
	private int currentRow;
	private int currentColumn;
	
	public Hero(String heroClass, String name) {
		this.heroClass = heroClass;
		this.name = name;
		this.attackPoints = Settings.HERO_ATTACK_POINTS;
		this.defensePoints = Settings.HERO_DEFENSE_POINTS;
		this.healthPoints = Settings.HERO_HEALTH_POINTS;
		this.usedSpecialAbility = false;
		this.numberOfElixirs = 0;
		this.currentRow = 0;
		this.currentColumn = 1;
		// TODO 
		// this.currentColum = ?
		// posicionar numa coluna que nao tem trap
	}

	public int setCurrentPosition(int row, int column) {
		this.currentRow = row;
		this.currentColumn = column;
	}
	
	public void useElixir() {
		this.healthPoints += Settings.V;
		this.numberOfElixirs -= 1;
	}

	public void useSpecialAbility(String heroClass) {
		switch (heroClass) {
			case "Warrior":
				if (usedSpecialAbility) {
					this.defensePoints /= 1.5;
				} else {	
					this.defensePoints *= 1.5;
					this.usedSpecialAbility = true;
				}
				break;
			case "Paladin":
				if (healthPoints + (1.5 * Settings.HERO_HEALTH_POINTS) > Settings.HERO_HEALTH_POINTS) {
					this.healthPoints = Settings.HERO_HEALTH_POINTS;
					this.usedSpecialAbility = true;
				} else {
					this.healthPoints += (1.5 * Settings.HERO_HEALTH_POINTS); 
					this.usedSpecialAbility = true;
				}
				break;
			case "Barbarian":
				this.attackPoints *= 1.5;
				super.attack(Character c);
				this.attackPoints /= 1.5;
				this.usedSpecialAbility = true;
		}
	}
}


