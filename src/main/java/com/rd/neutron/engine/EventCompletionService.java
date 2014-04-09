
package com.rd.neutron.engine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public class EventCompletionService<V> extends ExecutorCompletionService<V> {

	/**
	 * 
	 * @param executor
	 * @param completionQueue
	 */
	public EventCompletionService(Executor executor,
			BlockingQueue<Future<V>> completionQueue) {
		super(executor, completionQueue);

	}

}
