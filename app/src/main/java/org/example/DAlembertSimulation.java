package org.example;

import java.util.Random;

public class DAlembertSimulation {
    public static void main(String[] args) {
        double startingBalance = 1000; // Starting balance in dollars
        double initialBet = 11; // Initial bet in dollars
        double adjustedOdds = 2; // Adjusted odds
        double winProbability = 1 / adjustedOdds; // Adjusted win probability for 1.05x odds
        int numRounds = 100; // Number of rounds to simulate

        double finalBalance = simulateDAlembert(startingBalance, initialBet, winProbability, adjustedOdds, numRounds);
        System.out.printf("Final balance after %d rounds: %.2f\n", numRounds, finalBalance);
    }

    public static double simulateDAlembert(double startingBalance, double initialBet, double winProbability, double adjustedOdds, int numRounds) {
        double balance = startingBalance;
        double bet = initialBet;
        Random random = new Random();

        for (int round = 0; round < numRounds; round++) {
            if (balance < bet) {
                System.out.printf("Round %d: Insufficient funds to continue betting. Final balance: %.2f\n", round + 1, balance);
                break;
            }

            // Simulate the game outcome
            if (random.nextDouble() < winProbability) {
                balance += bet * (adjustedOdds - 1); // Winning bet amount
                if (bet > initialBet) {
                    bet -= initialBet; // Decrease bet by one unit after a win
                }
            } else {
                balance -= bet;
                bet += initialBet; // Increase bet by one unit after a loss
            }

            System.out.printf("Round %d: Current balance: %.2f, Next bet: %.2f\n", round + 1, balance, bet);
        }

        return balance;
    }
}

