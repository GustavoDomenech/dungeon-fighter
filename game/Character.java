package game;

import java.awt.Image;
import config.Settings;

public abstract class Character {
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected int healthPoints;

    public Character(String name, int attackPoints, int defensePoints, int healthPoints) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.healthPoints = healthPoints;
    }

    public int takeDamage(int damage) {
        this.healthPoints = Math.max(0, this.healthPoints - damage);
		return damage;
    }

	public String getName() { return name; }

	public int getAttackPoints() { return attackPoints; }
	
	public int getDefensePoints() { return defensePoints; }
	
	public int getHealthPoints() { return healthPoints; }

	public void setAttackPoints(int attackPoints) { this.attackPoints = attackPoints; };
	
	public void setDefensePoints(int defensePoints) { this.defensePoints = defensePoints; };
    
	public void setHealthPoints(int healthPoints) { this.healthPoints = healthPoints; };
}

