package com.rd.neutron.core.engine.matchingengine;

import java.util.concurrent.ConcurrentHashMap;

import com.rd.neutron.core.orderbook.LimitOrderBook;
import com.rd.neutron.quickfix.order.NewOrReplaceOrder;
import com.rd.neutron.quickfix.order.NewOrReplaceOrderImpl;
import com.rd.neutron.quickfix.order.Order;
import com.rd.neutron.quickfix.order.OrderBaseImpl;
import com.rd.neutron.quickfix.order.OrderSide;
import com.rd.neutron.quickfix.order.TradableInstrument;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public class MatchingEngine {
	
	private LimitOrderBook limitOrderBook;
	
	private static enum Singleton {
		INSTANCE;
		private static final MatchingEngine singleton = new MatchingEngine();

		public MatchingEngine getSingleton() {
			return singleton;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static MatchingEngine getInstance() {
		return MatchingEngine.Singleton.INSTANCE.getSingleton();
	}

	/**
	 * private constructor to enforce singleton.
	 */
	private MatchingEngine() {
		limitOrderBook = LimitOrderBook.getInstance();
		
	}
	
	/**
	 * 
	 * @param orderRequest
	 */
	private void matchOrder(final NewOrReplaceOrderImpl orderRequest) {
		
		final TradableInstrument symbol = orderRequest.getSymbol();
		final OrderSide side = orderRequest.getSide();
		limitOrderBook.addOrder(orderRequest); 
		
		//limitOrderBook
        
       /* if (isAllOrNoneRestricted(buyOrder, sellOrder)) {
            logger.debug("No match: AllOrNone restriction");
            return new MatchResult(false, NoMatchReason.allOrNone);
        }

        MatchResult matchResult =
            new MatchResult(false, NoMatchReason.priceMismatch);

        if (buyOrder.getType() == OrderType.Market) {
            if (sellOrder.getType() == OrderType.Market) {
                executeOrders(buyOrder, sellOrder, marketPrice.getPrice());
            }
            else {  // sell order is limit order
                executeOrders(buyOrder, sellOrder, sellOrder.getLimitPrice());
                marketPrice.change(sellOrder.getLimitPrice());
            }
            matchResult = new MatchResult(true, null);
        }
        else {  // buy order is a limit order
            if (sellOrder.getType() == OrderType.Market) {
                executeOrders(buyOrder, sellOrder, buyOrder.getLimitPrice());
                marketPrice.change(buyOrder.getLimitPrice());
                matchResult = new MatchResult(true, null);
            }
            else {  // sell order is limit order
                Money buyPrice = buyOrder.getLimitPrice();
                Money sellPrice = sellOrder.getLimitPrice();
                if (buyPrice.compareTo(sellPrice) >= 0) {
                    Money executionPrice =
                        buyPrice.plus(sellPrice).div(2, Constants.PRICE_SCALE);
                    executeOrders(buyOrder, sellOrder, executionPrice);
                    marketPrice.change(executionPrice);
                    matchResult = new MatchResult(true, null);
                }
            }
        }

        logger.debug("After match:\n{}\n{}", buyOrder, sellOrder);
        
        return matchResult;*/
    }
	
	

}

