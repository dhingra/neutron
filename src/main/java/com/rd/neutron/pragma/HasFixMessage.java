package com.rd.neutron.pragma;
import quickfix.Message;

/**
 * Object associated with this class has FIX Message
 * @author Rohit Dhingra
 * @see  quickfix.Message
 * @since 1.0.0
 */
public interface HasFixMessage {
	
	/**
	 * Thread-safety is delegated to Implementor class.
	 * @return the FIX message associated with this object.
	 */
	public Message getMessage();

}
