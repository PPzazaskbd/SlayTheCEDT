// === MODIFIED: java/logic/card/AttackCard.java ===

package logic.card;

import unit.Enemy;
import unit.Player;

import java.util.ArrayList;

public class AttackCard extends Card {
    private int damage;

    // Changed to public so StarterBuff can create cards
    public AttackCard(
            int cost,
            String name,
            String description,
            Rarity rarity,
            CardType cardType,
            int damage) {
        super(cost, name, description, rarity, cardType);
        this.damage = damage;
    }

    @Override
    public void execute(Player player, ArrayList<Enemy> enemies) {
        super.execute(player, enemies);
        System.out.println(this.description);

        // Deal damage to first living enemy (simple targeting for now)
        for (Enemy enemy : enemies) {
            if (enemy.getHp() > 0) {
                int newHp = enemy.getHp() - this.damage;
                enemy.setHp(newHp);
                System.out.println("Dealt " + damage + " damage to " + enemy.getName() +
                        "! HP: " + enemy.getHp());
                break; // Only hit first enemy
            }
        }
    }

    public int getDamage() {
        return damage;
    }
}