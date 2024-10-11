package com.nonrecursive;

import java.util.Stack;
import java.util.Scanner;

public class QuickSortNonRecursive {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] arr = getArray(n);
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

                if (pivotIndex - 1 > start) {
                    stack.push(new int[]{start, pivotIndex - 1});
                }
                if (pivotIndex + 1 < end) {
                    stack.push(new int[]{pivotIndex + 1, end});
                }
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low + 1;
        int right = high;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) {
                left++;
            }
            while (left <= right && arr[right] > pivot) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, low, right);

        return right; 
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] getArray(int n) {
        int[] arr = new int[n];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
