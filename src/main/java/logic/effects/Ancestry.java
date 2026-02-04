package logic.effects;

import unit.BaseUnit;

public class Ancestry extends StatusEffect {
    public Ancestry(int stacks) { super("Ancestry", stacks); }
    @Override
    public void onAttackPlayed(BaseUnit owner) {
        owner.setBlock(owner.getBlock() + stacks);
    }
    @Override
    public String getDescription() { return "Gain " + stacks + " Block every time you play an Attack."; }
}