package com.nonrecursive;

import java.util.Stack;

public class MinMax {

	static int count=0;

	public static void main(String[] args) {
		int[] arr = {13, -5, 21, 98, 897, 5643, 777, 1, 2, 12, 3, 111, 876, 987, 256, 765, 9999, 11, 3, 8};
		int[] minmax = new int[2];

		System.out.println("\n    Length of arr : " + arr.length);
		System.out.println("\n  Starting Time : " + System.currentTimeMillis());

		minmax = findMinMax(arr);

		System.out.println("  Min value is : " + minmax[0] + "\n  Max value is : " + minmax[1]);
		System.out.println("  Ending Time  : " + System.currentTimeMillis());
	}

	private static int[] findMinMax(int[] arr) {
		int min = arr[0];
		int max = arr[0];

		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {0, arr.length - 1});

		while (!stack.isEmpty()) {
			int[] range = stack.pop();
			int start = range[0];
			int end = range[1];

			if (start == end) {
				min = Math.min(min, arr[start]);
				max = Math.max(max, arr[start]);
			} else if (start >= end - 1) {
				min = Math.min(min, Math.min(arr[start], arr[end]));
				max = Math.max(max, Math.max(arr[start], arr[end]));
			} else {
				int mid = (start + end) / 2;
				stack.push(new int[] {start, mid});
				stack.push(new int[] {mid + 1, end});
			}
		}

		return new int[] {min, max};
	}
}
