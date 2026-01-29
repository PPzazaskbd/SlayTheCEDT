package logic.map;

import java.util.HashSet;

public class Map {
    private final HashSet<Room> rooms;
    private Room currentRoom;
    public Map() {
        this.rooms = new HashSet<>();
        rooms.add(new Room(0,0,RoomType.START));
        rooms.add(new Room(0,1,RoomType.COMBAT));
        rooms.add(new Room(1,0,RoomType.COMBAT));
        rooms.add(new Room(1,1,RoomType.TREASURE));
        currentRoom = new Room(0,0);
    }

    public HashSet<Room> getRooms() {
        return rooms;
    }

    public boolean isRoomexistInMap(Room room) {
        return rooms.contains(room);
    }
    public Room walk(Direction direction) {
        if (direction == Direction.LEFT) {
            if (isRoomexistInMap(new Room(this.currentRoom.getRoomCoordinate().first()+1,this.currentRoom.getRoomCoordinate().second()))) {
                this.currentRoom.setRoomCoordinate( new Pair<>(this.currentRoom.getRoomCoordinate().first()+1,this.currentRoom.getRoomCoordinate().second()));
            }
        }

    }
}
