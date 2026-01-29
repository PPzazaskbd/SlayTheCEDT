package logic.map;

import java.util.HashSet;
import java.util.Set;

public class Map {
    private final HashSet<Room> rooms;
    public Map() {
        this.rooms = new HashSet<>();
        rooms.add(new Room(0,0,RoomType.START));
        rooms.add(new Room(0,1,RoomType.COMBAT));
        rooms.add(new Room(1,0,RoomType.COMBAT));
        rooms.add(new Room(1,1,RoomType.TREASURE));
    }

    public HashSet<Room> getRooms() {
        return rooms;
    }
}
