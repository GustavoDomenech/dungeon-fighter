public class Monster extends Character {
	private static String name;
	private static int attackPoints;
	private static int defensePoints;
	private static int healthPoints;
	
	public Monster(
			String name, 
			int attackPoints, 
			int defensePoints, 
			int healthPoints) 
	{
		this.name = name;
		this.attackPoints = attackPoints;
		this.defensePoints = defensePoints;
		this.healthPoints = healthPoints;
	}

	public Monster() {
		this.name = "Blorg";
		this.attackPoints = 120;
		this.defensePoints = 60;
		this.healthPoints = 100;
	}

	public void attack(Hero h) { h.takeDamage(this.attackPoints) }

	public void takeDamage(int damage) { 
		if (this.healthPoints - damage <= 0) {
			this.healthPoints = 0;
		} else {
			this.healthPoints -= damage;
		}
	}


	public String getName(){ return name; }

	public int getAttackPoints(){ return attackPoints; }

	public int getDefensePoints(){ return defensePoints; }

	public int getHealthPoints(){ return healthPoints; }
}
