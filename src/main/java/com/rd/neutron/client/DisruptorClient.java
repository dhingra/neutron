package com.rd.neutron.client;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rd.neutron.model.Trade;
import com.lmax.disruptor.EventFactory;
import com.rd.neutron.engine.disruptor.NeutronDisruptor;
import com.rd.neutron.engine.disruptor.TradeEventProcessor;
import com.rd.neutron.event.Event;

public class DisruptorClient {

	public static void main(String args[]) throws Exception {
		final EventFactory<Event<Trade<String>>> eventFactory = Event.factory();
		final ExecutorService executor = Executors.newCachedThreadPool();
		// ExecutorService executor = Executors.newSingleThreadExecutor();
		// ExecutorService executor = Executors.newFixedThreadPool(1) ;
		final TradeEventProcessor<Trade<String>> tradeEventProcessor = new TradeEventProcessor<>();
		final NeutronDisruptor<Trade<String>> disruptor = new NeutronDisruptor<>(
				eventFactory, 65536, executor, tradeEventProcessor);
		disruptor.startService();
		long startTime = System.currentTimeMillis();
		// System.out.println("##startTime:" + startTime);
		for (long i = 1; i <= 100000; i++) {
			String uuid = UUID.randomUUID().toString();
			// Two phase commit. Grab one of the 1024 slots

			final Trade<String> trade = new Trade<>(uuid);
			disruptor.handleRequest(trade);
		}

		final long endTime = System.currentTimeMillis();
		final long latency = endTime - startTime;
		System.out.println("##latency:" + latency);
		disruptor.stopService();

	}

}
