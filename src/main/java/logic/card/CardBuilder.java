package logic.card;

import java.util.ArrayList;
import java.util.List;

public class CardBuilder {
    private int cost;
    private String name;
    private String description;
    private Rarity rarity;
    private CardType cardType;
    private int damage = 0;
    private int block = 0;
    private List<CardEffect> effects = new ArrayList<>();

    // Start with mandatory fields
    public CardBuilder(String name, CardType cardType, Rarity rarity) {
        this.name = name;
        this.cardType = cardType;
        this.rarity = rarity;
    }

    public CardBuilder cost(int cost) {
        this.cost = cost;
        return this;
    }

    public CardBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CardBuilder damage(int damage) {
        this.damage = damage;
        return this;
    }

    public CardBuilder block(int block) {
        this.block = block;
        return this;
    }

    /**
     * Add an effect to the card.
     * @param name Name of the effect (must match Factory)
     * @param stacks Intensity
     * @param duration Duration in turns (-1 for permanent)
     * @param target SELF or ENEMY
     */
    public CardBuilder addEffect(String name, int stacks, int duration, CardEffect.Target target) {
        this.effects.add(new CardEffect(name, stacks, duration, target));
        return this;
    }

    public Card build() {
        Card card = null;
        if (cardType == CardType.ATTACK) {
            AttackCard aCard = new AttackCard(cost, name, description, rarity, cardType, damage);
            aCard.setEffects(effects);
            card = aCard;
        } else if (cardType == CardType.SKILL) {
            SkillCard sCard = new SkillCard(cost, name, description, rarity, cardType, block);
            sCard.setEffects(effects);
            card = sCard;
        }

        if (card == null) {
            throw new IllegalStateException("CardType not supported in Builder");
        }
        return card;
    }
}