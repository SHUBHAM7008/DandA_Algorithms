package com.greedymethods;

import java.util.*;

public class OptMergeP {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(20,30,10,5,30);
        
		PriorityQueue<Integer> pq = new PriorityQueue<>(list);
		//printQ(pq);
		int pqsize=pq.size();
        int total=0;
        for(int i=0;i<pqsize-1;i++) {
        	int sum =pq.poll()+pq.poll();
        	total+=sum;
        	System.out.print(sum+" ");
        	pq.add(sum);
        }
        System.out.print("\nTotal : "+total);
	}

	private static void printQ(PriorityQueue<Integer> pq) {
		int size = pq.size();
		for(int i=0;i<size;i++) {
			System.out.println(pq.poll());
		}
	}
	
	
}
