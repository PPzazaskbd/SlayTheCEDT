// === MODIFIED: java/application/CombatFlow.java (complete version with Xipe fix) ===

package application;

import logic.card.Card;
import unit.Enemy;
import unit.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * CombatFlow handles the turn-based combat loop.
 * Follows the flowchart: combat → resetBlock → turn++ → draw → play cards → enemy turn → check win/lose
 */
public class CombatFlow {

    // === Combat State ===
    private Player player;
    private ArrayList<Enemy> enemies;
    private int turnCount;
    private boolean combatOver;

    // === Deck Management ===
    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    // === Constants ===
    private static final int HAND_SIZE = 5;

    /**
     * Constructor - initializes combat with player and enemies
     */
    public CombatFlow(Player player, ArrayList<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.turnCount = 0;
        this.combatOver = false;

        // Initialize piles
        this.drawPile = new ArrayList<>(player.getDeck());
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();

        // Shuffle draw pile at combat start
        Collections.shuffle(drawPile);
    }

    /**
     * Main combat loop - runs until win or lose
     * @return true if player wins, false if player loses
     */
    public boolean startCombat() {
        System.out.println("\n=== COMBAT START ===");
        System.out.println("Enemy: " + enemies.get(0).getName() + " (HP: " + enemies.get(0).getHp() + ")");
        System.out.println("Your HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Deck size: " + drawPile.size());

        while (!combatOver) {
            // --- Start of turn ---
            turnCount++;
            System.out.println("\n" + "=".repeat(50));
            System.out.println("TURN " + turnCount);
            System.out.println("=".repeat(50));

            // Step 1: Reset block
            resetBlock();

            // Step 2: Refill energy
            player.setCurrentEnergy(player.getMaxEnergy());
            System.out.println("Energy restored to: " + player.getCurrentEnergy());

            // Step 3: Draw cards
            drawCards(HAND_SIZE);
            printHand();

            // Step 4: Player turn - play cards until end turn
            playerTurn();

            // Step 5: Check if all enemies dead after player turn
            if (checkAllEnemiesDead()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("=== VICTORY ===");
                System.out.println("=".repeat(50));
                System.out.println("get rewards");
//                sout (getrewards());
                return true;
            }

            // Step 6: Discard remaining hand
            discardHand();

            // Step 7: Enemy turn
            enemyTurn();

            // Step 8: Check if player dead after enemy turn
            if (checkPlayerDead()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("=== DEFEAT ===");
                System.out.println("=".repeat(50));
                return false;
            }
        }

        return false;
    }

    /**
     * Reset player's block to 0 at start of turn
     */
    private void resetBlock() {
        player.setBlock(0);
        System.out.println("[TURN START] Block reset to 0");
    }

    /**
     * Draw cards from draw pile to hand
     * Shuffles discard pile into draw pile if needed
     */
    private void drawCards(int count) {
        for (int i = 0; i < count; i++) {
            // If draw pile empty, shuffle discard into draw
            if (drawPile.isEmpty()) {
                if (discardPile.isEmpty()) {
                    System.out.println("No cards left to draw!");
                    return;
                }
                shuffleDiscardIntoDraw();
            }

            // Draw top card
            Card drawnCard = drawPile.remove(0);
            hand.add(drawnCard);
        }
        System.out.println("[DRAW] Drew " + count + " cards. Draw pile: " + drawPile.size() + " left");
    }

    /**
     * Shuffle discard pile back into draw pile
     */
    private void shuffleDiscardIntoDraw() {
        System.out.println("[SHUFFLE] Shuffling discard pile (" + discardPile.size() + " cards) into draw pile...");
        drawPile.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(drawPile);

        // Xipe Totec blessing: next card is free
        if (player.hasXipeTotecBlessing()) {
            player.setFreeCardNextTurn(true);
            System.out.println("[XIPE TOTEC] Next card played is FREE!");
        }
    }

    /**
     * Player turn - loop until player chooses to end turn
     */
    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n[YOUR TURN] Energy: " + player.getCurrentEnergy() +
                    " | Block: " + player.getBlock() +
                    " | HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("Enter card index to play (0-" + (hand.size() - 1) + ") or -1 to end turn:");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // clear buffer
                System.out.println("Invalid input!");
                continue;
            }

            // === CHEAT CODE: 67 = instant win ===
            if (choice == 67) {
                System.out.println("🎮 CHEAT: Instant victory!");
                // Kill all enemies
                for (Enemy enemy : enemies) {
                    enemy.setHp(0);
                }
                return; // exit player turn and trigger victory check
            }

            // End turn
            if (choice == -1) {
                System.out.println("Ending turn...");
                break;
            }

            // Validate choice
            if (choice < 0 || choice >= hand.size()) {
                System.out.println("Invalid card index!");
                continue;
            }

            // Try to play the card
            Card selectedCard = hand.get(choice);
            if (tryPlayCard(selectedCard, choice)) {
                // Check if enemies died mid-turn
                if (checkAllEnemiesDead()) {
                    return;
                }
            }

            // Print updated hand
            printHand();
        }
    }

    /**
     * Attempt to play a card
     * @return true if card was played successfully
     */
    private boolean tryPlayCard(Card card, int handIndex) {
        int actualCost = card.getCost();

        // Xipe Totec: first card after shuffle is free
        if (player.isFreeCardNextTurn()) {
            actualCost = 0;
            player.setFreeCardNextTurn(false);
            System.out.println("[XIPE TOTEC] This card is FREE!");
        }

        // Check if enough energy
        if (player.getCurrentEnergy() < actualCost) {
            System.out.println("❌ Not enough energy! Card costs " + actualCost +
                    ", you have " + player.getCurrentEnergy());
            return false;
        }

        // Deduct energy
        player.setCurrentEnergy(player.getCurrentEnergy() - actualCost);

        // Execute card effect (polymorphism!)
        System.out.println("\n▶ Playing: " + card.getName() + " (Cost: " + actualCost + ")");
        card.execute(player, enemies);

        // Move card from hand to discard
        hand.remove(handIndex);
        discardPile.add(card);

        // Check if player died
        if (checkPlayerDead()) {
            combatOver = true;
        }

        return true;
    }

    /**
     * Check if card is playable (enough energy)
     */
    private boolean isPlayable(Card card) {
        return player.getCurrentEnergy() >= card.getCost();
    }

    /**
     * Discard all cards in hand at end of turn
     */
    private void discardHand() {
        if (!hand.isEmpty()) {
            discardPile.addAll(hand);
            int discarded = hand.size();
            hand.clear();
            System.out.println("[END TURN] Discarded " + discarded + " cards");
        }
    }

    /**
     * Enemy turn - all enemies execute their intent
     */
    private void enemyTurn() {
        System.out.println("\n--- ENEMY TURN ---");

        for (Enemy enemy : enemies) {
            // Skip dead enemies
            if (enemy.getHp() <= 0) continue;

            // Execute enemy intent
            executeEnemyIntent(enemy);

            // Check if player died
            if (checkPlayerDead()) {
                combatOver = true;
                return;
            }
        }

        // Print player status after enemy turn
        System.out.println("[AFTER ENEMY] Your HP: " + player.getHp() + "/" + player.getMaxHp() +
                " | Block: " + player.getBlock());
    }

    /**
     * Execute a single enemy's intent
     */
    private void executeEnemyIntent(Enemy enemy) {
        // Placeholder: always attack with 5 damage
        // TODO: Use enemy.enemyIntentPattern for more variety

        int damage = 5;
        System.out.println(enemy.getName() + " attacks for " + damage + " damage!");

        // Apply damage (block mitigates it)
        int actualDamage = Math.max(0, damage - player.getBlock());
        player.setBlock(Math.max(0, player.getBlock() - damage));
        player.setHp(player.getHp() - actualDamage);

        System.out.println("  ↳ You take " + actualDamage + " damage. (Block absorbed: " +
                Math.min(player.getBlock() + actualDamage, damage) + ")");
        System.out.println("  ↳ Your HP: " + player.getHp() + "/" + player.getMaxHp());
    }

    /**
     * Check if player is dead
     */
    private boolean checkPlayerDead() {
        return player.getHp() <= 0;
    }

    /**
     * Check if all enemies are dead
     */
    private boolean checkAllEnemiesDead() {
        for (Enemy enemy : enemies) {
            if (enemy.getHp() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Print current hand to console
     */
    private void printHand() {
        System.out.println("\n📋 YOUR HAND:");
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            System.out.println("  [" + i + "] " + card.getName() +
                    " (Cost: " + card.getCost() + ") - " + card.getDescription());
        }
    }

    // === Getters for testing ===

    public int getTurnCount() {
        return turnCount;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getDrawPile() {
        return drawPile;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }
}