/**
 * 
 */
package com.rd.neutron.pattern;

/**
 * 
 * @author Rohit Dhingra 
 * @param <T> type this factory will return
 * @param <E>
 */
public interface Factory<T, E extends Throwable> {
	/**
	 * 
	 * @return a new Instance of T
	 * @throws E
	 */
	T create() throws E;

}
