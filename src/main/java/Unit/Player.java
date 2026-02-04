// === MODIFIED: java/unit/Player.java ===

package unit;

import logic.card.*;

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
        // 5 Macuahuitl (Strikes)
        for (int i = 0; i < 5; i++) {
            deck.add(new CardBuilder("Strike", CardType.ATTACK, Rarity.BRONZE)
                    .cost(1)
                    .damage(6)
                    .description("Strike with obsidian blade. Deal 6 damage.")
                    .build());
        }
        // 5 Chimalli (Defends)
        for (int i = 0; i < 5; i++) {
            deck.add(new CardBuilder("Shield", CardType.SKILL, Rarity.BRONZE)
                    .cost(1)
                    .block(5)
                    .description("Raise your shield. Gain 5 Block.")
                    .build());
        }
        Collections.shuffle(deck);
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