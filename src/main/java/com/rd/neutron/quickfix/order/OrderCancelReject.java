package com.rd.neutron.quickfix.order;



/* $License$ */
/**
 * Represents the rejection of an {@link OrderCancel} or a
 * {@link OrderReplace}. Instances of this message can be created via
 * {@link Factory#createOrderCancelReject(quickfix.Message, BrokerID, Originator, UserID, UserID)}.
 * <p>
 * The enum attributes of this type have a null value, in case a value
 * is not specified for that attribute / field in the underlying FIX Message.
 * However, if the attribute / field has a value that does not have a
 * corresponding value in the Enum type, the sentinel value <code>Unknown</code>
 * is returned to indicate that the value is set but is not currently
 * expressible through the current API.
 *
 * @author anshul@marketcetera.com
 * @version $Id: OrderCancelReject.java 10578 2009-05-19 00:29:27Z anshul $
 * @since 1.0.0
 */

public interface OrderCancelReject extends TradeMessage, ReportBase {
}
