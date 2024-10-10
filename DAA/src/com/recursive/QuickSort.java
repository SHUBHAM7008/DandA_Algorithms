package com.recursive;

import java.util.Scanner;

public class QuickSort {
	static Scanner sc= new Scanner (System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the No of elements : ");
				int n = sc.nextInt();
		int[] arr = new int[n+1];
		arr=getArray(n+1);
		int low=0;
		int high=arr.length-1;
		QSort(arr,low,high);
        printArray(arr);
	}

	private static int[] getArray(int n) {
		// TODO Auto-generated method stub
		int[] arr= new int[n];
		for(int i=0;i<n-1;i++) {
			arr[i]=sc.nextInt();
		}
		arr[n-1]=Integer.MAX_VALUE;
		return arr;
}
	private static void printArray(int[] arr) {
		// TODO Auto-generated method stub
		for(int i=0;i<arr.length-1;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	private static void QSort(int[] arr,int low, int high) {
		if(low<high) {
			int j=partioning(arr,low,high);
			printArray(arr);
			System.out.println();
			QSort(arr,low,j);
			QSort(arr,j+1,high);
		}
	}

	private static int partioning(int[] arr,int low, int high) {
		// TODO Auto-generated method stub
		int pivot=low;
		low++;
		high--;
		while(low<high) {
			while(arr[low]<arr[pivot]) {
				low++;
			}
			while(arr[high]>arr[pivot]) {
				high--;
			}
			
			if(low<high) {
				swap(arr,low,high);
			}
		}
		if(low>high)
	     swap(arr,pivot,high);
		return high;
		//return j
	}

	private static void swap( int[] arr,int low, int high) {
		// TODO Auto-generated method stub
        int t =arr[low];
        arr[low]=arr[high];
        arr[high]=t;
	}

}
