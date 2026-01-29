package unit;

public class EnemyIntent {
    EnemyIntentType enemyIntentType;
    int count;
    String  intentMessage;


    public EnemyIntent(EnemyIntentType enemyIntentType, int count) {
        this.enemyIntentType = enemyIntentType;
        this.count = count;
        this.intentMessage = this.enemyIntentType  + " " + Integer.toString(count);
    }

    public EnemyIntentType getEnemyIntentType() {
        return enemyIntentType;
    }

    public void setEnemyIntentType(EnemyIntentType enemyIntentType) {
        this.enemyIntentType = enemyIntentType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;

    }

    public String getIntentMessage() {
        return intentMessage;
    }

    public void setIntentMessage(String intentMessage) {
        this.intentMessage = intentMessage;
    }

    @Override
    public String toString() {
        return "\nEnemyIntent{" +
                "enemyIntentType=" + enemyIntentType +
                ", count=" + count +
                ", intentMessage='" + intentMessage + '\'' +
                "}";
    }
}


