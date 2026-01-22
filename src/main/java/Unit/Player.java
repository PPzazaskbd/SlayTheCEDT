package Unit;

import logic.Card;
import logic.CardType;
import logic.Rarity;
import logic.SkillCard;

import java.util.ArrayList;
import java.util.Collections;

public class Player extends BaseUnit{
    String name;
     int hp;
     int maxHp;
     int currentEnergy;
     int maxEnergy;
     int block;

     int gold;
     ArrayList<Card> deck = new ArrayList<>(); // only in hand first

    public Player(String name, int hp, int maxHp, int maxEnergy, int block, int gold) {
        super(name,hp,maxHp);
        this.currentEnergy = maxEnergy;
        this.maxEnergy = maxEnergy;
        this.block = block;
        this.gold = gold; // move to GameManager
        setBeginCard();
    }

    void setBeginCard() {
        for (int i = 0; i < 20; i++) {
            SkillCard defend = new SkillCard(i, "Defend", "Gain " + (4 * i + 2) + " Block", Rarity.COMMON, CardType.SKILL, 4 * i + 2);
            deck.add(defend);
            System.out.println("defend added");
        }
        Collections.shuffle(deck);

    }
    //          potion , relic , draw/discard/exuasted pile,buff,debuff

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder(

                "name='" + name + '\'' +
                        ", hp=" + hp +
                        ", maxHp=" + maxHp +
                        ", currentEnergy=" + currentEnergy +
                        ", block=" + block +
                        ", gold=" + gold +
                        '}'
        );
        for (Card card : deck) {
            answer.append("\n");
            answer.append(card.toString());
        }
        return answer.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  int getHp() {
        return hp;
    }

    public  void setHp(int hp) {
        this.hp = hp;
    }

    public  int getMaxhp() {
        return maxHp;
    }

    public  void setMaxhp(int maxHp) {
        this.maxHp = maxHp;
    }

    public  int getCurrentEnergy() {
        return currentEnergy;
    }

    public  void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public  int getMaxEnergy() {
        return maxEnergy;
    }

    public  void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public  int getBlock() {
        return block;
    }

    public  void setBlock(int block) {
        this.block = block;
    }

    public  int getGold() {
        return gold;
    }

    public  void setGold(int gold) {
        this.gold = gold;
    }

    public  ArrayList<Card> getDeck() {
        return deck;
    }

    public  void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
