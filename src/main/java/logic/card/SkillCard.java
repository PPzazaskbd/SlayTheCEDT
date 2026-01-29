package logic.card;

import application.GameManager;

public class SkillCard extends  Card{
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
    public void execute() {
        super.execute();
        System.out.println(this.description);
        GameManager.getInstance().getPlayer().setBlock(GameManager.getInstance().getPlayer().getBlock()+this.block);
        System.out.println(GameManager.getInstance().getPlayer().getBlock());

    }
}
