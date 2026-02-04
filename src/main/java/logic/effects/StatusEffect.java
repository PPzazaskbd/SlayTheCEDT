package logic.effects;

import unit.BaseUnit;

public abstract class StatusEffect {
    protected String name;
    protected int stacks;
    protected int duration; // -1 = permanent, 0+ = turns remaining

    public StatusEffect(String name, int stacks) {
        this.name = name;
        this.stacks = stacks;
        this.duration = -1;  // Permanent
    }

    public StatusEffect(String name, int stacks, int duration) {
        this.name = name;
        this.stacks = stacks;
        this.duration = duration;
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
    public void decreaseDuration() {
        if (duration > 0) duration--;
    }

    public void resetStacks() { this.stacks = 0; }

    public int getDuration() { return duration; }

    public abstract String getDescription();

    public String getName() { return name; }

    public int getStacks() { return stacks; }
}