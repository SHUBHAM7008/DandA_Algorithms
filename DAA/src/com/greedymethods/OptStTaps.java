package com.greedymethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ListClass{
	List<Integer> list = new ArrayList<>();
}
public class OptStTaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = {3,1,4,2,5};
		Arrays.sort(arr);
		int tap = 3;
		List<List<Integer>> tapes = new ArrayList<>();
		List<Integer> avg = new ArrayList<>(tap);
		
		ListClass[] lists = new ListClass[tap];
		for(int i=0;i<tap;i++) {
			lists[i] = new ListClass();
		}
		
		int i=0;
		for(int a : arr) {
            lists[i].list.add(a); 
			i=(i+1)%tap;
		}
	
		printtapes(lists,arr.length);
	}

	private static void printtapes(ListClass[] lists,int n) {
		int c=0;
		int t=0;
		double MRT=0;
			for(ListClass i1 : lists) {
				c=0;
				t=0;
				 for(Integer i : i1.list) {
					 System.out.println(i);
				     c+=i;
				     t+=c;
				 }
				 MRT+=t;
				 System.out.println("=== ");
			}	
			System.out.println("MRT :: "+(MRT/n));
	}
	
	

}
