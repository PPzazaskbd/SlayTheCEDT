package logic.map;

public class Room {
    private   int leftCoordinate;
    private int rightCoordinate;
    private  RoomType type;

    public Room(int l, int r) {
        this.leftCoordinate = ;
        this.floor = l+r;
        this.type = RoomType.COMBAT;
    }

    public Room(int l, int r,RoomType roomType) {
        this.roomCoordinate = new Pair<>(l,r);
        this.floor = l+r;
        this.type = roomType;
    }

    public Pair<Integer, Integer> getRoomCoordinate() {
        return roomCoordinate;
    }

    public int getFloor() {
        return floor;
    }

    public RoomType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomCoordinate=" + roomCoordinate +
                ", floor=" + floor +
                ", type=" + type +
                '}';
    }

    public void setRoomCoordinate(Pair<Integer, Integer> roomCoordinate) {
        this.roomCoordinate = roomCoordinate;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
