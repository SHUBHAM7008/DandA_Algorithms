package com.recursive;

public class BinarySearchRecursive {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 11, 12, 13, 21, 98, 111, 256, 765, 876, 897, 987, 9999};
        int target = 111;
        int result = binarySearch(arr, target, 0, arr.length - 1);

        if (result == -1)
            System.out.println("Element not found.");
        else
            System.out.println("Element found at index: " + result);
    }

    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;  // Target not found
        }

        int mid = left + (right - left) / 2;

        // If the element is found
        if (arr[mid] == target) {
            return mid;
        }

        // If the element is smaller than mid, it must be in the left half
        if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        }

        // Else, the element is in the right half
        return binarySearch(arr, target, mid + 1, right);
    }
}
