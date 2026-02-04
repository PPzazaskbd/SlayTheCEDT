// === NEW FILE: java/application/GameMenu.java ===

package application;

import logic.buff.StarterBuff;
import unit.Player;

import java.util.Scanner;

/**
 * GameMenu handles the menu flow before run starts
 * Handles: Main Menu → Character Select → Buff Selection → Run Start
 */
public class GameMenu {

    private Scanner scanner;
    private static final String CHARACTER_NAME = "FD Battery";

    public GameMenu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Start the menu flow
     */
    public void start() {
        while (true) {
            displayMainMenu();
            int choice = getValidInput(0, 2);

            switch (choice) {
                case 0:
                    startNewRun();
                    break;
                case 1:
                    displaySettings();
                    break;
                case 2:
                    exitGame();
                    return;
            }
        }
    }

    /**
     * Display main menu
     */
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== SLAY THE CEDT ===");
        System.out.println("=".repeat(60));
        System.out.println("[0] Start New Run");
        System.out.println("[1] Settings");
        System.out.println("[2] Exit");
        System.out.print("\nChoose: ");
    }

    /**
     * Character selection (hardcoded for now)
     */
    private void characterSelect() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== SELECT CHARACTER ===");
        System.out.println("=".repeat(60));
        System.out.println("[0] " + CHARACTER_NAME);
        System.out.println("\n(Only 1 available for now)");
        System.out.print("\nChoose: ");

        int choice = getValidInput(0, 0);
        System.out.println("✓ Selected: " + CHARACTER_NAME);
    }

    /**
     * Buff selection flow
     */
    private StarterBuff buffSelection() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== CHOOSE YOUR BLESSING ===");
        System.out.println("=".repeat(60));

        StarterBuff[] buffs = StarterBuff.values();
        for (int i = 0; i < buffs.length; i++) {
            System.out.println("[" + i + "] " + buffs[i].toString());
        }

        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter choice (0-3): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 0 && choice < buffs.length) {
                    validInput = true;
                } else {
                    System.out.println("❌ Invalid choice! Enter 0-3");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("❌ Invalid input! Enter a number (0-3)");
            }
        }

        return buffs[choice];
    }

    /**
     * Start new run flow
     */
    private void startNewRun() {
        characterSelect();
        StarterBuff selectedBuff = buffSelection();

        // Create player
        Player player = new Player(CHARACTER_NAME, 100, 3, 0, 99);
        selectedBuff.apply(player);

        // Start run
        MapFlow mapFlow = new MapFlow(player);
        boolean won = mapFlow.startRun();

        System.out.println(won ? "\n✓ You escaped the flood!" : "\n✗ You were caught by the flood!");

        System.out.print("\nPress Enter to return to main menu...");
        scanner.nextLine();
        scanner.nextLine();
    }

    /**
     * Settings placeholder
     */
    private void displaySettings() {
        System.out.println("\n[TODO] Settings not yet implemented");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    /**
     * Exit game
     */
    private void exitGame() {
        System.out.println("\nThanks for playing!");
        scanner.close();
    }

    /**
     * Helper: get valid numeric input
     */
    private int getValidInput(int min, int max) {
        int choice = -1;
        boolean valid = false;

        while (!valid) {
            try {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    valid = true;
                } else {
                    System.out.print("❌ Enter " + min + "-" + max + ": ");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.print("❌ Invalid input! Enter a number: ");
            }
        }

        return choice;
    }
}