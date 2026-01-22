package Unit;

import java.util.ArrayList;

public class Enemy extends BaseUnit {
    String name;
    int hp;
    int maxHp;

    ArrayList<EnemyIntent> enemyIntentPattern = new ArrayList<>();
    // buff , debuff and more


    public Enemy( String name, int hp,int maxHp ) {
        super(name,hp,maxHp);
        setBeginPattern();
        this.maxHp = maxHp;
        this.hp = hp;
        this.name = name;

    }

    public void setBeginPattern() {
        this.enemyIntentPattern.add(new EnemyIntent(EnemyIntentType.ATTACK,11));
        this.enemyIntentPattern.add(new EnemyIntent(EnemyIntentType.BLOCK ,7));
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", enemyIntentPattern=" + enemyIntentPattern +
                '}';
    }
}

