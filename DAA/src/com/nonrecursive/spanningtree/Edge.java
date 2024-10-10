package com.nonrecursive.spanningtree;

import java.util.Comparator;

public class Edge implements Comparable<Edge> {

	private int wt;
	private int src;
	private int dest;
	
	
	public Edge() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Edge(int wt, int src, int dest) {
		super();
		this.wt = wt;
		this.src = src;
		this.dest = dest;
	}

	public int getWt() {
		return wt;
	}
	public void setWt(int wt) {
		this.wt = wt;
	}
	public int getSrc() {
		return src;
	}
	public void setSrc(int src) {
		this.src = src;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}

	@Override
	public String toString() {
		return "Edge [wt=" + wt + ", src=" + src + ", dest=" + dest + "]";
	}


	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.getWt()-o.getWt();
	}
	
	
}
