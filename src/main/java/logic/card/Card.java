package logic.card;

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

    public void execute() {
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
}
