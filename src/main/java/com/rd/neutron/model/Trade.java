package com.rd.neutron.model;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public class Trade<T> extends OrderManagementOperation {

	private T tradeId;

	public Trade(final T tradeId) {
		this.tradeId = tradeId;

	}

	public T getTradeId() {
		return tradeId;
	}

	public String toString() {
		return "tradeId:" + tradeId;
	}

}
