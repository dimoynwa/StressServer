package com.fmi;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws Exception {
		int n = Properties.DEF_NUMBER_OF_REQUESTS;
		if(args.length > 0) {
			try {
				n = Integer.parseInt(args[0]);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Going to test with " + n + " requests.");
		CyclicBarrier barrier = new CyclicBarrier(n);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		List<Task> tasks = new ArrayList<>(n);
		for(int i = 0; i < n; ++i) {
			tasks.add(new Task(barrier));
		}
		
		List<Future<Boolean>> results = executor.invokeAll(tasks);
		
		int numberOfSuccesses = 0;
		int numberOfErrors = 0;
		
		for(Future<Boolean> f : results) {
			if(f.get()) {
				numberOfSuccesses ++;
			} else {
				numberOfErrors ++;
			}
		}
		
		System.out.println("Success requests : " + numberOfSuccesses);
		System.out.println("Errors : " + numberOfErrors);
		
		executor.shutdown();
	}

}
