package com.dp;

import java.util.ArrayList;
import java.util.List;

public class EditDistance {
    
    public static class Result {
        int distance;
        int[][] matrix;

        Result(int distance, int[][] matrix) {
            this.distance = distance;
            this.matrix = matrix;
        }
    }

    public static Result editDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // Insert all characters of s2
                } else if (j == 0) {
                    dp[i][j] = i; // Remove all characters of s1
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], // Deletion
                                            Math.min(dp[i][j - 1], // Insertion
                                                     dp[i - 1][j - 1])); // Substitution
                }
            }
        }

        return new Result(dp[m][n], dp);
    }

    public static void printEditDistanceOperations(String s1, String s2, int[][] dp) {
        int i = s1.length(), j = s2.length();
        List<String> operations = new ArrayList<>();

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && s1.charAt(i - 1) == s2.charAt(j - 1)) {
                operations.add("Keep " + s1.charAt(i - 1));
                i--;
                j--;
            } else if (i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1] + 1) {
                operations.add("Substitute " + s1.charAt(i - 1) + " with " + s2.charAt(j - 1));
                i--;
                j--;
            } else if (i > 0 && dp[i][j] == dp[i - 1][j] + 1) {
                operations.add("Delete " + s1.charAt(i - 1));
                i--;
            } else {
                operations.add("Insert " + s2.charAt(j - 1));
                j--;
            }
        }

        // Reverse operations for correct order
        for (int k = operations.size() - 1; k >= 0; k--) {
            System.out.println(operations.get(k));
        }
    }

    public static void printEditDistanceMatrix(int[][] dp) {
        for (int[] row : dp) {
            for (int value : row) {
                System.out.printf("%3d ", value);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String s1 = "kitten";
        String s2 = "sitting";

        Result result = editDistance(s1, s2);
        System.out.printf("Edit Distance between '%s' and '%s': %d%n", s1, s2, result.distance);
        System.out.println("\nEdit Distance Matrix:");
        printEditDistanceMatrix(result.matrix);

        System.out.println("\nOperations to convert:");
        printEditDistanceOperations(s1, s2, result.matrix);
    }
}

