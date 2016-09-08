package com.fmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class Task implements Callable<Boolean> {

	private CyclicBarrier barrier;
	
	public Task(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	@Override
	public Boolean call() {
		
		try (Socket s = new Socket(Properties.HOST, Properties.PORT);
				PrintWriter pw = new PrintWriter(s.getOutputStream());
				BufferedReader br= new BufferedReader(
						new InputStreamReader(s.getInputStream()));) {
			//Waiting barrier
			System.out.println("Wait on the barrier.");
			barrier.await();
			
			//Sending Request
			pw.print(Properties.REQUEST);
			pw.println();
			pw.flush();
			
			//Read the response
			String line = br.readLine();
			System.out.println("Response : " + line);
			System.out.println("Example response : " + Properties.RESPONSE);
			if(Properties.RESPONSE.equals(line)) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static void main(String[] args) {
		Task t = new Task(null);
		t.call();
	}

}
