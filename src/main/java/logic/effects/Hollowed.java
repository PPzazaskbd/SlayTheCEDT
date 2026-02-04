package logic.effects;

import unit.BaseUnit;

public class Hollowed extends StatusEffect {
    public Hollowed(int stacks) { super("Hollowed", stacks); }
    @Override
    public void onTurnStart(BaseUnit owner) {
        if (owner instanceof unit.Player p) {
            p.setCurrentEnergy(Math.max(0, p.getCurrentEnergy() - stacks));
        }
    }
    @Override
    public String getDescription() { return "Start your turn with " + stacks + " less Energy."; }
}