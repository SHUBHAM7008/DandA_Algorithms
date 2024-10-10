package com.greedymethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Edge{
	int wt ;
	int src;
	int des;
	public Edge(int wt, int src, int des) {
		super();
		this.wt = wt;
		this.src = src;
		this.des = des;
	}
}
public class Kruskal {
	static int[] parent;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter no of edges : ");
		int num = sc.nextInt();
		

	   List<Edge> edges = new ArrayList<>(num);
		System.out.println("Format: weight  source  destination");
	   for(int i=0;i<num;i++) {
	       Edge e = new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt());
	       edges.add(e);
	   }	
        parent = new int[num];
       for(int i=0;i<parent.length;i++) {
    	   parent[i]=-1;
       }
       List<Edge> result = new ArrayList<>(edges.size());
       Collections.sort(edges,(w1,w2)->Integer.compare(w1.wt, w2.wt));
       
       for(Edge e : edges) {
    	   if(union(e.src,e.des)) {
    		   result.add(e);
    	   }
       }
       
       printResult(result);
       
	}
	
	private static void printResult(List<Edge> result) {
		System.out.println("=== RESULT ===\n   Src Des");
		for(Edge e : result) {
	    	   System.out.println("    "+e.src +"   "+ e.des);
	       }
	}

	public static int findparent(int node) {
		if(parent[node]==-1) {
			return node;
		}
		return findparent(parent[node]);
	}
  
   public static boolean union(int src , int des) {
	   int a = findparent(src);
	   int b = findparent(des);
	   if(a==b) return false;
	   parent[b] = a;
	   return true;
   }	
}
