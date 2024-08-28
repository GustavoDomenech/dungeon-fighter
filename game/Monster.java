package game;

import java.util.concurrent.ThreadLocalRandom;

import config.Settings;

public class Monster extends Character {
    private int attackPoints;
    private int defensePoints;
    private int healthPoints;
	private String type;
	private boolean usedSpecialAbilityOn = false;
	private int roundsPassed;
	private boolean isDeadOrNot;

    public Monster(String name, String type) {
        super(name, 0, 0, 0);

        if (type.equals("BOSS")) {
            super.setAttackPoints(Settings.BOSS_ATTACK_POINTS);
            super.setDefensePoints(Settings.BOSS_DEFENSE_POINTS);
            super.setHealthPoints(Settings.BOSS_HEALTH_POINTS);
			this.type = type;
			this.isDeadOrNot = false;
        } else if (type.equals("1") || type.equals("2") || type.equals("3") || type.equals("4")) {
            this.attackPoints = ThreadLocalRandom.current().nextInt(20, 60);
            this.defensePoints = ThreadLocalRandom.current().nextInt(20, 60);
            this.healthPoints = ThreadLocalRandom.current().nextInt(20, 60);
			this.type = type;
			this.roundsPassed = 0;
			this.isDeadOrNot = false;

            super.setAttackPoints(this.attackPoints);
            super.setDefensePoints(this.defensePoints);
            super.setHealthPoints(this.healthPoints);
        }     
	}

	public String getMonsterType() { return this.type; }

	public void toggleUsedSpecialAbilityOn() {
		if (this.usedSpecialAbilityOn) {
			this.usedSpecialAbilityOn = false;
		} else {
			this.usedSpecialAbilityOn = true;
		}
	}

	public boolean hasUsedSpecialAbilityOn() { return usedSpecialAbilityOn; }

	public int getRoundsPassed() { return roundsPassed; }

	public void increaseRoundsPassed() { this.roundsPassed++; }

	public boolean isBoss() { return this.type.equals("BOSS"); }

	public void wasKilled() { this.isDeadOrNot = true; }

	public boolean isDead() { return this.isDeadOrNot; };
}

