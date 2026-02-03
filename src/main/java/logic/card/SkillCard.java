// === MODIFIED: java/logic/card/SkillCard.java ===

package logic.card;

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

        // Add block to player
        player.setBlock(player.getBlock() + this.block);
        System.out.println("Gained " + this.block + " block! Total block: " + player.getBlock());
    }




    public int getBlock() {
        return block;
    }


}