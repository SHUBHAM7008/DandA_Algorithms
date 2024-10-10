package com.greedymethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ObjectClass {
	int no;
	float profit;
	float weight;
	float PperW;
	float add;
	public ObjectClass(int no, float profit, float weight) {
		super();
		this.no = no;
		this.profit = profit;
		this.weight = weight;
		PperW = (this.profit/this.weight);
		add=0;
	}
	public static Comparator<ObjectClass> comparator = new Comparator<>() {
		
		public int compare(ObjectClass o1,ObjectClass o2) {
			return Float.compare(o2.PperW, o1.PperW);
		}
	};	
}
public class Knapsack {

	public static void main(String[] args) {
		
		ObjectClass o1 = new ObjectClass(1,10,2);
		ObjectClass o2 = new ObjectClass(2,5,3);
		ObjectClass o3 = new ObjectClass(3,15,5);
		ObjectClass o4 = new ObjectClass(4,7,7);
		ObjectClass o5 = new ObjectClass(5,6,1);
		ObjectClass o6 = new ObjectClass(6,18,4);
		ObjectClass o7 = new ObjectClass(7,3,1);
		
		List<ObjectClass> resultObjs = new ArrayList<>();
		
		List<ObjectClass> listObj = Arrays.asList(o1,o2,o3,o4,o5,o6,o7); 
		int n =7;
		int m=15;
		float resultProfit=0;
		Collections.sort(listObj,ObjectClass.comparator);
		
		for(ObjectClass o : listObj) {
			if(o.weight <= m) {
				resultProfit+=o.profit;
				m-=o.weight;
				resultObjs.add(o);
				o.add =1;
				continue;
			}
			resultProfit += o.PperW*m;
			o.add = m/o.weight;
			resultObjs.add(o);
			break;	
		}
		//printList(resultObjs);
		System.out.print("Result : "+resultProfit);	
	}
	private static void printList(List<ObjectClass> listObj) {
		for(ObjectClass o : listObj) {
			System.out.println(o.no+" - "+o.PperW +"ADD "+o.add);
		}
	}
}
