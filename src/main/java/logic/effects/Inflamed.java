package logic.effects;

public class Inflamed extends StatusEffect {
    public Inflamed(int stacks) { super("Inflamed", stacks); }
    @Override
    public int modifyDamageDealt(int damage) { return damage + stacks; }
    @Override
    public String getDescription() { return "Deal " + stacks + " additional damage with Attacks."; }
}