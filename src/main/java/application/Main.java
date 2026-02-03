// === MODIFIED: java/application/Main.java ===

package application;

import logic.buff.StarterBuff;
import unit.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // === BUFF SELECTION ===
        System.out.println("=== CHOOSE YOUR BLESSING ===");
        StarterBuff[] buffs = StarterBuff.values();
        for (int i = 0; i < buffs.length; i++) {
            System.out.println("[" + i + "] " + buffs[i].toString());
        }
        System.out.print("Enter choice (0-3): ");
        int choice = scanner.nextInt();

        // Validate
        if (choice < 0 || choice >= buffs.length) {
            choice = 0; // default to first
        }
        StarterBuff selectedBuff = buffs[choice];

        // === CREATE PLAYER ===
        Player player = new Player("PP", 100, 3, 0, 99);

        // === APPLY BUFF ===
        selectedBuff.apply(player);

        // === START RUN ===
        MapFlow mapFlow = new MapFlow(player);
        boolean won = mapFlow.startRun();

        System.out.println(won ? "\n✓ You escaped the flood!" : "\n✗ You were caught by the flood!");
    }
}