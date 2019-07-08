package com.rd.neutron.quickfix.order;


import quickfix.Message;
import quickfix.Field;
import quickfix.StringField;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;


/* $License$ */
/**
 * Base class for all messages that wrap a FIX Message.
 *
 * @author anshul@marketcetera.com
 * @version $Id: FIXMessageWrapper.java 10613 2009-06-21 07:26:05Z tlerios $
 * @since 1.0.0
 */

class FIXMessageWrapper implements FIXMessageSupport {
    /**
     * Creates an instance.
     *
     * @param inMessage The FIX Message instance.
     */
    public FIXMessageWrapper(Message inMessage) {
        if(inMessage == null) {
            throw new NullPointerException();
        }
        mMessage = inMessage;
    }

    /**
     * Creates an instance. This empty constructor is intended for use
     * by JAXB.
     */

    protected FIXMessageWrapper() {
        mMessage = null;
    }

    @Override
    public Message getMessage() {
        return mMessage;
    }

    @Override
    public synchronized Map<Integer, String> getFields() {
        if(mFields == null) {
            Map<Integer, String> map = new HashMap<Integer, String>();
            Iterator<Field<?>> iterator = getMessage().iterator();
            while(iterator.hasNext()) {
                Field<?> f = iterator.next();
                if(f instanceof StringField) {
                    map.put(f.getTag(),((StringField)f).getValue());
                }
            }
            mFields = Collections.unmodifiableMap(map);
        }
        return mFields;
    }
    private transient Map<Integer,String> mFields;
   
    private final Message mMessage;
    private static final long serialVersionUID = 1L;
}
