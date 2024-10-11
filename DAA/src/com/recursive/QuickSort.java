package com.recursive;

import java.util.Scanner;

public class QuickSort {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Enter the number of elements: ");
		int n = sc.nextInt();
		int[] arr = getArray(n);
		int low = 0;
		int high = arr.length - 1;
		QSort(arr, low, high);
		printArray(arr);
	}

	// Method to take input from the user
	private static int[] getArray(int n) {
		int[] arr = new int[n];
		System.out.println("Enter the elements: ");
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		return arr;
	}

	private static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	private static void QSort(int[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			QSort(arr, low, pivotIndex - 1);  
			QSort(arr, pivotIndex + 1, high); 
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
}
