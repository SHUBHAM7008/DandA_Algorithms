package com.recursive;

import java.util.Scanner;

public class SelectKthEle {
	static Scanner sc= new Scanner (System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the No of elements : ");
				int n = sc.nextInt();
		int[] arr = new int[n+1];
		arr=getArray(n+1);
		System.out.println("Enter index to Select from elements and max index is "+n+" :");
		int k=sc.nextInt()-1;
		int low=0;
		int high=arr.length-1;
		System.out.print("Element is :"+ SelectK(arr,low,high,k));   
	}
	private static int SelectK(int[] arr,int low, int high,int k) {
			int j=partioning(arr,low,high);
			if(j==k) {
				return arr[j];
			}else if(j>k) {
			   high=j;
			}else {
				low=j+1;
			}
	  return SelectK(arr,low,high,k);
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

}
