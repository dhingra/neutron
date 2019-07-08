package com.rd.neutron.quickfix.order;



import com.rd.neutron.pattern.Cacheable;

/**
 * Instances of this class uniquely identify a FIX Broker to which an order can
 * be sent or from which a response to a request can be received.
 * 
 * @author Rohit Dhingra
 * @version 
 * @since 1.0.0
 */

public class BrokerID implements Cacheable {

	private final String mValue;
    private static final long serialVersionUID = 1L;
    
        
    /**
     * Creates an instance.
     *
     * @param inValue the string ID value. Cannot be null.
     */
    public BrokerID(String inValue) {
        if(inValue == null) {
            throw new NullPointerException();
        }
        mValue = inValue;
    }
	/**
     * Returns the text value of the ID.
     *
     * @return the text value of the ID.
     */
    public String getValue() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrokerID that = (BrokerID) o;

        return mValue.equals(that.mValue);

    }

    @Override
    public int hashCode() {
        return mValue.hashCode();
    }
    
    @Override
    public String toString() {
        return getValue();
    }
    

    
}
