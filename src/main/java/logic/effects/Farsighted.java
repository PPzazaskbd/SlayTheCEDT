package logic.effects;

public class Farsighted extends StatusEffect {
    public Farsighted(int stacks) { super("Farsighted", stacks); }
    @Override
    public String getDescription() { return "Draw " + stacks + " extra card(s) at the start of your turn."; }
}