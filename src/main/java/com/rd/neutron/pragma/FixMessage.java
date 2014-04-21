package com.rd.neutron.pragma;


/**
 * 
 * @author Rohit Dhingra
 * @see 
 * @since 1.0.0
 */
public interface FixMessage {
	// returns a String value of the FIX tag, given its ID.
	String get(int fixTagID);

}
