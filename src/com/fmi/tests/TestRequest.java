package com.fmi.tests;

import static org.junit.Assert.*;

import java.util.concurrent.CyclicBarrier;

import org.junit.Before;
import org.junit.Test;

import com.fmi.Task;

public class TestRequest {
	
	CyclicBarrier barrier;
	
	@Before
	public void setUp() throws Exception {
		barrier = new CyclicBarrier(1);
	}

	@Test
	public void test() {
		Task task = new Task(barrier);
		Boolean res = task.call();
		assertTrue(res);
	}

}
