package com.dp;

import java.util.Arrays;

public class OptimalBST {

    public static ResultOBST optimalBST(char[] keys, double[] freq) {
        int n = keys.length;
        double[][] cost = new double[n][n];
        int[][] root = new int[n][n];

        // Initialize cost for each key
        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
            root[i][i] = i;
        }

        // Calculate optimal cost for subtrees of increasing lengths
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                cost[i][j] = Double.POSITIVE_INFINITY;

                for (int r = i; r <= j; r++) {
                    double c = (r > i ? cost[i][r - 1] : 0) +
                               (r < j ? cost[r + 1][j] : 0) +
                               sum(freq, i, j);

                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                        root[i][j] = r;
                    }
                }
            }
        }

        return new ResultOBST(cost, root);
    }

    private static double sum(double[] freq, int i, int j) {
        double total = 0;
        for (int k = i; k <= j; k++) {
            total += freq[k];
        }
        return total;
    }

    public static void printOptimalBST(int[][] root, char[] keys, int i, int j) {
        if (i > j) return;
        int r = root[i][j];
        System.out.print(keys[r] + " ");
        printOptimalBST(root, keys, i, r - 1);
        printOptimalBST(root, keys, r + 1, j);
    }

    public static void main(String[] args) {
        char[] keys = {'A', 'B', 'C', 'D'};
        double[] freq = {0.3, 0.3, 0.3, 0.3};

        ResultOBST result = optimalBST(keys, freq);
        double[][] cost = result.cost;
        int[][] root = result.root;

        System.out.println("Cost Matrix:");
        for (double[] row : cost) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nRoot Matrix:");
        for (int[] row : root) {
            System.out.println(Arrays.toString(row));
        }

        double minCost = cost[0][keys.length - 1];
        System.out.println("\nMinimum cost of the Optimal Binary Search Tree: " + minCost);

        System.out.println("Keys in the Optimal BST:");
        printOptimalBST(root, keys, 0, keys.length - 1);
    }
}

class ResultOBST {
    double[][] cost;
    int[][] root;

    ResultOBST(double[][] cost, int[][] root) {
        this.cost = cost;
        this.root = root;
    }
}
