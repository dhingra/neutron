package com.rd.neutron.quickfix.order;


/**
 * An order to replace a previously placed order. Instances of this
 * type can be created via {@link Factory#createOrderReplace(ExecutionReport)}.
 *
 * @author anshul@marketcetera.com
 * @version $Id: OrderReplace.java 10229 2008-12-09 21:48:48Z klim $
 * @since 1.0.0
 */

public interface OrderReplace extends TradeMessage, RelatedOrder, NewOrReplaceOrder {
}
