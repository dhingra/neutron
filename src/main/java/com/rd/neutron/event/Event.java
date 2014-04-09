package com.rd.neutron.event;

import com.lmax.disruptor.EventFactory;
import com.rd.neutron.model.Trade;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */

public final class Event<T> {

	private T event;

	public T getEvent() {

		return event;
	}

	public void setEvent(final T event) {

		this.event = event;
	}

	public static <T> EventFactory<Event<T>> factory() {
		return new EventFactory<Event<T>>() {
			public Event<T> newInstance() {
				return new Event<T>();
			}
		};
	}

	public static void main(String args[]) {
		Trade trade = new Trade("123$");
		Event event = Event.factory().newInstance();
		event.setEvent(trade);
	}
}
