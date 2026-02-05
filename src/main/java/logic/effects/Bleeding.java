package logic.effects;

import unit.BaseUnit;

public class Bleeding extends StatusEffect {
    public Bleeding(int stacks) { super("Bleeding", stacks); }
    // New constructor for Builder
    public Bleeding(int stacks, int duration) { super("Bleeding", stacks, duration); }
    @Override
    public void onTurnEnd(BaseUnit owner) {
        owner.setHp(owner.getHp() - stacks);
    }
    @Override
    public String getDescription() { return "Lose " + stacks + " HP at the end of your turn."; }
}