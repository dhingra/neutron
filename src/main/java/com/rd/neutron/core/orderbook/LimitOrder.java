package com.rd.neutron.core.orderbook;

import java.math.BigDecimal;
import java.util.Currency;

import com.rd.neutron.common.Money;
import com.rd.neutron.quickfix.order.NewOrReplaceOrderImpl;
import com.rd.neutron.quickfix.order.TradableInstrument;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public class LimitOrder {
	private Money price;
	private BigDecimal tickSize ;
	private long timeStamp;
	
	/**
	 * 
	 * @param orderRequest
	 * @param tickSize
	 * @param timeStamp
	 */
	public LimitOrder(final BigDecimal price,  final BigDecimal tickSize, final long timeStamp){
		this.price = new Money(price,Currency.getInstance("USD")); //TODO : fix later.
		this.tickSize = tickSize;
		this.timeStamp = timeStamp;			
	}
	
	public Money getPrice() {
		return price;
	}

	

	public static void main(String args[]){
		//Money money = new Money(new BigDecimal(12.27));
		
		//System.out.println(money);
		BigDecimal bd = new BigDecimal(12.2);
		int t = bd.scale();
		System.out.println(t);
	}
	
	@Override
	public String toString(){
		return price.toString();
	}
	
	

}

