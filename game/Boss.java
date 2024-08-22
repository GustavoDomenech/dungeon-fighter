import config.Settings;

public class Boss extends Monster {
	/* TODO adicionar os sprites do chefão */
	private String name;
	private int attackPoints;
	private int defensePoints;
	private int healthPoints;
	private int row;
	private int column;
	/* TODO colocar numa coluna aleatoria da ultima linha*/

	public Boss() {
		this.name = Settings.BOSS_NAME;
		this.attackPoints = Settings.BOSS_ATTACK_POINTS;
		this.defensePoints = Settings.BOSS_DEFENSE_POINTS;
		this.healthPoints = Settings.BOSS_HEALTH_POINTS;
	}
}
