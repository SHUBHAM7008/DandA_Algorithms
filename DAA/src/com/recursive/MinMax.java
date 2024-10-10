package com.recursive;

public class MinMax {
	static int count=0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr= {13,-5,21,98,897,5643,777,1,2,12,3,111,222,876,987,256,765,999,11,3,8};
		int[] minmax=new int[2];
		System.out.println("\n    Length of arr : "+arr.length);
		System.out.println("\n  Staring Time : "+System.currentTimeMillis());
		minmax=DAndC(arr,0,arr.length-1);
		System.out.println("  Min value is : "+minmax[0]+"\n  Max value is : "+minmax[1]);
		System.out.println("  Ending Time  : "+System.currentTimeMillis());
	}
	private static int[] DAndC(int[] arr, int i, int j) {
		count++;
		 int[] left=new int[2];
		 int[] right=new int[2];
		if(i==j) {
			left[0]=arr[j];
			left[1]=arr[i];
		}
		else if(i>=j-1) {
			if(arr[i]>arr[j]) {
				left[0]=arr[j];
				left[1]=arr[i];
			}else {
				left[0]=arr[i];
				left[1]=arr[j];
			}
			return left;
		}else {
			int mid=(i+j)/2;
			left=DAndC(arr,i,mid);
			right=DAndC(arr,mid+1,j);
		}
		
		if(left[0]<right[0]) {
			left[0]=left[0];
		}else {
			left[0]=right[0];
		}
		if(left[1]<right[1]) {
			left[1]=right[1];
		}else {
			left[1]=left[1];
		}
		
		return left;
		
	}
	
	

}
