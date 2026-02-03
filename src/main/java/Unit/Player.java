// === MODIFIED: java/unit/Player.java ===

package unit;

import logic.card.Card;
import logic.card.AttackCard;
import logic.card.SkillCard;
import logic.card.CardType;
import logic.card.Rarity;

import java.util.ArrayList;
import java.util.Collections;

public class Player extends BaseUnit {

    private int currentEnergy;
    private int maxEnergy;
    private int block;
    private int cacao;
    private ArrayList<Card> deck = new ArrayList<>();

    // Xipe Totec blessing fields
    private boolean hasXipeTotecBlessing = false;
    private boolean freeCardNextTurn = false;

    public Player(String name, int maxHp, int maxEnergy, int block, int cacao) {
        super(name, maxHp);
        this.currentEnergy = maxEnergy;
        this.maxEnergy = maxEnergy;
        this.block = block;
        this.cacao = cacao;
        setBeginCard();
    }

    /**
     * Initialize starting deck: 5 Strikes + 5 Defends
     */
    void setBeginCard() {
        // Add 5 Strike cards (Attack)
        for (int i = 0; i < 5; i++) {
            AttackCard strike = new AttackCard(
                    1,                      // cost
                    "Strike",               // name
                    "Deal 6 damage",        // description
                    Rarity.BRONZE,          // rarity
                    CardType.ATTACK,        // type
                    6                       // damage
            );
            deck.add(strike);
        }

        // Add 5 Defend cards (Skill)
        for (int i = 0; i < 5; i++) {
            SkillCard defend = new SkillCard(
                    1,                      // cost
                    "Defend",               // name
                    "Gain 5 block",         // description
                    Rarity.BRONZE,          // rarity
                    CardType.SKILL,         // type
                    5                       // block
            );
            deck.add(defend);
        }

        // Shuffle the deck
        Collections.shuffle(deck);
        System.out.println("Starting deck initialized: 5 Strikes + 5 Defends (10 cards total)");
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder(
                "name='" + super.getName() + '\'' +
                        ", hp=" + super.getHp() +
                        ", maxHp=" + super.getMaxHp() +
                        ", currentEnergy=" + currentEnergy +
                        ", block=" + block +
                        ", cacao=" + cacao +
                        '}'
        );
        for (Card card : deck) {
            answer.append("\n");
            answer.append(card.toString());
        }
        return answer.toString();
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getGold() {
        return cacao;
    }

    public void setGold(int cacao) {
        this.cacao = cacao;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    // Xipe Totec blessing methods
    public boolean hasXipeTotecBlessing() {
        return hasXipeTotecBlessing;
    }

    public void setHasXipeTotecBlessing(boolean hasXipeTotecBlessing) {
        this.hasXipeTotecBlessing = hasXipeTotecBlessing;
    }

    public boolean isFreeCardNextTurn() {
        return freeCardNextTurn;
    }

    public void setFreeCardNextTurn(boolean freeCardNextTurn) {
        this.freeCardNextTurn = freeCardNextTurn;
    }
}