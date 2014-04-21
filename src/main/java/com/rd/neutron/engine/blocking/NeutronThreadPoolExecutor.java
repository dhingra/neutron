package com.rd.neutron.engine.blocking;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.rd.neutron.pattern.Service;

public class NeutronThreadPoolExecutor extends ThreadPoolExecutor implements
		Service {

	private Service.State state;
	private final ThreadLocal<Long> startTime;
	private final AtomicLong numTasks;
	private final AtomicLong totaltime;

	/**
	 * 
	 * @param corePoolSize
	 */
	public NeutronThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		startTime = new ThreadLocal<>();
		numTasks = new AtomicLong();
		totaltime = new AtomicLong();

	}

	/**
	 * 
	 * @param corePoolSize
	 * @param threadFactory
	 */
	public NeutronThreadPoolExecutor(int corePoolSize,
			ThreadFactory threadFactory) {
		super(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(), threadFactory);
		startTime = new ThreadLocal<>();
		numTasks = new AtomicLong();
		totaltime = new AtomicLong();

	}

	/**
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param threadFactory
	 * @param handler
	 */
	public NeutronThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		startTime = new ThreadLocal<>();
		numTasks = new AtomicLong();
		totaltime = new AtomicLong();

	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		startTime.set(System.currentTimeMillis());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		if (t != null) {
			System.out.println("exception occured");
		}
		try {
			long endTime = System.currentTimeMillis();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totaltime.addAndGet(taskTime);
			// System.out.println(String.format("Thread %s: end %s, time=%dns",
			// t, r, taskTime));
			// System.out.println(String.format("Number of tasks: %s, totaltime=%dms",
			// numTasks.get(), totaltime.get()));
		} finally {
			super.afterExecute(r, t);
		}

	}

	@Override
	public void startService() throws Exception {

	}

	@Override
	public void stopService() throws Exception {

	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public State state() {
		return null;
	}

	public void statistics() {

		System.out.println(String.format("Number of tasks: %s, totaltime=%dms",
				numTasks.get(), totaltime.get()));

	}

}
