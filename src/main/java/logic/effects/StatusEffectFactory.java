package logic.effects;

/**
 * Factory to create StatusEffects from names.
 * Used by CardBuilder to instantiate effects dynamically.
 */
public class StatusEffectFactory {

    public static StatusEffect create(String name, int stacks, int duration) {
        switch (name) {
            case "Ancestry":
                return new Ancestry(stacks, duration);
            case "Bleeding":
                return new Bleeding(stacks, duration);
            case "Cursed":
                return new Cursed(stacks, duration);
            case "Farsighted":
                return new Farsighted(stacks, duration);
            case "Hollowed":
                return new Hollowed(stacks, duration);
            case "Inflamed":
                return new Inflamed(stacks, duration);
            case "Nourished":
                return new Nourished(stacks, duration);
            case "Parched":
                return new Parched(stacks, duration);
            case "Starving":
                return new Starving(stacks, duration);
            case "Unyielding":
                return new Unyielding(stacks, duration);
            default:
                System.out.println("Warning: Effect " + name + " factory not fully implemented.");
                return null;
        }
    }
}