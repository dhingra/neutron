package com.rd.neutron.quickfix.order;



/* $License$ */
/**
 * An order to cancel a previously placed order. Instances
 * of this order can be created via
 * {@link Factory#createOrderCancel(ExecutionReport)}.
 *
 * @author Rohit Dhingra
 * @since 1.0.0
 */

public interface OrderCancel extends TradeMessage, RelatedOrder {
	 void setTimeInForce(OrderTerm inTimeInForce); //Rohit Dhingra
   
     OrderTerm getTimeInForce() ;
}
