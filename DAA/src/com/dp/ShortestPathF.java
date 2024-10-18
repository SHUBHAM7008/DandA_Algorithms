package com.dp;

import java.util.*;

public class ShortestPathF {

    private static final int INF = Integer.MAX_VALUE;

    public static int[][] convertToAdjMatrix(List<Edge> edges, int n, Map<String, Integer> nodeMap) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (Edge edge : edges) {
            graph[nodeMap.get(edge.u)][nodeMap.get(edge.v)] = edge.weight;
        }
        return graph;
    }

    public static Result shortestPath(int[][] graph, String[] nodeNames) {
        int n = graph.length;
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        int[] path = new int[n];
        Arrays.fill(path, -1);

        System.out.println("Paths and their costs:");
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (graph[j][i] != INF) {
                    int currentPathCost = dp[j] + graph[j][i];
                    System.out.printf("Path from %s to %s: %d (via %s)%n", nodeNames[j], nodeNames[i], currentPathCost, nodeNames[j]);
                    if (dp[i] > currentPathCost) {
                        dp[i] = currentPathCost;
                        path[i] = j;
                    }
                }
            }
        }

        List<String> resultPath = new ArrayList<>();
        int current = n - 1;
        while (current != -1) {
            resultPath.add(nodeNames[current]);
            current = path[current];
        }

        Collections.reverse(resultPath);
        return new Result(dp[n - 1], resultPath);
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 3), new Edge("A", "C", 6), new Edge("A", "D", 7),
            new Edge("B", "E", 8), new Edge("B", "F", 5),
            new Edge("C", "E", 10), new Edge("C", "F", 3), new Edge("C", "G", 11),
            new Edge("D", "G", 5),
            new Edge("E", "H", 6),
            new Edge("F", "H", 7),
            new Edge("G", "H", 2)
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

class Edge {
    String u, v;
    int weight;

    Edge(String u, String v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

class Result {
    int cost;
    List<String> path;

    Result(int cost, List<String> path) {
        this.cost = cost;
        this.path = path;
    }
}
