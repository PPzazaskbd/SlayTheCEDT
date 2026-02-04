package logic.effects;

import unit.BaseUnit;

public class Starving extends StatusEffect {
    public Starving(int stacks) { super("Starving", stacks); }
    @Override
    public void onTurnEnd(BaseUnit owner) {
        if (owner instanceof unit.Player) {
            unit.Player p = (unit.Player) owner;
            p.setGold(Math.max(0, p.getGold() - stacks));
        }
    }
    @Override
    public String getDescription() { return "Lose " + stacks + " Cacao at the end of your turn."; }
}
