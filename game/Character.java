import config.Settings;

public abstract class Character {
	private String name;
	private int attackPoints;
	private int defensePoints;
	private int healthPoints;
	private int row;
	private int column;

	public void attack(Character c) { c.takeDamage(this.attackPoints) }

	public void takeDamage(int damage) { 
		if (this.healthPoints - damage <= 0) {
			this.healthPoints = 0;
		} else {
			this.healthPoints -= damage;
		}
	}
}
