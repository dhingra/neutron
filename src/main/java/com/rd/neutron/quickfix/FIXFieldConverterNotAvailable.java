package com.rd.neutron.quickfix;

/**
 * Throws when the {@link FIXDataDictionary} is not found for
 * the specified version of FIX Protocol
 * @author 
 * @version $Id: FIXFieldConverterNotAvailable.java 16841 2014-02-20 19:59:04Z colin $
 */
public class FIXFieldConverterNotAvailable extends Exception {
    public FIXFieldConverterNotAvailable(Throwable nested) {
        super(nested);
    }

    public FIXFieldConverterNotAvailable(String message) {
        super(message);
    }

    public FIXFieldConverterNotAvailable(Throwable nested, String msg) {
        super(nested);
    }
    private static final long serialVersionUID = -3276359903285808328L;
}
