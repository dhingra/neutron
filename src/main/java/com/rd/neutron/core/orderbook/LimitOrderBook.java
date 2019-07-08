package com.rd.neutron.core.orderbook;

import java.math.BigDecimal;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.TreeSet;

import com.rd.neutron.quickfix.order.OrderSide;
import com.rd.neutron.common.Money;
import com.rd.neutron.quickfix.order.NewOrReplaceOrderImpl;
import com.rd.neutron.quickfix.order.Order;
import com.rd.neutron.quickfix.order.OrderCancelImpl;
import com.rd.neutron.quickfix.order.SecurityType;
import com.rd.neutron.quickfix.order.TradableInstrument;
import com.rd.neutron.quickfix.util.FIXUtil;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java .util.concurrent.ConcurrentLinkedQueue;

/**
 * <p><code>LimitOrderBook</code> represents the electronic collection
 * of the outstanding limit orders for a financial instrument.
 * 
 * @author  Rohit Dhingra  
 * @since   1.0.0
 */
public class LimitOrderBook implements OrderBook{
	
	private Money bidPrice;
	private Money askPrice;
	private Money askMin;
	private Money askMax;
	private Money bidMax;
    private Money bidMin;
    private LinkedList<PriceLadderListener> priceLadderListenerList;
	
	private ConcurrentHashMap <TradableInstrument, ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>>> orderBook;
	private static enum Singleton {
		INSTANCE;
		private static final LimitOrderBook singleton = new LimitOrderBook();
		
		public LimitOrderBook getSingleton() {
			return singleton;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static LimitOrderBook getInstance() {
		return LimitOrderBook.Singleton.INSTANCE.getSingleton();
	}

	/**
	 * private constructor to enforce singleton.
	 */
	private LimitOrderBook() {
		priceLadderListenerList = new LinkedList<>();
		orderBook = new ConcurrentHashMap<>(); // TODO: Add support for Concurrency level.
		
	}

	@Override
	public void addOrder(final NewOrReplaceOrderImpl order) {		
		final TradableInstrument symbol = order.getSymbol();
		final OrderSide side = order.getSide();
		ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>> map = orderBook.get(symbol);
		ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> timePriorityOrderMap ;
		ConcurrentLinkedQueue<NewOrReplaceOrderImpl> orderQueue;
		final LimitOrder limitOrder = new LimitOrder(order.getPrice(), new BigDecimal(.01),System.currentTimeMillis());
		if (map == null){
			map = new ConcurrentHashMap<>();
			orderQueue = new ConcurrentLinkedQueue<>();			
			timePriorityOrderMap = new ConcurrentSkipListMap<>(new LimitOrderComparator());	
			timePriorityOrderMap.put(limitOrder,orderQueue);
			map.put(side,timePriorityOrderMap);
			orderBook.put(symbol, map);
			
		}else{
			timePriorityOrderMap = map.get(side);
			if(timePriorityOrderMap == null){
				timePriorityOrderMap = new ConcurrentSkipListMap<>(new LimitOrderComparator());	
			}
			orderQueue = timePriorityOrderMap.get(limitOrder);
			if(orderQueue == null){
				orderQueue = new ConcurrentLinkedQueue<>();	
			}
		}
		orderQueue.add(order);		
		timePriorityOrderMap.put(limitOrder, orderQueue);
		map.put(side, timePriorityOrderMap );	
		
	}

	@Override
	public boolean cancelOrder(final OrderCancelImpl order) {
		
		return false;
	}

	@Override
	public boolean executeOrder(final NewOrReplaceOrderImpl order) {
		final TradableInstrument symbol = order.getSymbol();
		final OrderSide side = order.getSide();
		ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>> sideStack = orderBook.get(symbol);
		if(sideStack == null){
			return false;
		}
		final OrderSide oppSide = FIXUtil.getSideComplement(side);
		final ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> priceMap = sideStack.get(oppSide);
		if(priceMap == null){
			return false;
		}
		final Money price = new Money (order.getPrice(),order.getCurrency());
		//TODO: this silly logic need to be removed, price granuality can be associated to a book. 
		final LimitOrder limitOrder = new LimitOrder(order.getPrice(), new BigDecimal(.01),System.currentTimeMillis());
		if(side == OrderSide.BUY){
			/** Its a buy order get tail of this map. */			
			final ConcurrentNavigableMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> matchedPriceMap = priceMap.headMap(limitOrder, true);
			/** give best price to this order, which means askMin (south of price stack) */
			final NavigableSet<LimitOrder> keySet = matchedPriceMap.descendingKeySet();
			for(final LimitOrder lOrder : keySet){
				/**  */
				final ConcurrentLinkedQueue<NewOrReplaceOrderImpl> orderQueue = matchedPriceMap.get(lOrder);
				Iterator<NewOrReplaceOrderImpl> iterator = orderQueue.iterator();
				/** Iterate over order.*/
				final BigDecimal qopen = order.getQuantity();
				while(iterator.hasNext() || BigDecimal.ZERO.equals(order.getLeavesQty())){
					NewOrReplaceOrderImpl bookOrder = iterator.next();
					// TODO: check for all or nothing 
					/** get qopen */
					final BigDecimal leavesQuantity = bookOrder.getLeavesQty();
					if(leavesQuantity.compareTo(qopen) >= 0){
						/** this book order is going to give us the complete liquidity  */
						bookOrder.setLeavesQty(leavesQuantity.subtract(qopen));
						//bookOrder.setLastShares(quantity); // TODO: use this for execution report.
						bookOrder.setCumQty(bookOrder.getQuantity().subtract(qopen));
						
						// TODO: remove above order from book, if fully filled.
						
						order.setLeavesQty(BigDecimal.ZERO);
						order.setCumQty(order.getQuantity().subtract(order.getLeavesQty()));
						//order.setLastShares(quantity);
						
						// TODO : (a) Publish execution report for both. set execution price on execution report. 
						// for this BUY order execution price will be SELL Limit Price and for SELL limit order my execution
						// price will be BUY Limit Order.
						updateListeners(symbol.toString());						
						break;
					}else{
						bookOrder.setLeavesQty(BigDecimal.ZERO);						
						bookOrder.setCumQty(bookOrder.getQuantity().subtract(bookOrder.getLeavesQty()));	
						order.setLastShares(leavesQuantity);// qty bought/sold on this fill.
						order.setLeavesQty(order.getLeavesQty().subtract(leavesQuantity));
						updateListeners(symbol.toString());
					}								
				}				
			}		
		}/** ~ end of BUY order */
		else{/** handle SELL order.*/
			final ConcurrentNavigableMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> matchedPriceMap = priceMap.tailMap(limitOrder, true);
			/** give best price to this sell order, which means askMin (North of bid price stack) */
			final NavigableSet<LimitOrder> keySet = matchedPriceMap.keySet();
			for(final LimitOrder lOrder : keySet){
				/**  */
				final ConcurrentLinkedQueue<NewOrReplaceOrderImpl> orderQueue = matchedPriceMap.get(lOrder);
				Iterator<NewOrReplaceOrderImpl> iterator = orderQueue.iterator();
				/** Iterate over order.*/
				final BigDecimal qopen = order.getLeavesQty();
				while(iterator.hasNext() || BigDecimal.ZERO.equals(qopen)){
					NewOrReplaceOrderImpl bookOrder = iterator.next();
					// TODO: check for all or nothing 
					/** get qopen */
					final BigDecimal leavesQuantity = bookOrder.getLeavesQty();
					if(leavesQuantity.compareTo(qopen) >= 0){
						/** this book order is going to give us the complete liquidity  */
						bookOrder.setLeavesQty(leavesQuantity.subtract(qopen));
						//bookOrder.setLastShares(quantity); // TODO: use this for execution report.
						bookOrder.setCumQty(bookOrder.getQuantity().subtract(qopen));
						
						// TODO: remove above order from book, if fully filled.
						
						order.setLeavesQty(BigDecimal.ZERO);
						order.setCumQty(order.getQuantity().subtract(order.getLeavesQty()));
						updateListeners(symbol.toString());
						//order.setLastShares(quantity);
						
						// TODO : (a) Publish execution report for both. set execution price on execution report. 
						// for this BUY order execution price will be SELL Limit Price and for SELL limit order my execution
						// price will be BUY Limit Order.
						
						break;
					}else{
						bookOrder.setLeavesQty(BigDecimal.ZERO);					
						bookOrder.setCumQty(bookOrder.getQuantity().subtract(bookOrder.getLeavesQty()));	//qdone
						order.setLastShares(leavesQuantity);// qty bought/sold on this fill.
						order.setLeavesQty(order.getLeavesQty().subtract(leavesQuantity));//qopen
						updateListeners(symbol.toString());
					}								
				}				
			}			
		}		
		return false;
	}

	@Override
	public boolean executeMarketOrder(final NewOrReplaceOrderImpl order) {
		
		return false;
	}
	
	public void viewOrderBook(){
		//System.out.println(orderBook);
		Set<TradableInstrument> set = orderBook.keySet();
		for (TradableInstrument symbol : set){
			System.out.println(symbol.toString());
			System.out.println("============================================" );
			ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>> sideStack = orderBook.get(symbol);
			Set<OrderSide> sideSet = sideStack.keySet();
			for(final OrderSide side : sideSet){
				System.out.println(side.toString());
				System.out.println("---------------------------------------" );
				ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> map = sideStack.get(side);
				Set<LimitOrder> limitSet = map.keySet();
				for(final LimitOrder limitOrder : limitSet){
					ConcurrentLinkedQueue<NewOrReplaceOrderImpl> orderQueue = map.get(limitOrder);
					Iterator<NewOrReplaceOrderImpl> it = orderQueue.iterator();
					while(it.hasNext()){
						NewOrReplaceOrderImpl order  = it.next();
						System.out.println("OrderID:"+ order.getOrderID() + " " +order.getQuantity() + "@" +order.getPrice() + " LeavesQty=" + order.getLeavesQty() + " CumQty=" + order.getCumQty());
					}
					
				}
			}
			
			
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<TradableInstrument> getSymbolTable(){		
		return orderBook.keySet();// TODO: return defensive copy.		and add listener too.
	}
	
	/**
	 * 
	 * @param isBuyStack
	 */
	public List<String> getOrderSideStack(final String symbol, final boolean isBuyStack) {
		final List<String> pendingOrderList = new ArrayList();
		final TradableInstrument sym = new TradableInstrument(symbol,SecurityType.CommonStock);
		final ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>> sideStack = orderBook.get(sym);		
	    final OrderSide orderSide =  isBuyStack ? OrderSide.BUY : OrderSide.SELL;
	    final ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> priceMap = sideStack.get(orderSide);
	    final NavigableSet<LimitOrder> keySet = priceMap.keySet();
		for(final LimitOrder lOrder : keySet){
			/**  */
			final ConcurrentLinkedQueue<NewOrReplaceOrderImpl> orderQueue = priceMap.get(lOrder);
			Iterator<NewOrReplaceOrderImpl> iterator = orderQueue.iterator();
			BigDecimal pendingOrderSize = BigDecimal.ZERO;
			while(iterator.hasNext()){
				NewOrReplaceOrderImpl bookOrder = iterator.next();
				pendingOrderSize = pendingOrderSize.add(bookOrder.getLeavesQty());
			}
			pendingOrderList.add(pendingOrderSize.toPlainString());
			
		}
		return pendingOrderList;

	}
	
	/**
	 * 
	 * @param isBuyStack
	 */
	public List<List<String>> getPriceLadder(final String symbol) {
		final List<List<String>> pendingOrderList = new ArrayList();
		final TradableInstrument sym = new TradableInstrument(symbol,SecurityType.CommonStock);
		final ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>> sideStack = orderBook.get(sym);		
	    //final OrderSide orderSide =  isBuyStack ? OrderSide.BUY : OrderSide.SELL;
	    final ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> buyStack = sideStack.get(OrderSide.BUY);
	    final ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> sellStack = sideStack.get(OrderSide.SELL);
	    final NavigableSet<LimitOrder> buyOrders  = buyStack.keySet();
	    final NavigableSet<LimitOrder> sellOrders = sellStack.keySet();
	    final NavigableSet<LimitOrder> allOrders  = new TreeSet<>(new LimitOrderComparator());	
	    allOrders.addAll(buyOrders);
	    allOrders.addAll(sellOrders);
		for(final LimitOrder order : allOrders){
			/**  */
			final ConcurrentLinkedQueue<NewOrReplaceOrderImpl> sellOrderQueue = sellStack.get(order);
			final ConcurrentLinkedQueue<NewOrReplaceOrderImpl> buyOrderQueue = buyStack.get(order);
			BigDecimal pendingSellOrderSize = BigDecimal.ZERO;
			BigDecimal pendingBuyOrderSize 	= BigDecimal.ZERO;
			List list = new ArrayList();
			if(sellOrderQueue != null){
				Iterator<NewOrReplaceOrderImpl> iterator = sellOrderQueue.iterator();
				while(iterator.hasNext()){
					NewOrReplaceOrderImpl bookOrder = iterator.next();
					pendingSellOrderSize = pendingSellOrderSize.add(bookOrder.getLeavesQty());
				}
				
			}
			list.add(pendingSellOrderSize);
			list.add(order.getPrice().toString());
			
			if(buyOrderQueue != null){
				Iterator<NewOrReplaceOrderImpl> iterator = buyOrderQueue.iterator();
				while(iterator.hasNext()){
					NewOrReplaceOrderImpl bookOrder = iterator.next();
					pendingBuyOrderSize = pendingBuyOrderSize.add(bookOrder.getLeavesQty());
				}				
			}
			list.add(pendingBuyOrderSize);
			pendingOrderList.add(list);
		}
		System.out.println(pendingOrderList);
		return pendingOrderList;

	}
	
	/**
	 * 
	 *//*
	public List<String> getPriceLadder(final String symbol){
		final TradableInstrument sym = new TradableInstrument(symbol,SecurityType.CommonStock);
		ArrayList<String> list = new ArrayList<>();
		ConcurrentHashMap<OrderSide, ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>>> sideStack = orderBook.get(sym);
		Set<OrderSide> sideSet = sideStack.keySet();
		for(final OrderSide side : sideSet){				
			ConcurrentSkipListMap<LimitOrder, ConcurrentLinkedQueue<NewOrReplaceOrderImpl>> map = sideStack.get(side);
			Set<LimitOrder> limitSet = map.descendingKeySet();
			//list.addAll(limitSet);
			for(final LimitOrder limitOrder : limitSet){
				list.add(limitOrder.getPrice().toString());
			}
		}
		//Collections.sort(list, new LimitOrderComparator<LimitOrder>());
		return list;
	}*/
	
	public void addPriceLadderListener(final PriceLadderListener listener){
		priceLadderListenerList.add(listener);
	}
	
	/**
	 * 
	 */
	private void updateListeners(final String symbol){
		List<List<String>> priceLadderModel = getPriceLadder(symbol);
		for(final PriceLadderListener listener: priceLadderListenerList){
			listener.update(priceLadderModel);
		}
	}
	
}

