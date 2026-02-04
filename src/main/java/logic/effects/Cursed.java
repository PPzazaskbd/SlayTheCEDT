package logic.effects;

public class Cursed extends StatusEffect {
    public Cursed(int stacks) { super("Cursed", stacks); }
    public Cursed(int stacks, int duration) { super("Cursed", stacks, duration); }
    @Override
    public int modifyDamageReceived(int damage) { return damage + stacks; }
    @Override
    public String getDescription() { return "Take " + stacks + " additional damage when hit."; }
}