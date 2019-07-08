package com.rd.neutron.pragma;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public interface OrderChainMonitor {

	public void onBuySideMessage(FixMessage fixmessage);

	public void onSellSideMessage(FixMessage fixmessage);

	public int getFilledQty(String clOrdId);

	public int getLeavesQty(String clOrdId);

}
