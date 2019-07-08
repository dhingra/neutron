package com.rd.neutron.core.processor.disruptor;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
import java.util.concurrent.ExecutorService;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.rd.neutron.event.Event;
import com.rd.neutron.pattern.Service;

public class NeutronDisruptor<T> extends Disruptor<Event<T>> implements Service {

	private Service.State state;
	private RingBuffer<Event<T>> ringBuffer;
	// private EventHandler eventHandler;
	// private CompletionService eventCompletionService;
	private ExecutorService executor;

	public NeutronDisruptor(EventFactory<Event<T>> eventFactory,
			int ringBufferSize, final ExecutorService executor,
			final EventHandler<Event<T>> eventHandler) {
		super(eventFactory, ringBufferSize, executor);
		this.executor = executor;
		ringBuffer = new RingBuffer<Event<T>>(eventFactory,
				new SingleThreadedClaimStrategy(ringBufferSize),
				new SleepingWaitStrategy());

		SequenceBarrier barrier = ringBuffer.newBarrier();
		BatchEventProcessor<Event<T>> eventProcessor = new BatchEventProcessor<Event<T>>(
				ringBuffer, barrier, eventHandler);

		/** Build dependency graph */
		handleEventsWith(eventHandler);
		ringBuffer.setGatingSequences(eventProcessor.getSequence());
		state = Service.State.NEW;
		System.out.println("New Disruptor created...");
	}

	public void handleRequest(T t) {
		// String uuid = UUID.randomUUID().toString();
		// Two phase commit. Grab one of the 1024 slots
		long seq = ringBuffer.next();
		Event<T> event = ringBuffer.get(seq);
		event.setEvent(t);
		ringBuffer.publish(seq);
	}

	@Override
	public void startService() throws Exception {
		// TODO complete this logic.
		if (state == Service.State.NEW) {
			state = Service.State.STARTING;
			ringBuffer = start();
			state = Service.State.RUNNING;
			System.out.println("Disruptor started...");
		} else {

		}
	}

	@Override
	public void stopService() throws Exception {
		state = Service.State.STOPPING;
		shutdown();
		executor.shutdown();
		state = Service.State.TERMINATED;
		System.out.println("Disruptor stopped...");
	}

	@Override
	public boolean isRunning() {
		return state == Service.State.RUNNING;
	}

	@Override
	public State state() {
		return state;
	}

}
