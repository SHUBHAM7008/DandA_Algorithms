package com.dp;

import java.util.ArrayList;
import java.util.List;

public class ReliabilityDesign {

    // Function to calculate the maximum reliability with a given budget
    public static double reliabilityDesign(List<List<Component>> stages, double[][] dp, int budget) {
        int n = stages.size();

        for (int i = 1; i <= n; i++) {
            for (int b = 0; b <= budget; b++) {
                dp[i][b] = dp[i - 1][b];  // No component chosen for this stage
                for (Component comp : stages.get(i - 1)) {
                    if (comp.cost <= b) {
                        dp[i][b] = Math.max(dp[i][b], dp[i - 1][b - comp.cost] + comp.reliability);
                    }
                }
            }
        }

        return dp[n][budget];
    }

    // Function to print the chosen components for maximum reliability
    public static void printReliabilityChoices(double[][] dp, List<List<Component>> stages, int budget) {
        int n = stages.size();
        int b = budget;
        List<Component> chosenComponents = new ArrayList<>();

        for (int i = n; i > 0; i--) {
            for (Component comp : stages.get(i - 1)) {
                if (comp.cost <= b && dp[i][b] == dp[i - 1][b - comp.cost] + comp.reliability) {
                    chosenComponents.add(comp);
                    b -= comp.cost;
                    break;
                }
            }
        }

        // Print the selected components
        for (int i = 0; i < chosenComponents.size(); i++) {
            Component comp = chosenComponents.get(i);
            System.out.println("Stage " + (i + 1) + ": Cost = " + comp.cost + ", Reliability = " + comp.reliability);
        }
    }

    public static void main(String[] args) {
        // Define stages and components (cost, reliability)
        List<List<Component>> stages = new ArrayList<>();

        // Stage 1
        List<Component> stage1 = new ArrayList<>();
        stage1.add(new Component(1, 0.9));
        stage1.add(new Component(2, 0.95));
        stages.add(stage1);

        // Stage 2
        List<Component> stage2 = new ArrayList<>();
        stage2.add(new Component(2, 0.85));
        stage2.add(new Component(3, 0.88));
        stages.add(stage2);

        // Stage 3
        List<Component> stage3 = new ArrayList<>();
        stage3.add(new Component(2, 0.80));
        stage3.add(new Component(4, 0.85));
        stages.add(stage3);

        // Stage 4
        List<Component> stage4 = new ArrayList<>();
        stage4.add(new Component(1, 0.78));
        stage4.add(new Component(3, 0.90));
        stages.add(stage4);

        int budget = 7;

        // Create dp table
        double[][] dp = new double[stages.size() + 1][budget + 1];

        // Calculate maximum reliability
        double maxReliability = reliabilityDesign(stages, dp, budget);
        
        System.out.println("Maximum Reliability: " + maxReliability);
        System.out.println("\nChosen Components for Maximum Reliability:");
        printReliabilityChoices(dp, stages, budget);
    }
}

// Component class to store cost and reliability
class Component {
    int cost;
    double reliability;

    public Component(int cost, double reliability) {
        this.cost = cost;
        this.reliability = reliability;
    }
}
