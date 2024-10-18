package com.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowShopScheduling {

    public static void flowShopScheduling(Map<String, int[]> jobs) {
        System.out.println("\nInitial Job Details (Machine 1, Machine 2 processing times):");
        for (Map.Entry<String, int[]> entry : jobs.entrySet()) {
            String job = entry.getKey();
            int[] times = entry.getValue();
            System.out.println("Job " + job + ": M1 = " + times[0] + " units, M2 = " + times[1] + " units");
        }

        List<Job> group1 = new ArrayList<>();
        List<Job> group2 = new ArrayList<>();

        System.out.println("\nDividing jobs into two groups:");
        for (Map.Entry<String, int[]> entry : jobs.entrySet()) {
            String job = entry.getKey();
            int[] times = entry.getValue();
            int m1Time = times[0];
            int m2Time = times[1];

            if (m1Time <= m2Time) {
                group1.add(new Job(job, m1Time, m2Time));
                System.out.println("Job " + job + " added to Group 1 (M1 <= M2): M1 = " + m1Time + ", M2 = " + m2Time);
            } else {
                group2.add(new Job(job, m1Time, m2Time));
                System.out.println("Job " + job + " added to Group 2 (M1 > M2): M1 = " + m1Time + ", M2 = " + m2Time);
            }
        }

        // Sort group1 in ascending order of m1_time
        System.out.println("\nSorting Group 1 by ascending M1 times:");
        Collections.sort(group1, Comparator.comparingInt(Job::getM1Time));
        for (Job job : group1) {
            System.out.println("Job " + job.getName() + ": M1 = " + job.getM1Time() + ", M2 = " + job.getM2Time());
        }

        // Sort group2 in descending order of m2_time
        System.out.println("\nSorting Group 2 by descending M2 times:");
        Collections.sort(group2, (j1, j2) -> Integer.compare(j2.getM2Time(), j1.getM2Time()));
        for (Job job : group2) {
            System.out.println("Job " + job.getName() + ": M1 = " + job.getM1Time() + ", M2 = " + job.getM2Time());
        }

        // Combine the two groups to get the optimal sequence
        List<Job> optimalSequence = new ArrayList<>(group1);
        optimalSequence.addAll(group2);
        System.out.println("\nOptimal Job Sequence after combining both groups:");
        System.out.println(optimalSequence.stream().map(Job::getName).reduce((a, b) -> a + " -> " + b).orElse(""));

        // Simulate the processing and calculate makespan
        int m1Completion = 0;  // Machine 1 completion time
        int m2Completion = 0;  // Machine 2 completion time
        int totalCompletion = 0;

        System.out.println("\nProcessing Jobs:");

        for (Job job : optimalSequence) {
            m1Completion += job.getM1Time();
            m2Completion = Math.max(m2Completion, m1Completion) + job.getM2Time();
            totalCompletion = m2Completion;

            System.out.println("Job " + job.getName() + ":");
            System.out.println("  Machine 1 finishes at " + m1Completion + " (M1 time: " + job.getM1Time() + ")");
            System.out.println("  Machine 2 starts after M1 finishes or M2's previous job: max(" + (m2Completion - job.getM2Time()) + ", " + m1Completion + ")");
            System.out.println("  Machine 2 finishes at " + m2Completion + " (M2 time: " + job.getM2Time() + ")\n");
        }

        System.out.println("Total completion time (Makespan): " + totalCompletion + "\n");
    }

    public static void main(String[] args) {
        // Define jobs with processing times on two machines (M1, M2)
        Map<String, int[]> jobs = new HashMap<>();
        jobs.put("A", new int[]{3, 2}); // Job A: M1 = 3 units, M2 = 2 units
        jobs.put("B", new int[]{2, 4}); // Job B: M1 = 2 units, M2 = 4 units
        jobs.put("C", new int[]{5, 3}); // Job C: M1 = 5 units, M2 = 3 units
        jobs.put("D", new int[]{4, 6}); // Job D: M1 = 4 units, M2 = 6 units
        jobs.put("E", new int[]{3, 1}); // Job E: M1 = 3 units, M2 = 1 unit

        flowShopScheduling(jobs);
    }
}

// Job class to hold job details
class Job {
    private String name;
    private int m1Time;
    private int m2Time;

    public Job(String name, int m1Time, int m2Time) {
        this.name = name;
        this.m1Time = m1Time;
        this.m2Time = m2Time;
    }

    public String getName() {
        return name;
    }

    public int getM1Time() {
        return m1Time;
    }

    public int getM2Time() {
        return m2Time;
    }
}
