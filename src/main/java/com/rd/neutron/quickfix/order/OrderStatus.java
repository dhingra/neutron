package com.rd.neutron.quickfix.order;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */

public enum OrderStatus {
    /**
     * Sentinel value for Order Status that the system is not currently
     * aware of.
     */
    UNKNOWN(Character.MIN_VALUE),
    NEW(quickfix.field.OrdStatus.NEW),    
    PARTIALLY_FILLED(quickfix.field.OrdStatus.PARTIALLY_FILLED),
    FILLED(quickfix.field.OrdStatus.FILLED),
    DONE_FOR_DAY(quickfix.field.OrdStatus.DONE_FOR_DAY),
    CANCELED(quickfix.field.OrdStatus.CANCELED),
    REPLACED(quickfix.field.OrdStatus.REPLACED),
    PENDING_CANCEL(quickfix.field.OrdStatus.PENDING_CANCEL),
    STOPPED(quickfix.field.OrdStatus.STOPPED),
    REJECTED(quickfix.field.OrdStatus.REJECTED),
    SUSPENDED(quickfix.field.OrdStatus.SUSPENDED),
    PENDING_NEW(quickfix.field.OrdStatus.PENDING_NEW),
    CALCULATED(quickfix.field.OrdStatus.CALCULATED),
    EXPIRED(quickfix.field.OrdStatus.EXPIRED),
    ACCEPTED_FOR_BIDDING(quickfix.field.OrdStatus.ACCEPTED_FOR_BIDDING),
    PENDING_REPLACE(quickfix.field.OrdStatus.PENDING_REPLACE);

    /**
     * The FIX char value for this instance.
     *
     * @return the FIX char value for this instance.
     */
    public char getFIXValue() {
        return mFIXValue;
    }

    /**
     * Returns the OrderStatus instance corresponding to supplied FIX
     * field char value.
     *
     * @param inValue the FIX field value.
     *
     * @return the corresponding OrderStatus instance.
     */
    public static OrderStatus getInstanceForFIXValue(char inValue) {
        OrderStatus status = mFIXValueTable.get(inValue);
        return status == null
                ? UNKNOWN
                : status;
    }

    /**
     * Creates an instance.
     *
     * @param inFIXValue the FIX char value for this instance.
     */
    private OrderStatus(char inFIXValue) {
        mFIXValue = inFIXValue;
    }
    private final char mFIXValue;
    private static final Map<Character, OrderStatus> mFIXValueTable;
    static {
        Map<Character, OrderStatus> table = new HashMap<Character, OrderStatus>();
        for(OrderStatus status: values()) {
            table.put(status.getFIXValue(), status);
        }
        mFIXValueTable = Collections.unmodifiableMap(table);
    }
}


