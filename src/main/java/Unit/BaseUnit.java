package unit;

import logic.effects.StatusEffect;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseUnit {
    private String name;
    private int hp;
    private int maxHp;
    private int block;
    private List<StatusEffect> activeEffects = new ArrayList<>();
    public BaseUnit(String name, int maxHp) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.block = 0;
    }


    public void addStatus(StatusEffect newEffect) {
        for (StatusEffect e : activeEffects) {
            if (e.getClass().equals(newEffect.getClass())) {
                e.addStacks(newEffect.getStacks());
                return;
            }
        }
        activeEffects.add(newEffect);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.max(maxHp,0);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp,0);
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public List<StatusEffect> getActiveEffects() {
        return activeEffects;
    }

    public void setActiveEffects(List<StatusEffect> activeEffects) {
        this.activeEffects = activeEffects;
    }
}
