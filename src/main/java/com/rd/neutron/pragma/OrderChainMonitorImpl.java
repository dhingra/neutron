package com.rd.neutron.pragma;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public class OrderChainMonitorImpl implements OrderChainMonitor {

	@Override
	public void onBuySideMessage(FixMessage fixmessage) {

	}

	@Override
	public void onSellSideMessage(FixMessage fixmessage) {

	}

	@Override
	public int getFilledQty(String clOrdId) {
		return 0;
	}

	@Override
	public int getLeavesQty(String clOrdId) {
		return 0;
	}

}
