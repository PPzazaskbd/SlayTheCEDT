package logic.effects;

import unit.BaseUnit;

public class Bleeding extends StatusEffect {
    public Bleeding(int stacks) { super("Bleeding", stacks); }
    @Override
    public void onTurnEnd(BaseUnit owner) {
        owner.setHp(owner.getHp() - stacks);
    }
    @Override
    public String getDescription() { return "Lose " + stacks + " HP at the end of your turn."; }
}