package logic.effects;
import logic.DamageEngine;
import unit.BaseUnit;

public class Ancestry extends StatusEffect {
    public Ancestry(int stacks) { super("Ancestry", stacks); }
    @Override
    public void onAttackPlayed(BaseUnit owner) {
        // CHANGED: Use DamageEngine to respect Parched
        int blockGained = DamageEngine.applyBlockGain(owner, stacks);
        owner.setBlock(owner.getBlock() + blockGained);
        System.out.println("[ANCESTRY] Gained " + blockGained + " block from playing Attack!");
    }
    @Override
    public String getDescription() {
        return "Gain " + stacks + " Block every time you play an Attack.";
    }
}