// === MODIFIED: java/logic/card/AttackCard.java ===

package logic.card;

import logic.DamageEngine;
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

        for (Enemy enemy : enemies) {
            if (enemy.getHp() > 0) {
                // 1. Deal Damage
                DamageEngine.applyDamage(player, enemy, this.damage);

                // 2. Apply Effects (Pass the enemy as the target)
                applyEffects(player, enemy);

                break; // Single target for now
            }
        }
    }

    public int getDamage() {
        return damage;
    }
}