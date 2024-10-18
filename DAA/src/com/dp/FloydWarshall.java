package com.dp;

import java.util.Arrays;

public class FloydWarshall {

    final static int INF = Integer.MAX_VALUE;

    public static void floydWarshall(int[][] graph) {
        int V = graph.length;
        
        // Initialize distance and path matrices
        int[][] dist = new int[V][V];
        Integer[][] nextNode = new Integer[V][V];

        // Fill initial distance matrix and path matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (graph[i][j] != 0) {
                    dist[i][j] = graph[i][j];
                    nextNode[i][j] = j;
                } else {
                    dist[i][j] = INF;
                    nextNode[i][j] = null;
                }
            }
        }

        // Update distances and paths based on Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            nextNode[i][j] = nextNode[i][k];
                        }
                    }
                }
            }
        }

        printSolution(dist, nextNode, graph);
    }

    public static void printSolution(int[][] dist, Integer[][] nextNode, int[][] graph) {
        int V = dist.length;

        System.out.println("The following matrix shows the shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println(); // Newline for the next row
        }

        System.out.println("\nPaths between each pair of vertices (including edge values):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.println("Path from " + i + " to " + j + ": No path");
                } else {
                    PathResult result = reconstructPath(i, j, nextNode, graph);
                    String pathStr = String.join(" -> ", result.getPath());
                    String valuesStr = String.join(" + ", result.getValues());
                    System.out.println("Path from " + i + " to " + j + ": " + pathStr 
                                       + " (Total cost: " + dist[i][j] + ", Values: " + valuesStr + ")");
                }
            }
        }
    }

    public static PathResult reconstructPath(int start, int end, Integer[][] nextNode, int[][] graph) {
        if (nextNode[start][end] == null) {
            return new PathResult(new String[0], new String[0]); // No path exists
        }

        StringBuilder path = new StringBuilder();
        StringBuilder values = new StringBuilder();

        while (start != end) {
            path.append(start).append(" ");
            values.append(graph[start][nextNode[start][end]]).append(" ");
            start = nextNode[start][end];
        }

        path.append(end); // Append the last node
        return new PathResult(path.toString().trim().split(" "), values.toString().trim().split(" "));
    }

    public static void main(String[] args) {
        // Adjacency matrix representation of the graph
        int[][] graph = {
            {0, 4, 11}, // From vertex 0 to 1 is 4, to 2 is 11
            {6, 0, 2},  // From vertex 1 to 0 is 6, to 2 is 2
            {3, 0, 0}   // From vertex 2 to 0 is 3
        };

        floydWarshall(graph);
    }
}

// Helper class for returning path and edge values
class PathResult {
    private String[] path;
    private String[] values;

    public PathResult(String[] path, String[] values) {
        this.path = path;
        this.values = values;
    }

    public String[] getPath() {
        return path;
    }

    public String[] getValues() {
        return values;
    }
}
