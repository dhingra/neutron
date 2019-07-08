package com.rd.neutron.quickfix.order;

import java.io.Serializable;


/**
 * Instances of<code>OrderID</code> this class uniquely identify an order.
 * If the underlying  message protocol is FIX, this value typically translates to
 * <code>ClOrdID</code> or <code>OrigClOrdID</code> fields.
 * 
 * @author  Rohit Dhingra 
 * @since   1.0.0
 */
public class OrderID implements Serializable {
	
	private String mValue;
	private static final long serialVersionUID = 1L;
	
    /**
     * Creates an instance, given the text value of the OrderID.
     *
     * @param inValue the text value of the OrderID. Cannot be null.
     */
    public OrderID(String inValue) {
        setValue(inValue);
    }

    /**
     * The text value of the orderID.
     *
     * @return the text value of the orderID.
     */
    public String getValue() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderID orderID = (OrderID) o;

        return mValue.equals(orderID.mValue);

    }

    @Override
    public int hashCode() {
        return mValue.hashCode();
    }

    @Override
    public String toString() {
        return getValue();
    }

    /**
     * Sets the value of the ID.
     *
     * @param inValue the value of this ID. Cannot be null
     */
    private void setValue(String inValue) {
        if(inValue == null) {
            throw new NullPointerException();
        }
        mValue = inValue;
    }

}


