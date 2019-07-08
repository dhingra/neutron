package com.rd.neutron.quickfix.order;



/**
 * A Single Order to trade a instrument.
 *
 * @author Rohit Dhingra
 * @version 
 * @since 1.0.0
 */


public interface OrderSingle extends TradeMessage, NewOrReplaceOrder, Cloneable, OrderI {
    /**
     * Creates clone of this order.
     *
     * @return the clone of this order
     */
    public OrderSingle clone();
}
