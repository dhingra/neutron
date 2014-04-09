package com.rd.neutron.cache;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */

public class NeutronCache<K, V> {

	private LinkedBlockingQueue<K> queue;
	private ConcurrentHashMap<K, V> cache;

	private static enum Singleton {
		INSTANCE;
		private static final NeutronCache singleton = new NeutronCache();

		public NeutronCache getSingleton() {
			return singleton;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static NeutronCache getInstance() {
		return NeutronCache.Singleton.INSTANCE.getSingleton();
	}

	/**
	 * private constructor to enforce singleton.
	 */
	private NeutronCache() {
		cache = new ConcurrentHashMap<>(); // TODO: Add support for Concurrency
											// level?
		queue = new LinkedBlockingQueue<>();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		cache.put(key, value);
		try {
			queue.put(key);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return cache.get(key);
	}

	/**
	 * 
	 * @return
	 */
	public LinkedBlockingQueue<K> getEventsQueue() {
		return queue;
	}
}
