package com.nonrecursive;


import java.util.Scanner;

public class SelectKthEle {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the No of elements : ");
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        arr = getArray(n + 1);
        System.out.println("Enter index to Select from elements and max index is " + n + " :");
        int k = sc.nextInt() - 1;
        System.out.print("Element is :" + selectKNonRecursive(arr, k));
    }

    private static int selectKNonRecursive(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int j = partition(arr, low, high);

            if (j == k) {
                return arr[j]; 
            } else if (j > k) {
                high = j; 
            } else {
                low = j + 1; 
            }
        }

        return arr[low]; 
    }

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
