package com.rd.neutron.quickfix.order;


import com.rd.neutron.pattern.Cacheable;


/**
 * Common interface for Orders that can either be sent to any
 * broker or are created to work with a
 * specific broker.
 *
 * This message type is not meant to be used directly.
 *  
 * @author Rohit Dhingra 
 *
 * @since 1.0.0
 */

public interface Order extends Cacheable {
    /**
     * Gets the security type for the Order.
     *
     * @return the security type for the Order.
     */
    SecurityType getSecurityType();

    /**
     * Gets the brokerID to which this order should be sent.
     * The brokerID can be optionally specified to over-ride the
     * default order routing mechanisms used on the server to route
     * this order to the appropriate broker.
     *
     * @return the brokerID to send this order to.
     */
    BrokerID getBrokerID();

    /**
     * Sets the brokerID to which this order should be sent.
     *
     * @param inBrokerID the brokerID to send this order to.
     */
    void setBrokerID(BrokerID inBrokerID);
}
