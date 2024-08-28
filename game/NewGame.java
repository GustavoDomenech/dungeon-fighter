package game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class NewGame {
    private HashMap<String, String> actorPositions;
	private String heroKey;

    public NewGame(String heroClass, String heroName) {
        actorPositions = new HashMap<>();
		String[] monsters = { "MONSTER_INVISIBLE_1", "MONSTER_INVISIBLE_2", "MONSTER_INVISIBLE_3", "MONSTER_INVISIBLE_4" };
        String[] traps = {
            "TRAP_INVISIBLE_STATIC_1", "TRAP_INVISIBLE_STATIC_2", 
            "TRAP_INVISIBLE_STATIC_3", "TRAP_INVISIBLE_STATIC_4", 
            "TRAP_INVISIBLE_STATIC_5", "TRAP_INVISIBLE_STATIC_6", 
            "TRAP_INVISIBLE_RANDOM_1", "TRAP_INVISIBLE_RANDOM_2", 
            "TRAP_INVISIBLE_RANDOM_3", "TRAP_INVISIBLE_RANDOM_4", 
            "TRAP_INVISIBLE_RANDOM_5", "TRAP_INVISIBLE_RANDOM_6"
        };
        String[] elixirs = { "ELIXIR_INVISIBLE_1", "ELIXIR_INVISIBLE_2", "ELIXIR_INVISIBLE_3" };
		
		actorPositions.put(heroClass, generateUniquePosition(0));

        for (int i = 0 ; i < monsters.length; i++) {
            actorPositions.put(monsters[i], generateUniquePosition(i));
        }

        for (String trap : traps) {
            actorPositions.put(trap, generateUniquePosition(-1));
        }

        for (String elixir : elixirs) {
            actorPositions.put(elixir, generateUniquePosition(-1));
        }
        
		actorPositions.put("BOSS", generateUniquePosition(4));
    }

    private String generateUniquePosition(int row) {
        String position;
        
		do {
        	int x;

			if (row >= 0) {
				x = row;
			} else {
				x = ThreadLocalRandom.current().nextInt(0, 5);
			}
            int y = ThreadLocalRandom.current().nextInt(0, 10);
            position = x + "," + y;
        } while (actorPositions.containsValue(position));
        return position;
    }

    public HashMap<String, String> getActorPositions() { return actorPositions; }
}

