package logic.map;

import unit.Enemy;

import java.util.*;

public class Map {
    private final HashMap<Pair<Integer,Integer>,Room> rooms;
    private Pair<Integer,Integer> currentRoom;
    public Map() {
        this.rooms = new HashMap<>();

        generateMap();
        currentRoom = new Pair<>(0,0);
    }




    public Room walk(Direction direction) {
        Pair<Integer, Integer> newCoord;

        if (direction == Direction.LEFT) {
            newCoord = new Pair<>(currentRoom.first() + 1, currentRoom.second());
        } else {
            newCoord = new Pair<>(currentRoom.first(), currentRoom.second() + 1);
        }

        Room newRoom = rooms.get(newCoord);
        if (newRoom != null) {
            currentRoom = newCoord;
            return newRoom;
        }
        return null;
    }

    public boolean addRoom(Room room) {
        if (rooms.containsKey(new Pair<>(room.getLeftCoordinate()-1,room.getRightCoordinate())) ||  rooms.containsKey(new Pair<>(room.getLeftCoordinate(),room.getRightCoordinate()-1)) ) {
            rooms.put(new Pair<>(room.getLeftCoordinate(),room.getRightCoordinate()),room);
            return true;
        }
        return false;
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoom);
    }
    /**
     * Initialize the entire map with rooms based on grid structure
     * Messy version - just populate all valid coordinates
     */
    /**
     * Initialize the entire map with rooms based on grid structure
     * Uses addRoom to enforce connectivity
     */
    private void generateMap() {
        // First room at (0,0) - special case, no adjacency check
        rooms.put(new Pair<>(0, 0), new Room(0, 0, RoomType.COMBAT, createEnemies(0)));

        // x=0: y ∈ [1,3]
        for (int y = 1; y <= 3; y++) {
            addRoom(new Room(0, y, RoomType.COMBAT, createEnemies(0)));
        }

        // x=1: y ∈ [0,4]
        for (int y = 0; y <= 4; y++) {
            addRoom(new Room(1, y, RoomType.COMBAT, createEnemies(1)));
        }

        // x=2: y ∈ [0,4]
        for (int y = 0; y <= 4; y++) {
            addRoom(new Room(2, y, RoomType.COMBAT, createEnemies(2)));
        }

        // x=3: y ∈ [0,7]
        for (int y = 0; y <= 7; y++) {
            addRoom(new Room(3, y, RoomType.COMBAT, createEnemies(3)));
        }

        // x=4: y ∈ [1,7]
        for (int y = 1; y <= 7; y++) {
            addRoom(new Room(4, y, RoomType.COMBAT, createEnemies(4)));
        }

        // x=5: y ∈ [3,7]
        for (int y = 3; y <= 7; y++) {
            addRoom(new Room(5, y, RoomType.COMBAT, createEnemies(5)));
        }

        // x=6: y ∈ [3,7]
        for (int y = 3; y <= 7; y++) {
            addRoom(new Room(6, y, RoomType.COMBAT, createEnemies(6)));
        }

        // x=7: y ∈ [3,7]
        for (int y = 3; y <= 7; y++) {
            addRoom(new Room(7, y, RoomType.COMBAT, createEnemies(7)));
        }

        currentRoom = new Pair<>(0, 0);
        System.out.println("Map initialized with " + rooms.size() + " rooms");
    }

    public Room getRoomAt(int x, int y) {
        return rooms.get(new Pair<>(x, y));
    }

    /**
     * Create enemies for a room based on floor difficulty
     */
    //TODO
    private ArrayList<Enemy> createEnemies(int floor) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        int enemyHp = 20 + (floor * 5); // scales by floor
        enemies.add(new Enemy("Enemy_" + floor, enemyHp, enemyHp));
        return enemies;
    }
}
