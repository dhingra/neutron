package com.rd.neutron.pattern;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public interface Service {

	/**
	 * Starts this component
	 * 
	 * @throws Component
	 *             specific Exception
	 */
	void startService() throws Exception;

	/**
	 * stops this component
	 * 
	 * @throws Component
	 *             specific Exception
	 */
	void stopService() throws Exception;

	boolean isRunning();

	State state();

	enum State {
		/**
		 * A service in this state is inactive. It does minimal work and
		 * consumes minimal resources.
		 */
		NEW,

		/**
		 * A service in this state is transitioning to {@link #RUNNING}.
		 */
		STARTING,

		/**
		 * A service in this state is operational.
		 */
		RUNNING,

		/**
		 * A service in this state is transitioning to {@link #TERMINATED}.
		 */
		STOPPING,

		/**
		 * A service in this state has completed execution normally. It does
		 * minimal work and consumes minimal resources.
		 */
		TERMINATED,

		/**
		 * A service in this state has encountered a problem and may not be
		 * operational. It cannot be started nor stopped.
		 */
		FAILED;

	}
}
