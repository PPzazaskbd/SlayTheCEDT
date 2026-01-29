package logic.card;

public class AttackCard extends Card {
    int damage;

    AttackCard(
            int cost,
            String name,
            String description,
            Rarity rarity,
            CardType cardType,
            int damage) {
        super(cost,name,description,Rarity.COMMON,CardType.ATTACK);
        this.damage = damage;

    }

}
