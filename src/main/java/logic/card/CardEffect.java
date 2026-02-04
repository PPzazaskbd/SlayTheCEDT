package logic.card;

public record CardEffect(String effectName, int stacks, int duration, Target target) {
    public enum Target {
        SELF,
        ENEMY
    }
}
