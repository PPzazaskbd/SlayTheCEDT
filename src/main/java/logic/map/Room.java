package logic.map;

public class Room {
    public final Pair<Integer,Integer> roomCoordinate;
    private final int floor;
    private final RoomType type;

    public Room(int l, int r, RoomType type) {
        this.roomCoordinate = new Pair<>(l,r);
        this.floor = l+r;
        this.type = type;
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
}
