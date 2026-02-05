package logic.card;

import unit.BaseUnit;
import unit.Enemy;
import unit.Player;

import java.util.ArrayList;
import java.util.List;

public  class Card {
    protected int cost;
    protected String name;
    protected String description;
    protected Rarity rarity;
    protected CardType cardType;
    protected List<CardEffect> effects = new ArrayList<>();

    public Card(int cost, String name, String description, Rarity rarity, CardType cardType) {
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.cardType = cardType;
    }

    protected void applyEffects(unit.BaseUnit self, unit.BaseUnit target) {
        for (CardEffect effect : effects) {
            unit.BaseUnit actualTarget = (effect.target() == CardEffect.Target.SELF) ? self : target;

            if (actualTarget != null) {
                logic.effects.StatusEffect status = logic.effects.StatusEffectFactory.create(
                        effect.effectName(),
                        effect.stacks(),
                        effect.duration()
                );

                if (status != null) {
                    actualTarget.addStatus(status);
                    System.out.println("Applied " + effect.effectName() + " to " + actualTarget.getName());
                }
            }
        }
    }

    public void execute(Player player, ArrayList<Enemy> enemies) {
        System.out.print("playing the card : ");
    }


    public String toString() {
        return "Card{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rarity=" + rarity +
                ", cardType=" + cardType +
                '}';
    }

    public void setEffects(List<CardEffect> effects) {
        this.effects = effects;
    }
    // Add this getter to Card.java
    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public CardType getCardType() {
        return cardType;
    }
}
