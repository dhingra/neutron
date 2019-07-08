package com.rd.neutron.quickfix;


import quickfix.FieldNotFound;
import quickfix.Message;

/**
 * Exception with multiple constructors designed to handle and create messages
 * for various FIX-related errors
 *
 * @author Toli Kuznets
 * @version $Id: MarketceteraFIXException.java 16841 2014-02-20 19:59:04Z colin $
 */

public class MarketceteraFIXException extends Exception {
    public MarketceteraFIXException(String message) {
        super(message);
    }

    public MarketceteraFIXException(Throwable nested, String msg) {
        super(nested);
    }

    public MarketceteraFIXException(Throwable nested) {
        super(nested);
    }

    public static MarketceteraFIXException createFieldNotFoundException(FieldNotFound fnf) {
        String fieldName = CurrentFIXDataDictionary.getCurrentFIXDataDictionary().getHumanFieldName(fnf.field);
        return new MarketceteraFIXException(fnf, "Messages.FIX_FNF_NOMSG");
    }

    public static MarketceteraFIXException createFieldNotFoundException(FieldNotFound fnf, Message message) {
        String fieldName = CurrentFIXDataDictionary.getCurrentFIXDataDictionary().getHumanFieldName(fnf.field);
        return new MarketceteraFIXException(fnf,"FIX_FNF_MSG");
    }
    private static final long serialVersionUID = 5430289756961955558L;
}
