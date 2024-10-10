package com.nonrecursive.spanningtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpanningTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Edge e1 = new Edge(3,1,2);
		Edge e2 = new Edge(1,2,3);
		Edge e3 = new Edge(5,3,4);
		Edge e4 = new Edge(3,4,5);
		Edge e5 = new Edge(2,1,5);
		Edge e6 = new Edge(1,2,5);
		Edge e7 = new Edge(2,3,5);
		
		List<Edge> edges =  Arrays.asList(e1,e2,e3,e4,e5,e6,e7);
		Collections.sort(edges);
		printEdges(edges);
		ArrayList<Edge> tree = new ArrayList<>(edges.size());
		
		ArrayList<Integer> SrcEdge = new ArrayList<>(edges.size());
		ArrayList<Integer> DestEdge = new ArrayList<>(edges.size());
		for(Edge i : edges) {
			SrcEdge.add(i.getSrc());
			DestEdge.add(i.getDest());
		}
		
		boolean flag1,flag2;
		for(Edge i:edges) {
			flag1=false;
			flag2=false;
			if(SrcEdge.contains(i.getSrc()) && DestEdge.contains(i.getDest())) {
				flag1=true;
				break;
			}
			if(!(flag1)) {
				tree.add(i);
			}
		}
		
		printEdges(tree);
	}

	private static void printEdges(List<Edge> edges) {
		for(Edge e:edges) {
			System.out.println(e);
		}
		System.out.println("=============================");
	}

}
