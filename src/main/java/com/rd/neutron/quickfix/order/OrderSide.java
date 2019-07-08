package com.rd.neutron.quickfix.order;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * 
 * @author Rohit Dhingra
 * @since 1.0.0
 */
public enum OrderSide {

	/**
	 * Sentinel value for Side that the system is not currently aware of.
	 */
	Unknown(Character.MIN_VALUE),

	/**
	 * A Buy Order.
	 */
	BUY(quickfix.field.Side.BUY),

	/**
	 * A Sell Order.
	 */
	SELL(quickfix.field.Side.SELL),
	
	
	/**
	 * A Sell Short Order.
	 */
	SELL_SHORT(quickfix.field.Side.SELL_SHORT),
	
	/**
	 * A Sell Short Exempt Order.
	 */
	SELL_SHORT_EXEMPT(quickfix.field.Side.SELL_SHORT_EXEMPT);

	private final char mFIXValue;
	private static final Map<Character, OrderSide> mFIXValueMap;
	static {
		Map<Character, OrderSide> table = new HashMap<Character, OrderSide>();
		for (OrderSide s : values()) {
			table.put(s.getFIXValue(), s);
		}
		mFIXValueMap = Collections.unmodifiableMap(table);
	}
	
	/**
	 * Gets the OrderSide instance.
	 * 
	 * @param inValue the FIX char value.
	 * 
	 * @return the Side instance.
	 */
	public static OrderSide getInstanceForFIXValue(char inValue) {
		OrderSide s = mFIXValueMap.get(inValue);
		return s == null ? Unknown : s;
	}

	/**
	 * Creates an instance.
	 * 
	 * @param inFIXValue the FIX char value for this instance.
	 */
	OrderSide(char inFIXValue) {
		mFIXValue = inFIXValue;
	}

	/**
	 * The FIX char value for this instance.
	 * 
	 * @return the FIX char value for this instance.
	 */
	public char getFIXValue() {
		return mFIXValue;
	}

	
}
