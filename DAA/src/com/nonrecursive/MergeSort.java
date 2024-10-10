package com.nonrecursive;
import java.util.*;
public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> arr= Arrays.asList(5,6,9,1,8,44,65,3,2);
		int len=arr.size();
		int size,low=0,high=0,mid=0;
		for(size=2;size<=len;size*=2) {
			for(int i=0;i+size-1<len;i+=size) {
				low=i;
				high=(i+size-1);
				mid=(low+high)/2;
				
				arr=merge(arr,low,mid,high);
			}
		}
		
		if(size/2<len) {
			arr=merge(arr,low,mid,high);
		}
     System.out.print(arr);
	}

	private static List<Integer> merge(List<Integer> arr, int low, int mid, int high) {
		// TODO Auto-generated method stub
		List<Integer> result=new ArrayList<>();
		int i=low;
		int j=mid;
		while(i<mid && j<arr.size()) {
			if(arr.get(i)>arr.get(j)) {
				result.add(arr.get(j));
				j++;
			}else {
				result.add(arr.get(i));
				i++;
			}
		}
		while(i<arr.size()/2) {
			result.add(arr.get(i));
			i++;
		}
		while(j<arr.size()) {
			result.add(arr.get(j));
			j++;
		}
		
		return result;
	}

}
