package com.rd.neutron.quickfix.order;


/* $License$ */
/**
 * ReplaceOrder Implementation. 
 * @author Rohit Dhingra
 * @version $Id: OrderReplaceImpl.java 10309 2009-02-09 20:04:30Z anshul $
 * @since 1.0.0
 */

public class OrderReplaceImpl extends NewOrReplaceOrderImpl implements OrderReplace {

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
        return mDestOrderID;
    }

    @Override
    public void setBrokerOrderID(String inDestOrderID) {
        mDestOrderID = inDestOrderID;
    }

    @Override
    public String toString() {
       return "";
    	/* return Messages.ORDER_REPLACE_TO_STRING.getText(
                String.valueOf(getAccount()),
                String.valueOf(getCustomFields()),
                String.valueOf(getBrokerID()),
                String.valueOf(getOrderCapacity()),
                String.valueOf(getOrderID()),
                String.valueOf(getOrderType()),
                String.valueOf(getOriginalOrderID()),
                String.valueOf(getPositionEffect()),
                String.valueOf(getPrice()),
                String.valueOf(getQuantity()),
                String.valueOf(getSecurityType()),
                String.valueOf(getSide()),
                String.valueOf(getSymbol()),
                String.valueOf(getTimeInForce()),
                String.valueOf(getBrokerOrderID())
        );*/
    }

    /**
     * Creates an uninitialized instance. This constructor is meant to be
     * used by JAXB.
     */
    OrderReplaceImpl() {
    }

    private OrderID mOriginalOrderID;
    private static final long serialVersionUID = 1L;
    private String mDestOrderID;
}
