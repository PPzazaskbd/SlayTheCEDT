package logic.card;

import unit.Enemy;
import unit.Player;

import java.util.ArrayList;

public interface IExecuteable {
    void execute(Player player , ArrayList<Enemy> enemies);
}
