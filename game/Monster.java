package game;

import config.Settings;

public class Monster extends Character {
		/* TODO da pra fazer uma função que randomiza os stats dos monstros */
    public Monster(String name, int attackPoints, int defensePoints, int healthPoints, String type) {
        super(name, attackPoints, defensePoints, healthPoints);
	}
    public Monster() {
		/* exemplo de sobrecarga de metodos ein anota */
        super(Settings.BOSS_NAME, Settings.BOSS_ATTACK_POINTS, Settings.BOSS_DEFENSE_POINTS, Settings.BOSS_HEALTH_POINTS);
    }
}
