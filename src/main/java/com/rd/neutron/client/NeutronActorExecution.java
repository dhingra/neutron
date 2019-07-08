package com.rd.neutron.client;

import com.rd.neutron.core.processor.actors.NeutronActor;
import com.rd.neutron.core.processor.actors.NeutronActorMaster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
*
* @author  Rohit Dhingra 
* @see     
* @since   1.0.0
*/
public class NeutronActorExecution {
	
	
	public NeutronActorExecution(){
		
	}
	
	
	/**
	 * 
	 * @param actor
	 */
    public void execute(final NeutronActor actor) {
        ActorSystem actorSystem = ActorSystem.create();
        ActorRef master = actorSystem.actorOf(Props.create(NeutronActorMaster.class, actor));
        //master.tell(actorSystem.guardian());
        actorSystem.awaitTermination();
    }
    
    public static void main(String[] args) {
    	NeutronActorExecution execution = new NeutronActorExecution();    	
       
    }
	

}

