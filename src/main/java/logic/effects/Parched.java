package logic.effects;

public class Parched extends StatusEffect {
    public Parched(int stacks) { super("Parched", stacks); }
    @Override
    public int modifyBlockGained(int block) { return Math.max(0, block - stacks); }
    @Override
    public String getDescription() { return "Reduce Block gained from cards by " + stacks + "."; }
}
