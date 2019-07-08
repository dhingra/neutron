package com.rd.neutron.core.processor.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import com.rd.neutron.pattern.Service;

/**
 * Master actor that the actor.
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */

public class NeutronActorMaster extends UntypedActor implements Service {

	private final Logger logger = LoggerFactory.getLogger(NeutronActorMaster.class);
	private final ActorRef neutronActor;

	NeutronActorMaster(final NeutronActor actor) {
		this.neutronActor = getContext().actorOf(
				Props.create(NeutronActor.class));
	}

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof String) {
		} else if (false) {
			logger.info("Shutting down, finished");
			getContext().system().shutdown();
		}
	}

	protected ActorRef getNeutronActor() {
		return neutronActor;
	}

	@Override
	public void startService() throws Exception {

	}

	@Override
	public void stopService() throws Exception {

	}

	@Override
	public boolean isRunning() {

		return false;
	}

	@Override
	public State state() {

		return null;
	}

}
