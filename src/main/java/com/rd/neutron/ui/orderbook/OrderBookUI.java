package com.rd.neutron.ui.orderbook;

import java.math.BigDecimal;
import java.util.Currency;

import com.rd.neutron.core.orderbook.LimitOrderBook;
import com.rd.neutron.quickfix.order.BrokerID;
import com.rd.neutron.quickfix.order.NewOrReplaceOrderImpl;
import com.rd.neutron.quickfix.order.OrderID;
import com.rd.neutron.quickfix.order.OrderSide;
import com.rd.neutron.quickfix.order.OrderType;
import com.rd.neutron.quickfix.order.SecurityType;
import com.rd.neutron.quickfix.order.TradableInstrument;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public class OrderBookUI {
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	LimitOrderBook orderBook =  LimitOrderBook.getInstance();
		NewOrReplaceOrderImpl order = new NewOrReplaceOrderImpl();
		//Create order
        //OrderSingle orderSingle = Factory.getInstance().createOrderSingle();
        order.setAccount("my account");
        order.setBrokerID(new BrokerID("brokerA"));
        order.setOrderID(new OrderID("ord1"));
        order.setOrderType(OrderType.LIMIT);
        order.setPrice(new BigDecimal("11.36"));
        order.setQuantity(new BigDecimal("100"));
        order.setLeavesQty(new BigDecimal("100"));
        order.setSide(OrderSide.BUY);
        order.setCurrency(Currency.getInstance("USD"));
        //order.setSymbol(new TradableInstrument("IBM"));   
        order.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		orderBook.addOrder(order);
		
		NewOrReplaceOrderImpl order2 = new NewOrReplaceOrderImpl();
		//Create order
        //OrderSingle orderSingle = Factory.getInstance().createOrderSingle();
		order2.setAccount("my account");
		order2.setCurrency(Currency.getInstance("USD"));
		order2.setBrokerID(new BrokerID("brokerA"));
		order2.setOrderID(new OrderID("ord2"));
		order2.setOrderType(OrderType.LIMIT);
		order2.setPrice(new BigDecimal("11.35"));
		order2.setQuantity(new BigDecimal("100"));
		order2.setLeavesQty(new BigDecimal("100"));
		order2.setSide(OrderSide.BUY);
		order2.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		order2.setCurrency(Currency.getInstance("USD"));
		orderBook.addOrder(order2);
		
		
		NewOrReplaceOrderImpl order3 = new NewOrReplaceOrderImpl();
		//Create order
        //OrderSingle orderSingle = Factory.getInstance().createOrderSingle();
		order3.setAccount("my account");
		order3.setCurrency(Currency.getInstance("USD"));
		order3.setBrokerID(new BrokerID("brokerA"));
		order3.setOrderID(new OrderID("ord3"));
		order3.setOrderType(OrderType.LIMIT);
		order3.setPrice(new BigDecimal("11.34"));
		order3.setQuantity(new BigDecimal("125"));
		order3.setLeavesQty(new BigDecimal("125"));
		order3.setSide(OrderSide.BUY);
		//order3.setSymbol(new TradableInstrument("IBM")); 
		order3.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		orderBook.addOrder(order3);
		
		//orderBook.viewOrderBook();
		
		NewOrReplaceOrderImpl order4 = new NewOrReplaceOrderImpl();
		//Create order
        //OrderSingle orderSingle = Factory.getInstance().createOrderSingle();
		order4.setAccount("my account");
		order4.setCurrency(Currency.getInstance("USD"));
		order4.setBrokerID(new BrokerID("brokerA"));
		order4.setOrderID(new OrderID("ord4"));
		order4.setOrderType(OrderType.LIMIT);
		order4.setPrice(new BigDecimal("11.33"));
		order4.setQuantity(new BigDecimal("45"));
		order4.setLeavesQty(new BigDecimal("45"));
		order4.setSide(OrderSide.BUY);
		order4.setCurrency(Currency.getInstance("USD"));
		//order3.setSymbol(new TradableInstrument("IBM")); 
		order4.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		orderBook.addOrder(order4);
		
		
		NewOrReplaceOrderImpl order5 = new NewOrReplaceOrderImpl();
		//Create order
        //OrderSingle orderSingle = Factory.getInstance().createOrderSingle();
		order5.setAccount("my account");
		order5.setCurrency(Currency.getInstance("USD"));
		order5.setBrokerID(new BrokerID("brokerA"));
		order5.setOrderID(new OrderID("ord5"));
		order5.setOrderType(OrderType.LIMIT);
		order5.setPrice(new BigDecimal("11.32"));
		order5.setQuantity(new BigDecimal("45"));
		order5.setLeavesQty(new BigDecimal("45"));
		order5.setSide(OrderSide.BUY);
		//order3.setSymbol(new TradableInstrument("IBM")); 
		order5.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		orderBook.addOrder(order5);
		//orderBook.viewOrderBook();
		
		NewOrReplaceOrderImpl order6 = new NewOrReplaceOrderImpl();
		order6.setCurrency(Currency.getInstance("USD"));
		order6.setAccount("account1");        
		order6.setBrokerID(new BrokerID("brokerA"));
		order6.setOrderID(new OrderID("ord6"));
		order6.setOrderType(OrderType.LIMIT);
		order6.setPrice(new BigDecimal("11.38"));
		order6.setQuantity(new BigDecimal("45"));
		order6.setLeavesQty(new BigDecimal("45"));
		order6.setSide(OrderSide.SELL);
		//order3.setSymbol(new TradableInstrument("IBM")); 
		order6.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		orderBook.addOrder(order6);
		//orderBook.viewOrderBook();
		
		NewOrReplaceOrderImpl order7 = new NewOrReplaceOrderImpl();
		order7.setCurrency(Currency.getInstance("USD"));
		order7.setAccount("account1");        
		order7.setBrokerID(new BrokerID("brokerA"));
		order7.setOrderID(new OrderID("ord7"));
		order7.setOrderType(OrderType.LIMIT);
		order7.setPrice(new BigDecimal("11.39"));
		order7.setQuantity(new BigDecimal("135"));
		order7.setLeavesQty(new BigDecimal("135"));
		order7.setSide(OrderSide.SELL);
		 
		order7.setSymbol(new TradableInstrument("IBM",SecurityType.CommonStock));   
		orderBook.addOrder(order7);
		
		NewOrReplaceOrderImpl order8 = new NewOrReplaceOrderImpl();
		order8.setCurrency(Currency.getInstance("USD"));
		order8.setAccount("account1");        
		order8.setBrokerID(new BrokerID("brokerA"));
		order8.setOrderID(new OrderID("ord8"));
		order8.setOrderType(OrderType.LIMIT);
		order8.setPrice(new BigDecimal("11.40"));
		order8.setQuantity(new BigDecimal("105"));
		order8.setLeavesQty(new BigDecimal("105"));
		order8.setSide(OrderSide.SELL);
		 
		order8.setSymbol(new TradableInstrument("IBM", SecurityType.CommonStock));   
		orderBook.addOrder(order8);
		
		NewOrReplaceOrderImpl order9 = new NewOrReplaceOrderImpl();
		order9.setCurrency(Currency.getInstance("USD"));
		order9.setAccount("account1");        
		order9.setBrokerID(new BrokerID("brokerA"));
		order9.setOrderID(new OrderID("ord9"));
		order9.setOrderType(OrderType.LIMIT);
		order9.setPrice(new BigDecimal("11.33"));
		order9.setQuantity(new BigDecimal("145"));
		order9.setLeavesQty(new BigDecimal("145"));
		order9.setSide(OrderSide.SELL);
		 
		order9.setSymbol(new TradableInstrument("IBM", SecurityType.CommonStock));   
		//orderBook.executeOrder(order9);
		
		orderBook.viewOrderBook();
		
		NewOrReplaceOrderImpl order10 = new NewOrReplaceOrderImpl();
		order10.setCurrency(Currency.getInstance("USD"));
		order10.setAccount("account1");        
		order10.setBrokerID(new BrokerID("brokerA"));
		order10.setOrderID(new OrderID("ord10"));
		order10.setOrderType(OrderType.LIMIT);
		order10.setPrice(new BigDecimal("560"));
		order10.setQuantity(new BigDecimal("145"));
		order10.setLeavesQty(new BigDecimal("145"));
		order10.setSide(OrderSide.SELL);
		 
		order10.setSymbol(new TradableInstrument("APPL", SecurityType.CommonStock));   
		orderBook.addOrder(order10);
		
		OrderBookFrame.main(new String[1]);
    }

}

