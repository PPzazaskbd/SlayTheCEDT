// === NEW FILE: java/logic/buff/StarterBuff.java ===

package logic.buff;

import logic.card.AttackCard;
import logic.card.CardType;
import logic.card.Rarity;
import unit.Player;

/**
 * StarterBuff - The 4 Aztec god blessings player chooses at run start.
 * Each provides a unique starting bonus.
 */
public enum StarterBuff {

    // Huitzilopochtli: God of war - extra attack cards
    HUITZILOPOCHTLI("Huitzilopochtli", "Receive 3 Attack cards"),

    // Quetzalcoatl: Feathered serpent - more HP
    QUETZALCOATL("Quetzalcoatl", "Gain 25% max HP"),

    // Tlaloc: Rain god - extra resources
    TLALOC("Tlaloc", "Gain 50 cacao"),

    // Xipe Totec: God of renewal - first card free after shuffle
    XIPE_TOTEC("Xipe Totec", "After shuffling, first card is free");

    private final String godName;
    private final String description;

    StarterBuff(String godName, String description) {
        this.godName = godName;
        this.description = description;
    }

    /**
     * Apply this buff to the player at run start
     */
    public void apply(Player player) {
        switch (this) {
            case HUITZILOPOCHTLI:
                // Add 3 attack cards to starting deck
                for (int i = 0; i < 3; i++) {
                    player.getDeck().add(new AttackCard(
                            1,                          // cost
                            "Strike",                   // name
                            "Deal 6 damage",            // description
                            Rarity.BRONZE,              // rarity
                            CardType.ATTACK,            // type
                            6                           // damage
                    ));
                }
                System.out.println("Huitzilopochtli blesses you with 3 Strike cards!");
                break;

            case QUETZALCOATL:
                // Increase max HP by 25%
                int bonusHp = (int) (player.getMaxHp() * 0.25);
                player.setMaxHp(player.getMaxHp() + bonusHp);
                player.setHp(player.getHp() + bonusHp); // Also heal the bonus
                System.out.println("Quetzalcoatl blesses you with +" + bonusHp + " max HP!");
                break;

            case TLALOC:
                // Add 50 cacao
                int bonusCacao = 50;
                player.setGold(player.getGold() + bonusCacao);
                System.out.println("Tlaloc blesses you with +" + bonusCacao + " cacao!");
                break;

            case XIPE_TOTEC:
                // Set flag for free card after shuffle (handled in CombatFlow)
                player.setHasXipeTotecBlessing(true);
                System.out.println("Xipe Totec blesses you! First card after shuffle is free.");
                break;
        }
    }

    public String getGodName() {
        return godName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return godName + ": " + description;
    }
}