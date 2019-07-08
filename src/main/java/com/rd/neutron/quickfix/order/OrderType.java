package com.rd.neutron.quickfix.order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  Rohit Dhingra     
 * @since   1.0.0
 */

public enum OrderType {
    /**
     * A Market Price order.
     */
    MARKET(quickfix.field.OrdType.MARKET),
    /**
     * A Limit price order.
     */
    LIMIT(quickfix.field.OrdType.LIMIT),
    
    
    /**
     * A Stop order.
     */
    STOP(quickfix.field.OrdType.STOP),
    /**
     * A Stop Limit order.
     */
    STOP_LIMIT(quickfix.field.OrdType.STOP_LIMIT),
    
    /**
     * Sentinel value for Order Types that the system is not currently
     * aware of.
     */
    Unknown(Character.MIN_VALUE);

    /**
     * The FIX char value for this instance.
     *
     * @return the FIX char value for this instance.
     */
    char getFIXValue() {
        return mFIXValue;
    }

    /**
     * Returns the OrderType instance corresponding to the supplied FIX
     * char value.
     *
     * @param inValue the FIX char value.
     *
     * @return the corresponding OrderType instance.
     */
    public static OrderType getInstanceForFIXValue(char inValue) {
        OrderType ot = mFIXValueMap.get(inValue);
        return ot == null
                ? Unknown
                : ot;
    }

    /**
     * Creates an instance.
     *
     * @param inFIXValue the FIX char value for this instance.
     */
    private OrderType(char inFIXValue) {
        mFIXValue = inFIXValue;
    }
    private final char mFIXValue;
    private static final Map<Character, OrderType> mFIXValueMap;
    static {
        Map<Character, OrderType> table = new HashMap<Character, OrderType>();
        for(OrderType ot: values()) {
            table.put(ot.getFIXValue(), ot);
        }
        mFIXValueMap = Collections.unmodifiableMap(table);
    }
}

