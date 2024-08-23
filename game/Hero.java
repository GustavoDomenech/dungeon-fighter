package game;

import java.awt.Image;

import config.Settings;

public class Hero extends Character {
    private String heroClass;
    private boolean usedSpecialAbility;
    private int numberOfElixirs;

    public Hero(String heroClass, String name) {
        super(name, Settings.HERO_ATTACK_POINTS, Settings.HERO_DEFENSE_POINTS, Settings.HERO_HEALTH_POINTS);
        this.heroClass = heroClass;
        this.usedSpecialAbility = false;
        this.numberOfElixirs = 0;
    }

    public void useElixir() {
        this.healthPoints += Settings.ELIXIR_HEAL_AMOUNT;
        this.numberOfElixirs -= 1;
        this.healthPoints = Math.min(this.healthPoints, Settings.HERO_HEALTH_POINTS);
    }

    public void useSpecialAbility() {
        switch (heroClass) {
            case "Warrior":
				/* TODO isso aqui nao parece que vai funcionar 
				 * (eu pensei em deixar o botão nao clicável e chamar a funçao depois de 2 rodadas) */
                if (usedSpecialAbility) {
                    this.defensePoints /= 1.5;
                } else {
                    this.defensePoints *= 1.5;
                    this.usedSpecialAbility = true;
                }
                break;
            case "Paladin":
                this.healthPoints = Math.min(this.healthPoints + (int) (1.5 * Settings.HERO_HEALTH_POINTS), Settings.HERO_HEALTH_POINTS);
                this.usedSpecialAbility = true;
                break;
            case "Barbarian":
                this.attackPoints *= 1.5;
				this.usedSpecialAbility = true;
                break;
        }
    }

	public int getNumberOfElixirs() { return numberOfElixirs; }
}
