package logic.effects;

public class Unyielding extends StatusEffect {
    public Unyielding(int stacks) { super("Unyielding", stacks); }
    public Unyielding(int stacks, int duration) { super("Unyielding", stacks, duration); }
    @Override
    public String getDescription() { return "Block is no longer reset to 0 at the start of your turn."; }
}
