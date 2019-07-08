package com.rd.neutron.quickfix.order;


/* $License$ */
/**
 * Order Cancel message implementation. This class is public for the sake
 * of JAXB and is not intended for general use.
 *
 * @author anshul@marketcetera.com
 * @version $Id: OrderCancelImpl.java 10309 2009-02-09 20:04:30Z anshul $
 * @since 1.0.0
 */

public class OrderCancelImpl extends OrderBaseImpl implements OrderCancel {
    @Override
    public OrderID getOriginalOrderID() {
        return mOriginalOrderID;
    }

    @Override
    public void setOriginalOrderID(OrderID inOrderID) {
        mOriginalOrderID = inOrderID;
    }

    @Override
    public String getBrokerOrderID() {
        return mBrokerOrderID;
    }

    @Override
    public void setBrokerOrderID(String inDestOrderID) {
        mBrokerOrderID = inDestOrderID;
    }
    
    public OrderTerm getTimeInForce() {
        return mTimeInForce;
    }

    
    public void setTimeInForce(OrderTerm inTimeInForce) {//Rohit Dhingra
        mTimeInForce = inTimeInForce;
    }
    @Override
    public String toString() {
        /*return Messages.ORDER_CANCEL_TO_STRING.getText(
                String.valueOf(getAccount()),
                String.valueOf(getCustomFields()),
                String.valueOf(getBrokerID()),
                String.valueOf(getOrderID()),
                String.valueOf(getOriginalOrderID()),
                String.valueOf(getQuantity()),
                String.valueOf(getSecurityType()),
                String.valueOf(getSide()),
                String.valueOf(getSymbol()),
                String.valueOf(getBrokerOrderID()),
                String.valueOf(getTimeInForce()) //Rohit Dhingra
        );*/
    	return "";
    }

    /**
     * Creates an uninitialized instance. This constructor is meant to be
     * used by JAXB.
     */
    OrderCancelImpl() {
    }

    private OrderID mOriginalOrderID;
    private String mBrokerOrderID;
    private OrderTerm mTimeInForce;
    private static final long serialVersionUID = 1L;
}
