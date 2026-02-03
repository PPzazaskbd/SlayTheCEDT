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

    public Card(int cost, String name, String description, Rarity rarity, CardType cardType) {
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.cardType = cardType;
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
