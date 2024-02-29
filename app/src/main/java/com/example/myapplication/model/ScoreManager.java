package com.example.myapplication.model;

public class ScoreManager {
    private static double score; // Now static

    // Static initializer block
    static {
        score = 100; // Default initialization, can be set to any initial value as required
    }

    // Removed the constructor as we no longer instantiate this class

    public static double getScore() {
        return score;
    }

    public static void decrementScore(double amount) {
        if (score > amount) { // Changed to decrement by the amount passed, not just by one
            score -= amount;
        }
    }



    // If you need to set the score at any point
    public static void setScore(double newScore) {
        score = newScore;
    }

    // If you need to increment the score
    public static void incrementScore(double amount) {
        score += amount;
    }
}
