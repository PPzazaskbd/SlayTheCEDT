// === MODIFIED: java/application/MapFlow.java ===

package application;

import logic.map.Map;
import logic.map.Room;
import logic.map.Pair;
import logic.map.RoomType;
import unit.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MapFlow handles navigation through the map, room selection, and floor progression
 * Player starts at (0,0) with floor=0
 * Victory condition: reach (7,7)
 * One-way progression enforced by: x' + y' <= currentFloor
 *
 * Grid rotated 45°: LEFT = (x+1, y), RIGHT = (x, y+1)
 */
public class MapFlow {

    private Map map;
    private Player player;
    private int currentFloor;
    private Pair<Integer, Integer> currentPosition;
    private boolean runOver;

    private static final int VICTORY_X = 7;
    private static final int VICTORY_Y = 7;

    /**
     * Constructor - initialize map flow with player
     */
    public MapFlow(Player player) {
        this.map = new Map();
        this.player = player;
        this.currentFloor = 0;
        this.currentPosition = new Pair<>(0, 0);
        this.runOver = false;
    }

    /**
     * Main run loop - navigate map, fight combats, progress floors
     * @return true if player wins (reaches 7,7), false if dies
     */
    public boolean startRun() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== RUN START ===");
        System.out.println("=".repeat(60));
        System.out.println("Player: " + player.getName());
        System.out.println("Goal: Reach (" + VICTORY_X + "," + VICTORY_Y + ") to escape the flood");
        System.out.println("Starting position: (0, 0)");
        System.out.println("Starting floor: " + currentFloor);

        while (!runOver) {
            // Display current state
            displayMapStatus();

            // Ask for next move
            Pair<Integer, Integer> nextPos = getPlayerMove();
            if (nextPos == null) continue; // invalid move, ask again

            // Move to new position
            currentPosition = nextPos;
            System.out.println("\n▶ Moving to (" + currentPosition.first() + "," + currentPosition.second() + ")");

            // Get room and execute
            Room room = map.getRoomAt(currentPosition.first(), currentPosition.second());
            if (room == null) {
                System.out.println("❌ ERROR: Room not found!");
                continue;
            }

            // Execute room (TODO: handle different room types)
            if (room.getRoomType() == RoomType.COMBAT) {
                boolean combatWon = executeCombat(room);

                if (!combatWon) {
                    System.out.println("\n" + "=".repeat(60));
                    System.out.println("=== RUN FAILED ===");
                    System.out.println("=".repeat(60));
                    return false;
                }
            }

            // Increment floor after successful combat
            currentFloor++;
            System.out.println("\n[FLOOR PROGRESSED] Now on floor: " + currentFloor);

            // Check victory condition
            if (currentPosition.first() == VICTORY_X && currentPosition.second() == VICTORY_Y) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("=== VICTORY ===");
                System.out.println("You reached (7,7)! Escaped the flood!");
                System.out.println("=".repeat(60));
                return true;
            }
        }

        return false;
    }

    /**
     * Display current map status
     */
    private void displayMapStatus() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("Position: (" + currentPosition.first() + "," + currentPosition.second() + ")");
        System.out.println("Floor: " + currentFloor);
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Cacao: " + player.getGold());
        System.out.println("─".repeat(60));
    }

    /**
     * Ask player for next move: left (x+1,y) or right (x,y+1)
     * Validate reachability: x' + y' <= currentFloor
     */
    private Pair<Integer, Integer> getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int x = currentPosition.first();
        int y = currentPosition.second();

        // Option 1: LEFT - move (x+1, y)
        int leftX = x + 1;
        int leftY = y;
        // Allow movement to next floor level
        boolean leftValid = leftX + leftY <= (currentFloor + 1) && map.getRoomAt(leftX, leftY) != null;

        // Option 2: RIGHT - move (x, y+1)
        int rightX = x;
        int rightY = y + 1;
        // Allow movement to next floor level
        boolean rightValid = rightX + rightY <= (currentFloor + 1) && map.getRoomAt(rightX, rightY) != null;

        // Display valid options
        System.out.println("\n📍 Available moves:");
        if (leftValid) {
            System.out.println("[1] LEFT to (" + leftX + "," + leftY + ") ✓");
        } else {
            System.out.println("[1] LEFT to (" + leftX + "," + leftY + ") ✗ BLOCKED (flood)");
        }

        if (rightValid) {
            System.out.println("[2] RIGHT to (" + rightX + "," + rightY + ") ✓");
        } else {
            System.out.println("[2] RIGHT to (" + rightX + "," + rightY + ") ✗ BLOCKED (flood)");
        }

        System.out.print("\nChoose [1/2]: ");

        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input!");
            return null;
        }

        if (choice == 1 && leftValid) {
            return new Pair<>(leftX, leftY);
        } else if (choice == 2 && rightValid) {
            return new Pair<>(rightX, rightY);
        } else {
            System.out.println("❌ That move is blocked!");
            return null;
        }
    }

    /**
     * Execute combat in a room
     * TODO: Handle different enemy types, varied rewards, card offerings
     */
    private boolean executeCombat(Room room) {
        System.out.println("\n[⚔️ COMBAT ROOM] Fighting " + room.getEnemys().size() + " enemy/enemies");

        // Run combat
        CombatFlow combat = new CombatFlow(player, new ArrayList<>(room.getEnemys()));
        boolean won = combat.startCombat();

        if (won) {
            // TODO: Implement proper reward system
            System.out.println("\n[🎁 REWARDS]");
            System.out.println("TODO: Random reward generation");
            System.out.println("TODO: Card choice screen");
            System.out.println("TODO: Cacao amount based on difficulty");

            // Placeholder reward
            int cacaoReward = 50;
            player.setGold(player.getGold() + cacaoReward);
            System.out.println("✓ Gained " + cacaoReward + " cacao");
        }

        return won;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Pair<Integer, Integer> getCurrentPosition() {
        return currentPosition;
    }
}