package com.dp;

import java.util.*;

public class TravelingSalesmanProblem {

    public static class Result {
        int cost;
        List<String> path;

        Result(int cost, List<String> path) {
            this.cost = cost;
            this.path = path;
        }
    }

    public static Result tsp(int[][] graph, String[] cities) {
        int n = graph.length;

        // dp[mask][j] holds the minimum cost to visit the cities in 'mask' ending at city j
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Base case: starting from city 0 to other cities
        for (int i = 1; i < n; i++) {
            dp[1 << i][i] = graph[0][i]; // Only visiting city i
            System.out.printf("Setting initial dp value for %s -> %s: %d%n", cities[0], cities[i], graph[0][i]);
        }

        // Fill dp table for subsets of increasing size
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int j = 1; j < n; j++) { 
                if ((mask & (1 << j)) != 0) { // If j is included in the mask
                    int prevMask = mask ^ (1 << j); // Remove j from the mask
                    for (int k = 1; k < n; k++) {
                        if ((prevMask & (1 << k)) != 0) { // If k is included in the previous mask
                            dp[mask][j] = Math.min(dp[mask][j], dp[prevMask][k] + graph[k][j]);
                        }
                    }
                }
            }
        }

        // Print DP values for subsets
        for (int mask = 0; mask < (1 << n); mask++) {
            for (int j = 0; j < n; j++) {
                if (dp[mask][j] != Integer.MAX_VALUE) {
                    System.out.printf("dp[%s -> %s] = %d%n", getCityNames(mask, cities), cities[j], dp[mask][j]);
                }
            }
        }

        // Calculate minimum cost of completing the tour
        int minCost = Integer.MAX_VALUE;
        int lastCity = -1;

        System.out.println("Final step:");
        for (int i = 1; i < n; i++) {
            int cost = dp[(1 << n) - 1][i] + graph[i][0]; // Complete the tour returning to the starting city
            System.out.printf("Considering returning to %s from %s, cost: dp[%s] + graph[%s][%s] = %d%n",
                    cities[0], cities[i], Integer.toBinaryString((1 << n) - 1), cities[i], cities[0], cost);
            if (cost < minCost) {
                minCost = cost;
                lastCity = i;
            }
        }

        // Reconstruct the path
        List<String> path = new ArrayList<>();
        int mask = (1 << n) - 1; // All cities visited
        while (lastCity != -1) {
            path.add(cities[lastCity]);
            int temp = lastCity; 
            lastCity = -1; 
            for (int k = 1; k < n; k++) {
                if (k != temp && (mask & (1 << k)) != 0 && 
                    dp[mask][temp] == dp[mask ^ (1 << temp)][k] + graph[k][temp]) {
                    lastCity = k; 
                    break; 
                }
            }
            mask ^= (1 << temp); // Remove temp from mask
        }

        Collections.reverse(path); // Reverse the path to get the correct order
        path.add(cities[0]); // Return to the starting city

        return new Result(minCost, path);
    }

    // Convert subset bits to city names
    private static String getCityNames(int subset, String[] cities) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < cities.length; j++) {
            if ((subset & (1 << j)) != 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(cities[j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Graph with city names and travel costs
        String[] cities = {"A", "B", "C", "D"};
        int[][] graph = {
            {0, 10, 15, 20},  // A -> B, C, D
            {10, 0, 35, 25},  // B -> A, C, D
            {15, 35, 0, 30},  // C -> A, B, D
            {20, 25, 30, 0}   // D -> A, B, C
        };

        Result result = tsp(graph, cities);
        System.out.printf("%nMinimum cost to complete the tour: %d%n", result.cost);
        System.out.printf("Optimal path: %s%n", String.join(" -> ", result.path));
    }
}
