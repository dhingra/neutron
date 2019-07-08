package com.rd.neutron.quickfix.order;

import java.math.BigDecimal;
import java.util.Currency;
import com.rd.neutron.pattern.Cacheable;


/**
 * Order attributes that are common between new and replace orders.
 * @author Rohit Dhingra
 * @version 
 * @since 1.0.0
 */


public interface NewOrReplaceOrder extends Cacheable {
    /**
     * Gets the OrderType for the Order.
     *
     * @return the order type.
     */
    OrderType getOrderType();

    /**
     * Sets the OrderType for the Order.
     *
     * @param inOrderType the order type.
     */
    void setOrderType(OrderType inOrderType);

    /**
     * Gets the time in force value for the Order. If a value
     * is not specified, it defaults to
     * {@link org.marketcetera.trade.TimeInForce#Day}.
     *
     * @return the time in force value.
     */
    OrderTerm getTimeInForce();

    /**
     * Sets the time in force value for the Order.
     *
     * @param inTimeInForce the time in force value.
     */
    void setTimeInForce(OrderTerm inTimeInForce);

    /**
     * Gets the limit price for this order. A limit price should be
     * specified when the OrderType is {@link OrderType#Limit}.
     * This value is ignored if the OrderType is not {@link OrderType#Limit}.
     *
     *
     * @return the limit price for the order.
     */
    BigDecimal getPrice();

    /**
     * Sets the limit price for this order. A limit price should be
     * specified when the OrderType is {@link OrderType#Limit}.
     *
     * @param inPrice the limit price for the order.
     */
    void setPrice(BigDecimal inPrice);

    /**
     * Gets the order capacity value for this order.
     *
     * @return the order capacity value.
     */
    OrderCapacity getOrderCapacity();

    /**
     * Sets the order capacity value for this order.
     *
     * @param inOrderCapacity the order capacity value
     */
    void setOrderCapacity(OrderCapacity inOrderCapacity);
    
    /**
     * 
     * @return
     */
    Currency getCurrency();
    
    /**
     * 
     * @param currency
     */
    void setCurrency(Currency currency);

    
}
