package com.rd.neutron.quickfix.order;

import java.math.BigDecimal;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public interface OrderI {

	public abstract OrderID getOrderID();

	public abstract void setOrderID(OrderID orderID);

	public abstract OrderSide getOrderSide();

	public abstract void setOrderSide(OrderSide orderSide);

	public abstract BigDecimal getQuantity();

	public abstract void setQuantity(BigDecimal quantity);

	public abstract BrokerID getBrokerID();

	public abstract void setBrokerID(BrokerID brokerID);

	public abstract String getAccount();

	public abstract void setAccount(String account);

	public abstract TradableInstrument getTradableInstrument();

	public abstract void setTradableInstrument(
			TradableInstrument tradableInstrument);

}
