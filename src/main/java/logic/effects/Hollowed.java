package logic.effects;

public class Hollowed extends StatusEffect {
    public Hollowed(int stacks) { super("Hollowed", stacks); }
    @Override
    public void onTurnStart(BaseUnit owner) {
        if (owner instanceof unit.Player) {
            unit.Player p = (unit.Player) owner;
            p.setCurrentEnergy(Math.max(0, p.getCurrentEnergy() - stacks));
        }
    }
    @Override
    public String getDescription() { return "Start your turn with " + stacks + " less Energy."; }
}