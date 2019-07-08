package com.rd.neutron.pragma;

import quickfix.Message;
import quickfix.Field;
import quickfix.StringField;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Rohit Dhingra
 * @see
 * @since 1.0.0
 */
public class FixMessageWrapper implements HasFixMessage, FixMessage {

	private  Map<Integer, String> mFields;
	private final Message fixMessage;

	/**
	 *  
	 * @param inMessage The FIX Message instance.
	 */
	public FixMessageWrapper(Message inMessage) {
		if (inMessage == null) {
			throw new NullPointerException();
		}
		fixMessage = inMessage;
	}

	@Override
	public Message getMessage() {
		return fixMessage;
	}

	/**
	 * 
	 * @return
	 */
	public synchronized Map<Integer, String> getFields() {
		if (mFields == null) {
			Map<Integer, String> map = new HashMap<Integer, String>();
			Iterator<Field<?>> iterator = getMessage().iterator();
			while (iterator.hasNext()) {
				Field<?> f = iterator.next();
				if (f instanceof StringField) {
					map.put(f.getTag(), ((StringField) f).getValue());
				}
			}
			mFields = Collections.unmodifiableMap(map);
		}
		return mFields;
	}

	@Override
	public String get(int fixTagID) {
		return mFields.get(fixTagID);
	}

}
