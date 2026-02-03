package application;

import unit.Enemy;
import unit.Player;

import java.util.ArrayList;

public class GameManager {
    public static GameManager gameManager;
    private GameManager() {}
    private Player player;
    private ArrayList<Enemy> enemies;
    public static synchronized GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }return gameManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    public void addEnemies (Enemy enemy) {
        this.getEnemies().add(enemy);
    }
}
