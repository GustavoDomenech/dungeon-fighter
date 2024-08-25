package game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import config.Settings;

public class NewGame {
	/* TODO função pra colocar em posições aleatorias */
	private HashMap<String, String> actorPositions;

	public NewGame(String heroClass, String heroName) {
		actorPositions = new HashMap<>();
		
		switch (heroClass) {
			case "Warrior":
				actorPositions.put("HERO_WARRIOR", "0,0");
				break;
			case "Paladin":
				actorPositions.put("HERO_PALADIN", "0,0");
				break;
			case "Barbarian":
				actorPositions.put("HERO_BARBARIAN", "0,0");
				break;
			default:
				actorPositions.put("HERO_WARRIOR", "0,0");
				break;
		}
        actorPositions.put("MONSTER_1", "0,6");
        actorPositions.put("MONSTER_2", "1,2");
        actorPositions.put("MONSTER_3", "2,9");
        actorPositions.put("MONSTER_4", "3,7");
        actorPositions.put("TRAP_INVISIBLE_STATIC_1", "4,4");
        actorPositions.put("TRAP_INVISIBLE_STATIC_2", "0,3");
        actorPositions.put("TRAP_INVISIBLE_STATIC_3", "1,5");
        actorPositions.put("TRAP_INVISIBLE_STATIC_4", "2,6");
        actorPositions.put("TRAP_INVISIBLE_STATIC_5", "3,1");
        actorPositions.put("TRAP_INVISIBLE_STATIC_6", "4,8");
        actorPositions.put("TRAP_INVISIBLE_RANDOM_1", "0,7");
        actorPositions.put("TRAP_INVISIBLE_RANDOM_2", "1,4");
        actorPositions.put("TRAP_INVISIBLE_RANDOM_3", "2,8");
        actorPositions.put("TRAP_INVISIBLE_RANDOM_4", "3,2");
        actorPositions.put("TRAP_INVISIBLE_RANDOM_5", "4,0");
        actorPositions.put("TRAP_INVISIBLE_RANDOM_6", "2,3");
        actorPositions.put("ELIXIR_1", "1,6");
        actorPositions.put("ELIXIR_2", "3,8");
        actorPositions.put("ELIXIR_3", "0,2");
        actorPositions.put("BOSS", "4,9");
        //actorPositions.put("TRAP_STATIC_1", "4,4");
        //actorPositions.put("TRAP_STATIC_2", "0,3");
        //actorPositions.put("TRAP_STATIC_3", "1,5");
        //actorPositions.put("TRAP_STATIC_4", "2,6");
        //actorPositions.put("TRAP_STATIC_5", "3,1");
        //actorPositions.put("TRAP_STATIC_6", "4,8");
        //actorPositions.put("TRAP_RANDOM_1", "0,7");
        //actorPositions.put("TRAP_RANDOM_2", "1,4");
        //actorPositions.put("TRAP_RANDOM_3", "2,8");
        //actorPositions.put("TRAP_RANDOM_4", "3,2");
        //actorPositions.put("TRAP_RANDOM_5", "4,0");
        //actorPositions.put("TRAP_RANDOM_6", "2,3");
	}

	public static void changeHeroPosition(HashMap<String, String> h, String newPosition) {
		for (Map.Entry<String, String> entry : h.entrySet()) {
			if (entry.getKey().startsWith("HERO")) {
				h.put(entry.getKey(), newPosition);
			}
		}
	}

	public HashMap<String, String> getActorPositions() { return actorPositions; }
}
