package com.rd.neutron.quickfix;


/**
 * Excpetion to denote that we ran out of IDs while getting them
 * @author 
 * @version 
 */

public class NoMoreIDsException extends Exception
{
    private static final long serialVersionUID = -6403447553151646661L;

    public NoMoreIDsException(Throwable nested)
    {
        super(nested);
    }
    public NoMoreIDsException(String message)
    {
        super(message);
    }

    public NoMoreIDsException(Throwable nested, String message)
    {
        //super(nested, message);
    }

}
