package com.nonrecursive;

public class BinarySearchNonRecursive {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 11, 12, 13, 21, 98, 111, 256, 765, 876, 897, 987, 9999};
        int target = 111;
        int result = binarySearch(arr, target);

        if (result == -1)
            System.out.println("Element not found.");
        else
            System.out.println("Element found at index: " + result);
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the element is found
            if (arr[mid] == target) {
                return mid;
            }

            // If target is smaller, search the left half
            if (arr[mid] > target) {
                right = mid - 1;
            }
            // Else, search the right half
            else {
                left = mid + 1;
            }
        }

        // Element not found
        return -1;
    }
}
