package com.rd.neutron.quickfix.order;



/* $License$ */
/**
 * A Suggestion for a single order. Instances of this type
 * can be created via {@link Factory#createOrderSingleSuggestion()}
 *
 * @author anshul@marketcetera.com
 * @version $Id: OrderSingleSuggestion.java 10229 2008-12-09 21:48:48Z klim $
 * @since 1.0.0
 */

public interface OrderSingleSuggestion extends Suggestion {
    /**
     * Returns the order suggested by this suggestion.
     *
     * @return the order suggested by this suggestion.
     */
    public OrderSingle getOrder();

    /**
     * Sets the order suggested by this suggestion.
     *
     * @param inOrder the suggested order.
     */
    public void setOrder(OrderSingle inOrder);
}
