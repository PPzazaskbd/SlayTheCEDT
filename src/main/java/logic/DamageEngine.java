package logic;

import logic.effects.StatusEffect;
import unit.BaseUnit;

public class DamageEngine {

    // Static method = anyone can call DamageEngine.applyDamage()
    public static void applyDamage(BaseUnit source, BaseUnit target, int damage) {
        // Source modifies damage (Inflamed)
        for (StatusEffect e : source.getActiveEffects()) {
            damage = e.modifyDamageDealt(damage);
        }
        // Target modifies damage received (Cursed)
        for (StatusEffect e : target.getActiveEffects()) {
            damage = e.modifyDamageReceived(damage);
        }
        int actualDmg = Math.max(0, damage - target.getBlock());
        target.setBlock(Math.max(0, target.getBlock() - damage));
        target.setHp(target.getHp() - actualDmg);
        System.out.println(source.getName() + " deals " + damage + " DMG (" +
                actualDmg + " to HP) to " + target.getName());
    }

    // Similar for block
    public static int applyBlockGain(BaseUnit owner, int blockAmount) {
        for (StatusEffect e : owner.getActiveEffects()) {
            blockAmount = e.modifyBlockGained(blockAmount);
        }
        return blockAmount;
    }
}
