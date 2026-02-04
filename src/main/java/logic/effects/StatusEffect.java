package logic.effects;

import unit.BaseUnit;

public abstract class StatusEffect {
    protected String name;
    protected int stacks;

    public StatusEffect(String name, int stacks) {
        this.name = name;
        this.stacks = stacks;
    }

    // --- Hooks ---
    public void onTurnStart(BaseUnit owner) {}
    public void onTurnEnd(BaseUnit owner) {}
    public int modifyDamageDealt(int damage) { return damage; }
    public int modifyDamageReceived(int damage) { return damage; }
    public int modifyBlockGained(int block) { return block; }
    public void onAttackPlayed(BaseUnit owner) {}

    // --- Core Logic ---
    public void addStacks(int amount) { this.stacks += amount; }

    public void decreaseStacks(int amount) {
        this.stacks = Math.max(0, this.stacks - amount);
    }

    public void resetStacks() { this.stacks = 0; }

    public abstract String getDescription();

    public String getName() { return name; }
    public int getStacks() { return stacks; }
}