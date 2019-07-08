package com.rd.neutron.quickfix.order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Rohit Dhingra
 * @since 1.0.0
 */
public enum OrderTerm {

	
	UNKNOWN(Character.MIN_VALUE),
	/**
     * Order valid for the day.
     */
    DAY(quickfix.field.TimeInForce.DAY),
    /**
     * Order valid until cancelled.
     */
    GOOD_TILL_CANCEL(quickfix.field.TimeInForce.GOOD_TILL_CANCEL),
    /**
     * At market opening.
     */
    AT_THE_OPENING(quickfix.field.TimeInForce.AT_THE_OPENING),
    /**
     * Immediate order execution or cancel.
     */
    IMMEDIATE_OR_CANCEL(quickfix.field.TimeInForce.IMMEDIATE_OR_CANCEL),

    /**
     * Fill or Kill.
     */
    FILL_OR_KILL(quickfix.field.TimeInForce.FILL_OR_KILL),

    /**
     * At market close.
     */
    AT_THE_CLOSE(quickfix.field.TimeInForce.AT_THE_CLOSE);


	/**
	 * The FIX char value for this instance.
	 * 
	 * @return the FIX char value for this instance.
	 */
	public char getFIXValue() {
		return mFIXValue;
	}

	/**
	 * Returns the OrderTerm instance corresponding to supplied FIX field char
	 * value.
	 * 
	 * @param inValue
	 *            the FIX field value.
	 * 
	 * @return the corresponding OrderStatus instance.
	 */
	public static OrderTerm getInstanceForFIXValue(char inValue) {
		OrderTerm status = mFIXValueTable.get(inValue);
		return status == null ? UNKNOWN : status;
	}

	/**
	 * Creates an instance.
	 * 
	 * @param inFIXValue
	 *            the FIX char value for this instance.
	 */
	private OrderTerm(char inFIXValue) {
		mFIXValue = inFIXValue;
	}

	private final char mFIXValue;
	private static final Map<Character, OrderTerm> mFIXValueTable;
	static {
		Map<Character, OrderTerm> table = new HashMap<Character, OrderTerm>();
		for (OrderTerm status : values()) {
			table.put(status.getFIXValue(), status);
		}
		mFIXValueTable = Collections.unmodifiableMap(table);
	}
}
