package logic.effects;

import unit.BaseUnit;

// logic/effects/Nourished.java
public class Nourished extends StatusEffect {
    public Nourished(int stacks) { super("Nourished", stacks); }
    @Override
    public void onTurnEnd(BaseUnit owner) {
        if (owner instanceof unit.Player p) {
            p.setGold(p.getGold() + stacks);
        }
    }
    @Override
    public String getDescription() { return "Gain " + stacks + " Cacao at the end of your turn."; }
}