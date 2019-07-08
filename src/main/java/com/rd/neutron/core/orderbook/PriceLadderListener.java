package com.rd.neutron.core.orderbook;

import java.util.List;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public interface PriceLadderListener {
	
	void update(final List<List<String>> priceLadderModel);	
     
}

