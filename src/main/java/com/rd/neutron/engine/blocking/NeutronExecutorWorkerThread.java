package com.rd.neutron.engine.blocking;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */

public class NeutronExecutorWorkerThread<T> implements Runnable {

	private NeutronThreadPoolExecutor neutronThreadPoolExecutor;
	private LinkedBlockingQueue<T> queue;

	private static enum Singleton {
		INSTANCE;

		private static final NeutronExecutorWorkerThread singleton = new NeutronExecutorWorkerThread();

		public NeutronExecutorWorkerThread getSingleton() {
			return singleton;
		}

	}

	/**
	 * 
	 * @return
	 */
	public static NeutronExecutorWorkerThread getInstance() {
		return NeutronExecutorWorkerThread.Singleton.INSTANCE.getSingleton();
	}

	/**
	 * private constructor to enforce singleton.
	 */
	private NeutronExecutorWorkerThread() {
		neutronThreadPoolExecutor = new NeutronThreadPoolExecutor(3);
		queue = new LinkedBlockingQueue<>();
	}

	/**
	 * 
	 * @param coomand
	 */
	public void execute(Runnable command) {
		neutronThreadPoolExecutor.execute(command);
	}

	@Override
	public void run() {
		System.out.println("Request handler started.");
		while (true) {
			T t;
			try {
				t = queue.take();
				Request request = new Request(t);
				execute(request);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param t
	 */
	public void submitRequest(T t) {
		try {
			queue.put(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author rohitdhingra
	 * 
	 */
	class Request implements Runnable {
		T t;

		Request(T t) {
			this.t = t;
		}

		@Override
		public void run() {
			// System.out.println(t);
		}
	}

	public void statistics() {

		neutronThreadPoolExecutor.statistics();
	}
}
