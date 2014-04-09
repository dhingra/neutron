
package com.rd.neutron.engine;

import com.lmax.disruptor.EventHandler;
import com.rd.neutron.event.Event;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public final class TradeEventProcessor<T> implements EventHandler<Event<T>> {

	public void onEvent(final Event<T> event, final long sequence,
			final boolean endOfBatch) throws Exception {

		// System.out.println("Sequence: " + sequence);
		// System.out.println("ValueEvent: " + event.getEvent());
	}

}
