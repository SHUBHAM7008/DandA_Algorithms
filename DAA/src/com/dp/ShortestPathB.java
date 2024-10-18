package com.dp;

import java.util.*;

public class ShortestPathB {

    private static final int INF = Integer.MAX_VALUE;

    public static int[][] convertToAdjMatrix(List<EdgeB> edges, int n, Map<String, Integer> nodeMap) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (EdgeB edge : edges) {
            graph[nodeMap.get(edge.u)][nodeMap.get(edge.v)] = edge.weight;
        }
        return graph;
    }

    public static Result shortestPath(int[][] graph, String[] nodeNames) {
        int n = graph.length;
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[n - 1] = 0;

        int[] path = new int[n];
        Arrays.fill(path, -1);

        System.out.println("Paths and their costs:");
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] != INF) {
                    int currentPathCost = dp[j] + graph[i][j];
                    System.out.printf("Path from %s to %s: %d (via %s)%n", nodeNames[i], nodeNames[j], currentPathCost, nodeNames[i]);
                    if (dp[i] > currentPathCost) {
                        dp[i] = currentPathCost;
                        path[i] = j;
                    }
                }
            }
        }

        List<String> resultPath = new ArrayList<>();
        int current = 0;
        while (current != -1) {
            resultPath.add(nodeNames[current]);
            current = path[current];
        }

        return new Result(dp[0], resultPath);
    }

    public static void main(String[] args) {
        List<EdgeB> edges = Arrays.asList(
            new EdgeB("A", "B", 3), new EdgeB("A", "C", 6), new EdgeB("A", "D", 7),
            new EdgeB("B", "E", 8), new EdgeB("B", "F", 5),
            new EdgeB("C", "E", 10), new EdgeB("C", "F", 3), new EdgeB("C", "G", 11),
            new EdgeB("D", "G", 5),
            new EdgeB("E", "H", 6),
            new EdgeB("F", "H", 7),
            new EdgeB("G", "H", 2)
        );

        String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Map<String, Integer> nodeMap = new HashMap<>();
        for (int i = 0; i < nodeNames.length; i++) {
            nodeMap.put(nodeNames[i], i);
        }

        int n = nodeNames.length;
        int[][] graph = convertToAdjMatrix(edges, n, nodeMap);

        Result result = shortestPath(graph, nodeNames);

        System.out.printf("%nMinimum cost to reach the destination: %d%n", result.cost);
        System.out.println("Shortest Path: " + String.join(" -> ", result.path));
    }
}

class EdgeB {
    String u, v;
    int weight;

    EdgeB(String u, String v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

class ResultB {
    int cost;
    List<String> path;

    ResultB(int cost, List<String> path) {
        this.cost = cost;
        this.path = path;
    }
}
