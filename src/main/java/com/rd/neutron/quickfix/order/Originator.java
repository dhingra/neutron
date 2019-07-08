package com.rd.neutron.quickfix.order;



/* $License$ */
/**
 * Identifies the type of entity that originated the report.
 *
 * @author anshul@marketcetera.com
 * @version $Id: Originator.java 10229 2008-12-09 21:48:48Z klim $
 * @since 1.0.0
 */

public enum Originator {
    /**
     * Indicates that the report was originated by the system's server.
     */
    Server,
    /**
     * Indicates that the report was originated by the FIX broker. 
     */
    Broker
}
