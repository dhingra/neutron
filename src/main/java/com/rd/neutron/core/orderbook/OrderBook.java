package com.rd.neutron.core.orderbook;

import com.rd.neutron.quickfix.order.NewOrReplaceOrderImpl;
import com.rd.neutron.quickfix.order.OrderCancelImpl;


/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public interface OrderBook {
	
	
	/**
	 * Place a limit order for a particular price,quantity, <code>OrderTerm</code>
	 * returns true if order was successfully placed (-not- executed) 
     * and false otherwise.    
	 */
	public void addOrder(final NewOrReplaceOrderImpl order);
	
	/**
	 * Cancel a given order. Order is updated with <code> OrderStatus </code>.   
	 * @param order
	 * @return Returns true on successful cancellation with no units
	 * transacted. otherwise false.
	 */
    public boolean cancelOrder(final OrderCancelImpl order);
    
    
    /**
     * 
     * @param order
     * @return
     */
    public boolean executeOrder(final NewOrReplaceOrderImpl order);
    
    /**
     * 
     * @param order
     * @return
     */
    public boolean executeMarketOrder(final NewOrReplaceOrderImpl order);
    
    

}

