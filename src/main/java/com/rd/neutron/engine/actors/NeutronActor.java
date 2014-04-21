package com.rd.neutron.engine.actors;

import akka.actor.UntypedActor;
import com.rd.neutron.event.Event;
import akka.event.Logging;
import akka.actor.Props;
import akka.japi.Creator;
import akka.event.LoggingAdapter;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public class NeutronActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	/**
	 * Create Props for an actor of this type.
	 * 
	 * @param magicNumber
	 *            The magic number to be passed to this actor’s constructor.
	 * @return a Props for creating this actor, which can then be further
	 *         configured (e.g. calling `.withDispatcher()` on it)
	 */
	public static Props props(final int magicNumber) {
		return Props.create(new Creator<NeutronActor>() {
			private static final long serialVersionUID = 1L;

			@Override
			public NeutronActor create() throws Exception {
				return new NeutronActor(magicNumber);
			}
		});
	}

	final int magicNumber;
	
	/**
	 * 
	 * @param magicNumber
	 */
	public NeutronActor(int magicNumber) {
		this.magicNumber = magicNumber;
	}
	
	/**
	 * 
	 */
	public void onReceive(Object message) throws Exception {
		if (message instanceof Event) {
			log.info("Received String message: {}", message);
			getSender().tell(message, getSelf());
		} else
			unhandled(message);
	}
}