package com.greedymethods;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Job {
	
	int no;
	int profit;
	int deadline;
	public Job(int no, int profit, int deadline) {
		super();
		this.no = no;
		this.profit = profit;
		this.deadline = deadline;
	}
	
	public static Comparator<Job> comparator = new Comparator<>() {

		@Override
		public int compare(Job o1, Job o2) {
			return o2.profit - o1.profit;
		}
	};
}
public class JobSeq {

	public static void main(String[] args) {
		Job j1 = new Job(1,100,2);
		Job j2 = new Job(2,19,1);
		Job j3 = new Job(3,27,2);
		Job j4 = new Job(4,25,1);
		Job j5 = new Job(5,15,3);
 
		List<Job> jobs = Arrays.asList(j1,j2,j3,j4,j5);
		Collections.sort(jobs,Job.comparator);        
        System.out.println("Result ::"+ TakeJobs(jobs));
		printJobs(jobs);
	}
	private static int TakeJobs(List<Job> jobs) {
		int CompleteJobs = Math.min(jobs.size(), maxDeadline(jobs));
        int[] jobtobedone = new int[CompleteJobs];
        for(Job j : jobs) {
	         if(jobtobedone[j.deadline-1] == 0 ) {
	        	 jobtobedone[j.deadline-1] = j.profit;
	        	 continue;
	         }
	         for(int k=j.deadline-2;k>=0;k--) {
	        	 if(jobtobedone[k] == 0 ) {
		        	 jobtobedone[k] = j.profit; 
		        	 break;
		         }
	         }
		}
        
        int result =0;
        for(int profit : jobtobedone) {
       	   result += profit;
        }
        return result;
	}
	private static int maxDeadline(List<Job> jobs) {
		int deadline = Integer.MIN_VALUE;
		for(Job j : jobs) {
			if(deadline < j.deadline)
				deadline = j.deadline;
		}
		return deadline;
	}
	private static void printJobs(List<Job> jobs) {
		for(Job j : jobs) {
			System.out.println(j.no +" "+j.profit+" "+j.deadline);
		}
	}
}
