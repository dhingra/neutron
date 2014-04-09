/**
 * 
 */
package com.rd.neutron.pattern;

/**
 * 
 * @author Rohit Dhingra
 *
 * @param <O> 
 * @param <I> type of payload
 * @param <E> type of implementation specific Exception
 */
public interface Handler<O, I, E extends Throwable> {
	
	/**
	 * 
	 * @param payload to handle
	 * @return
	 * @throws E
	 */
	O handle(I payload) throws E;

}
