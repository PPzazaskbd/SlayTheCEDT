package logic.map;

import unit.Enemy;

import java.util.List;

public class Room {
    private final int leftCoordinate;
    private final int rightCoordinate;
    private final RoomType roomType;
    private final List<Enemy> Enemys;


    public Room(int l, int r, RoomType roomType, List<Enemy> enemys) {
        this.leftCoordinate = l ;
        this.rightCoordinate = r;
        this.roomType = roomType;
        Enemys = enemys;
    }


    public int getFloor() {
        return this.getLeftCoordinate()+this.getRightCoordinate();
    }



    public int getLeftCoordinate() {
        return leftCoordinate;
    }

    public int getRightCoordinate() {
        return rightCoordinate;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public List<Enemy> getEnemys() {
        return Enemys;
    }
}
