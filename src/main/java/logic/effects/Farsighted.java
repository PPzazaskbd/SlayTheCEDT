package logic.effects;

public class Farsighted extends StatusEffect {
    public Farsighted(int stacks) { super("Farsighted", stacks); }
    public Farsighted(int stacks, int duration) { super("Farsighted", stacks, duration); }
    @Override
    public String getDescription() { return "Draw " + stacks + " extra card(s) at the start of your turn."; }
}