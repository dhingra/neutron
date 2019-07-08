package com.rd.neutron.quickfix.order;

/**
 * Backing object for an order to trade a security. This class is public
 * for the sake of JAXB and is not intended for general use.
 *
 * @author Rohit Dhingra
 * @version 
 * @since 1.0.0
 */

public class OrderSingleImpl extends NewOrReplaceOrderImpl implements OrderSingle {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	OrderSingleImpl() {
    }

  
	
	@Override
    public OrderSingle clone() {
        try {
            // Since this instance has no modifiable fields, 
            // simply return the clone
            return (OrderSingleImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
    }
    }

    @Override
    public String toString() {
        /*return Messages.ORDER_SINGLE_TO_STRING.getText(
                String.valueOf(getAccount()),
                String.valueOf(getCustomFields()),
                String.valueOf(getBrokerID()),
                String.valueOf(getOrderCapacity()),
                String.valueOf(getOrderID()),
                String.valueOf(getOrderType()),
                String.valueOf(getPositionEffect()),
                String.valueOf(getPrice()),
                String.valueOf(getQuantity()),
                String.valueOf(getSecurityType()),
                String.valueOf(getSide()),
                String.valueOf(getSymbol()),
                String.valueOf(getTimeInForce())
        );*/
    	return "";
    }



	@Override
	public OrderSide getOrderSide() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setOrderSide(OrderSide orderSide) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public TradableInstrument getTradableInstrument() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setTradableInstrument(TradableInstrument tradableInstrument) {
		// TODO Auto-generated method stub
		
	}

    
}
