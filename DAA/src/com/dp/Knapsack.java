package com.dp;

import java.util.Scanner;

public class Knapsack {

    public static int knapsack(int[] weights, int[] profits, int capacity) {
        int n = profits.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + profits[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxProfit = dp[n][capacity];
        int w = capacity;
        int[] included = new int[n];

        // Determine which items are included in the knapsack
        for (int i = n; i > 0 && maxProfit > 0; i--) {
            if (maxProfit != dp[i - 1][w]) {
                included[i - 1] = 1;
                maxProfit -= profits[i - 1];
                w -= weights[i - 1];
            } else {
                included[i - 1] = 0;
            }
        }

        System.out.println("Items included (weight, profit, ratio, included):");
        for (int i = 0; i < n; i++) {
            double ratio = (double) profits[i] / weights[i];
            System.out.printf("Item %d: (%d, %d, %.2f, %d)%n", i + 1, weights[i], profits[i], ratio, included[i]);
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];

        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        System.out.println("Enter profits of items:");
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }

        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        int maxProfit = knapsack(weights, profits, capacity);
        System.out.printf("\nMaximum profit in Knapsack = %.2f%n", (double) maxProfit);
        
        sc.close();
    }
}
