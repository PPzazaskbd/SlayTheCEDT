package Unit;

public abstract class BaseUnit {
    private String name;
    private int hp;
    private int maxHp;

    public BaseUnit(String name, int hp, int maxHp) {
        this.name = name;
        this.hp = hp;
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
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
