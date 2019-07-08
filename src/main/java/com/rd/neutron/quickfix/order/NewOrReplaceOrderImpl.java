package com.rd.neutron.quickfix.order;

import java.math.BigDecimal;
import java.util.Currency;



/**
 * 
 * @author Rohit Dhingra
 * @version 
 * @since 1.0.0
 */


public class NewOrReplaceOrderImpl extends OrderBaseImpl  implements NewOrReplaceOrder {
	
	private static final long serialVersionUID = 1L;
	private OrderCapacity mOrderCapacity;
    private OrderType mOrderType;
    private OrderTerm mTimeInForce;
    private BigDecimal mPrice;
    private Currency currency;
    
	
    @Override
    public OrderType getOrderType() {
        return mOrderType;
    }

    @Override
    public void setOrderType(OrderType inOrderType) {
        mOrderType = inOrderType;
    }

    @Override
    public OrderTerm getTimeInForce() {
        return mTimeInForce;
    }

    @Override
    public void setTimeInForce(OrderTerm inTimeInForce) {
        mTimeInForce = inTimeInForce;
    }

    @Override
    public BigDecimal getPrice() {
        return mPrice;
    }

    @Override
    public void setPrice(BigDecimal inPrice) {
        mPrice = inPrice;
    }

    @Override
    public OrderCapacity getOrderCapacity() {
        return mOrderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity inOrderCapacity) {
        mOrderCapacity = inOrderCapacity;
    }

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(Currency inCurrency) {
		currency = inCurrency;		
	}



}
