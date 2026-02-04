package application;

import logic.DamageEngine;
import logic.card.Card;
import logic.card.AttackCard;
import logic.effects.StatusEffect;
import logic.effects.Unyielding;
import unit.Enemy;
import unit.Player;
import unit.BaseUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * CombatFlow handles the turn-based combat loop.
 * Integrates StatusEffect hooks for Aztec buffs/debuffs.
 */
public class CombatFlow {

    private Player player;
    private ArrayList<Enemy> enemies;
    private int turnCount;
    private boolean combatOver;

    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    private static final int BASE_HAND_SIZE = 5;

    public CombatFlow(Player player, ArrayList<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.turnCount = 0;
        this.combatOver = false;

        this.drawPile = new ArrayList<>(player.getDeck());
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();

        Collections.shuffle(drawPile);
    }

    public boolean startCombat() {
        System.out.println("\n=== COMBAT START ===");
        System.out.println("Enemy: " + enemies.get(0).getName() + " (HP: " + enemies.get(0).getHp() + ")");

        while (!combatOver) {
            turnCount++;
            System.out.println("\n" + "=".repeat(50));
            System.out.println("TURN " + turnCount);
            System.out.println("=".repeat(50));

            // --- 1. Start of Turn ---
            // Tangled: Unyielding logic
            if (!player.hasStatus(Unyielding.class)) {
                resetBlock(player);
            } else {
                System.out.println("[STATUS] Unyielding: Block preserved!");
            }


            // Trigger Start Hooks (e.g., Hollowed energy reduction)
            player.setCurrentEnergy(player.getMaxEnergy());
            triggerStatusHooks(player, "start");

            // Tangled: Farsighted draw logic
            int cardsToDraw = BASE_HAND_SIZE + player.getStatusStacks("Farsighted");
            drawCards(cardsToDraw);

            printHand();

            // --- 2. Player Turn ---
            playerTurn();

            if (checkAllEnemiesDead()) {
                System.out.println("\n=== VICTORY ===");
                cleanupCombat(); // Reset for next room
                return true;
            }

            // --- 3. End of Turn ---
            triggerStatusHooks(player, "end"); // e.g. Bleeding, Nourished
            discardHand();

            // --- 4. Enemy Turn ---
            enemyTurn();

            if (checkPlayerDead()) {
                System.out.println("\n=== DEFEAT ===");
                return false;
            }

            // --- 5. Decrement durations ---
            decrementDurations(player);
            for (Enemy e : enemies) {
                decrementDurations(e);
            }
        }
        return false;
    }

    private void resetBlock(BaseUnit unit) {
        unit.setBlock(0);
        System.out.println("[TURN START] " + unit.getName() + "'s Block reset to 0");
    }

    private void drawCards(int count) {
        for (int i = 0; i < count; i++) {
            if (drawPile.isEmpty()) {
                if (discardPile.isEmpty()) break;
                shuffleDiscardIntoDraw();
            }
            hand.add(drawPile.remove(0));
        }
        System.out.println("[DRAW] Drew " + count + " cards.");
    }

    private void shuffleDiscardIntoDraw() {
        System.out.println("[SHUFFLE] Draw pile empty. Shuffling discard...");
        drawPile.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(drawPile);
        if (player.hasXipeTotecBlessing()) {
            player.setFreeCardNextTurn(true);
            System.out.println("[XIPE TOTEC] Blessing activated! Next card is free.");
        }
    }

    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n[YOUR TURN] Energy: " + player.getCurrentEnergy() +
                    " | Block: " + player.getBlock() +
                    " | HP: " + player.getHp());
            System.out.println("Enter card index (0-" + (hand.size() - 1) + ") or -1 to end:");

            int choice = -1;
            try { choice = scanner.nextInt(); } catch (Exception e) { scanner.nextLine(); continue; }

            if (choice == 67) { for (Enemy e : enemies) e.setHp(0); return; }
            if (choice == -1) break;
            if (choice < 0 || choice >= hand.size()) continue;

            Card card = hand.get(choice);
            if (tryPlayCard(card, choice)) {
                if (checkAllEnemiesDead()) return;
            }
            printHand();
        }
    }

    private boolean tryPlayCard(Card card, int handIndex) {
        int actualCost = player.isFreeCardNextTurn() ? 0 : card.getCost();

        if (player.getCurrentEnergy() < actualCost) {
            System.out.println("❌ Not enough energy!");
            return false;
        }

        if (player.isFreeCardNextTurn()) player.setFreeCardNextTurn(false);
        player.setCurrentEnergy(player.getCurrentEnergy() - actualCost);

        System.out.println("\n▶ Playing: " + card.getName());
        card.execute(player, enemies);

        // Tangled: Ancestry check (grant block when attacking)
        if (card instanceof AttackCard) {
            for (StatusEffect e : player.getActiveEffects()) {
                e.onAttackPlayed(player);
            }
        }

        hand.remove(handIndex);
        discardPile.add(card);
        return true;
    }

    private void enemyTurn() {
        System.out.println("\n--- ENEMY TURN ---");
        for (Enemy enemy : enemies) {
            if (enemy.getHp() <= 0) continue;
            triggerStatusHooks(enemy, "start");

            // Use DamageEngine for enemy attacks
            int baseDamage = 5;
            DamageEngine.applyDamage(enemy, player, baseDamage);  // CHANGED

            triggerStatusHooks(enemy, "end");
            if (checkPlayerDead()) return;
        }
    }


    private void triggerStatusHooks(BaseUnit unit, String timing) {
        for (StatusEffect e : new ArrayList<>(unit.getActiveEffects())) {
            if (timing.equals("start")) e.onTurnStart(unit);
            if (timing.equals("end")) e.onTurnEnd(unit);
        }
    }

    private void decrementDurations(BaseUnit unit) {
        ArrayList<StatusEffect> toRemove = new ArrayList<>();
        for (StatusEffect e : unit.getActiveEffects()) {
            e.decreaseDuration();
            if (e.getDuration() == 0) {
                toRemove.add(e);
            }
        }
        unit.getActiveEffects().removeAll(toRemove);
    }

    private void discardHand() {
        discardPile.addAll(hand);
        hand.clear();
    }

    private void cleanupCombat() {
        player.getActiveEffects().clear();
        System.out.println("[CLEANUP] All status effects cleared.");
    }

    private boolean checkPlayerDead() { return player.getHp() <= 0; }
    private boolean checkAllEnemiesDead() {
        for (Enemy e : enemies) if (e.getHp() > 0) return false;
        return true;
    }

    private void printHand() {
        System.out.println("\n📋 HAND:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("  [" + i + "] " + hand.get(i).getName() + " (" + hand.get(i).getCost() + " Energy)");
        }
    }
}
