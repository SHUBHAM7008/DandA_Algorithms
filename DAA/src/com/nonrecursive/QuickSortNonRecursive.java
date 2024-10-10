package com.nonrecursive;

import java.util.Stack;
import java.util.Scanner;

public class QuickSortNonRecursive {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the No of elements : ");
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        arr = getArray(n + 1);
        quickSortNonRecursive(arr, 0, arr.length - 1);
        printArray(arr);
    }

    private static void quickSortNonRecursive(int[] arr, int low, int high) {
        Stack<int[]> stack = new Stack<>();

        stack.push(new int[]{low, high});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int start = range[0];
            int end = range[1];

            if (start < end) {
                int pivotIndex = partition(arr, start, end);
                printArray(arr);
                System.out.println();

                // Push subarrays to the stack for further sorting
                stack.push(new int[]{start, pivotIndex});
                stack.push(new int[]{pivotIndex + 1, end});
            }
        }
    }

    // Partition method
    private static int partition(int[] arr, int low, int high) {
        int pivot = low;
        low++;
        high--;

        while (low <= high) {
            while (arr[low] < arr[pivot]) {
                low++;
            }
            while (arr[high] > arr[pivot]) {
                high--;
            }

            if (low < high) {
                swap(arr, low, high);
            }
        }

        swap(arr, pivot, high); 
        return high;
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    private static int[] getArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = sc.nextInt();
        }
        arr[n - 1] = Integer.MAX_VALUE;
        return arr;
    }


    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
