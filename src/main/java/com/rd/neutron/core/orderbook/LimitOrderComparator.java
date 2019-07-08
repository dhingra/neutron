package com.rd.neutron.core.orderbook;
import java.util.Comparator;
/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public class LimitOrderComparator implements Comparator<LimitOrder> {

	@Override
	public int compare(LimitOrder o1, LimitOrder o2) {
		return o1.getPrice().compareTo(o2.getPrice());
	}

}

