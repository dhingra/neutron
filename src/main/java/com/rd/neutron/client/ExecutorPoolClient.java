package com.rd.neutron.client;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import com.rd.neutron.core.processor.blocking.NeutronExecutorWorkerThread;

public class ExecutorPoolClient<T> {

	private LinkedBlockingQueue<T> queue;
	private NeutronExecutorWorkerThread neutronExecutorWorkerThread;
	private CyclicBarrier barrier;

	public ExecutorPoolClient(final CyclicBarrier barrier) {
		this.barrier = barrier;
		neutronExecutorWorkerThread = NeutronExecutorWorkerThread.getInstance();
		Thread t = new Thread(neutronExecutorWorkerThread);
		t.start();

	}

	public void pumpInRequeust(int k) {
		long startTime = System.currentTimeMillis();
		// System.out.println("##startTime:" + startTime);
		for (long i = 1; i <= 100000; i++) {
			final String uuid = UUID.randomUUID().toString();
			neutronExecutorWorkerThread.submitRequest(uuid);
		}

		// System.out.println("##Done");
		long endTime = System.currentTimeMillis();
		long latency = endTime - startTime;
		System.out.println("##latency:" + latency);

	}

	class Request implements Runnable {
		int k;

		Request(int k) {
			this.k = k;
		}

		@Override
		public void run() {
			System.out.println("Request thread started...");
			long startTime = System.currentTimeMillis();
			for (long i = 1; i <= 100000; i++) {
				final String uuid = UUID.randomUUID().toString();
				neutronExecutorWorkerThread.submitRequest(uuid);

			}
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			// System.out.println("##Done");
			long endTime = System.currentTimeMillis();
			long latency = endTime - startTime;
			System.out.println(String.format("Request latency:=%dms", latency));
			statistics();

		}
	}

	public void statistics() {
		neutronExecutorWorkerThread.statistics();
	}

	public static void main(String args[]) {

		int k = 100000;
		CyclicBarrier barrier = new CyclicBarrier(2);
		ExecutorPoolClient client = new ExecutorPoolClient(barrier);
		ExecutorPoolClient.Request request = client.new Request(k);
		Thread requestThread = new Thread(request);
		requestThread.start();
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		
	}

}
