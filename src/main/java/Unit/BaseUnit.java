package unit;

public abstract class BaseUnit {
    private String name;
    private int hp;
    private int maxHp;

    public BaseUnit(String name, int maxHp) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
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
}
