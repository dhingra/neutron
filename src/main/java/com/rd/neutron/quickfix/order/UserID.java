package com.rd.neutron.quickfix.order;

import com.rd.neutron.pattern.Cacheable;



/* $License$ */
/**
 * Instances of this class uniquely identify a user (trader).
 * 
 * @author tlerios@marketcetera.com
 * @since 1.5.0
 * @version $Id: UserID.java 10492 2009-04-14 00:11:35Z klim $
 */

public class UserID implements Cacheable {

    /**
     * Returns the long value of the ID.
     *
     * @return the long value of the ID.
     */
    public long getValue() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserID that = (UserID) o;

        return (mValue==that.mValue);

    }

    @Override
    public int hashCode() {
        return (int)mValue;
    }
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
    /**
     * Creates an instance.
     *
     * @param inValue the long ID value.
     */
    public UserID(long inValue) {
        mValue = inValue;
    }

    /**
     * Creates a new ID. This empty constructor is intended for use
     * by JAXB.
     */

    protected UserID() {
        mValue = 0;
    }

    
    private final long mValue;
    private static final long serialVersionUID = 1L;
}
