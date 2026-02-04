package logic.effects;

/**
 * Factory to create StatusEffects from names.
 * Used by CardBuilder to instantiate effects dynamically.
 */
public class StatusEffectFactory {

    public static StatusEffect create(String name, int stacks, int duration) {
        // Handle Aztec/Thematic names mapping to code classes if needed
        // For now, mapping direct class names

        switch (name) {
            case "Bleeding":
                return new Bleeding(stacks, duration);
            case "Inflamed":
                return new Inflamed(stacks, duration);
            case "Cursed":
                return new Cursed(stacks, duration);
            case "Farsighted":
                return new Farsighted(stacks, duration);
            // Add other effects here
            default:
                // Fallback for effects that don't have the duration constructor yet
                System.out.println("Warning: Effect " + name + " factory not fully implemented.");
                return null;
        }
    }
}