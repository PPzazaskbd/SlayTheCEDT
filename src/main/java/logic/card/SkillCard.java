// === MODIFIED: java/logic/card/SkillCard.java ===

package logic.card;

import logic.DamageEngine;
import unit.Enemy;
import unit.Player;

import java.util.ArrayList;

/**
 * SkillCard - Defensive/utility cards that add block or other effects
 */
public class SkillCard extends Card {
    protected int block;

    public SkillCard(
            int cost,
            String name,
            String description,
            Rarity rarity,
            CardType cardType,
            int block
    ) {
        super(cost, name, description, rarity, cardType);
        this.block = block;
    }

    @Override
    public void execute(Player player, ArrayList<Enemy> enemies) {
        super.execute(player, enemies);
        System.out.println(this.description);

        // CHANGED: Apply block with effect modifiers (Parched)
        int blockGained = DamageEngine.applyBlockGain(player, this.block);
        player.setBlock(player.getBlock() + blockGained);

        System.out.println("Gained " + blockGained + " block! Total block: " + player.getBlock());
    }


    public int getBlock() {
        return block;
    }


}