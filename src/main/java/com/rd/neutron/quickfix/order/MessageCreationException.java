package com.rd.neutron.quickfix.order;


/* $License$ */
/**
 * Indicates an error when creating a message instance. Instances of this
 * exception are typically thrown when a FIX Message cannot be wrapped
 * by the factory into a message that the system is capable of dealing with.
 *
 * @author 
 * @version $Id: MessageCreationException.java 10229 2008-12-09 21:48:48Z klim $
 * @since 1.0.0
 */

public class MessageCreationException extends Exception {
    /**
     * Creates a new instance.
     *
     * @param message the message.
     */
    public MessageCreationException(String message) {
        super(message);
    }

    /**
     * Creates a new instance.
     *
     * @param cause the cause for this exception.
     * @param message the message.
     */
    public MessageCreationException(Throwable cause,
                                    String message) {
        //super(cause, message);
    }

    private static final long serialVersionUID = 1L;

}
