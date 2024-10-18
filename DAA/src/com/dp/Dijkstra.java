package com.dp;

import java.util.*;

public class Dijkstra {

    // Helper class to represent an edge with cost
    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    // Function to convert edges to adjacency matrix
    public static int[][] convertToAdjMatrix(List<EdgeEntry> edges, int n, Map<String, Integer> nodeMap) {
        int INF = Integer.MAX_VALUE;
        int[][] graph = new int[n][n];

        // Initialize the graph with INF for all edges except the diagonal (self-loops)
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0; // Distance to itself is 0
        }

        for (EdgeEntry edge : edges) {
            graph[nodeMap.get(edge.start)][nodeMap.get(edge.end)] = edge.weight;
        }

        return graph;
    }

    // Dijkstra's Algorithm to find shortest paths
    public static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        distances[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.node;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[currentNode][neighbor] != Integer.MAX_VALUE) {
                    int newDist = distances[currentNode] + graph[currentNode][neighbor];
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        path[neighbor] = currentNode;
                        pq.add(new Edge(neighbor, newDist));
                    }
                }
            }
        }

        return path;
    }

    // Function to reconstruct the shortest path
    public static List<String> reconstructPath(int start, int end, int[] path, String[] nodeNames) {
        List<String> result = new ArrayList<>();
        for (int current = end; current != -1; current = path[current]) {
            result.add(nodeNames[current]);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        // Define edges (start, end, weight)
        List<EdgeEntry> edges = Arrays.asList(
                new EdgeEntry("A", "B", 3), new EdgeEntry("A", "C", 6), new EdgeEntry("A", "D", 7),
                new EdgeEntry("B", "E", 8), new EdgeEntry("B", "F", 5),
                new EdgeEntry("C", "E", 10), new EdgeEntry("C", "F", 3), new EdgeEntry("C", "G", 11),
                new EdgeEntry("D", "G", 5),
                new EdgeEntry("E", "H", 6),
                new EdgeEntry("F", "H", 7),
                new EdgeEntry("G", "H", 2)
        );

        String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Map<String, Integer> nodeMap = new HashMap<>();
        for (int i = 0; i < nodeNames.length; i++) {
            nodeMap.put(nodeNames[i], i);
        }

        int n = nodeNames.length;

        // Convert to adjacency matrix
        int[][] graph = convertToAdjMatrix(edges, n, nodeMap);

        String startNodeName = "B";  // Change this to the desired starting node
        int startNode = nodeMap.get(startNodeName);

        // Run Dijkstra's Algorithm
        int[] path = dijkstra(graph, startNode);

        // Print shortest distances and paths from start node
        System.out.println("Shortest distances and paths from node " + startNodeName + ":");
        for (int endNode = 0; endNode < n; endNode++) {
            if (graph[startNode][endNode] == Integer.MAX_VALUE) {
                System.out.println("To " + nodeNames[endNode] + ": Distance = inf, Path = No path");
            } else {
                List<String> shortestPath = reconstructPath(startNode, endNode, path, nodeNames);
                System.out.println("To " + nodeNames[endNode] + ": Distance = " + graph[startNode][endNode] +
                        ", Path = " + String.join(" -> ", shortestPath));
            }
        }
    }
}

// Edge entry class to store the edges
class EdgeEntry {
    String start;
    String end;
    int weight;

    public EdgeEntry(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

