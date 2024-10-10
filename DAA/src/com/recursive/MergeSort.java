package com.recursive;
import java.util.*;
public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> arr= Arrays.asList(5,6,9,1,8,44,65,3,2);
		System.out.println(DAndC(arr));

	}

	private static List<Integer> DAndC(List<Integer> arr) {

		List<Integer> left,right;
		int len=arr.size();
		if(len==1) {
			return arr;
		}else if(len==2){
			if(arr.get(0)>arr.get(1)) {
			arr.set(0, arr.get(0)^arr.get(1));
			arr.set(1, arr.get(0)^arr.get(1));
			arr.set(0, arr.get(0)^arr.get(1));
			}
			return arr;
		}
		else {
				int mid=len/2;
			 left = DAndC(arr.subList(0,mid));
			 right = DAndC(arr.subList(mid,len));
			 return marge(left,right);	
		}
		
		
	}

	private static List<Integer> marge(List<Integer> left, List<Integer> right) {

		List<Integer> sorted=new ArrayList<>();
		int i=0,j=0;
		while(i<left.size() && j<right.size()) {
			if(left.get(i)>right.get(j)) {
					sorted.add(right.get(j));
					j++;
			}else {
				sorted.add(left.get(i));
				i++;	
			}
		}
		while(i<left.size()) {
			sorted.add(left.get(i));
			i++;
		}
		while(j<right.size()) {
			sorted.add(right.get(j));
			j++;
		}
		return sorted;
	}

}
