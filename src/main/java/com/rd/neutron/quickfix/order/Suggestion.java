package com.rd.neutron.quickfix.order;



import java.math.BigDecimal;
import java.io.Serializable;

/* $License$ */
/**
 * Identifies data needed for an order suggestion.
 *
 * @author anshul@marketcetera.com
 * @version $Id: Suggestion.java 10229 2008-12-09 21:48:48Z klim $
 * @since 1.0.0
 */

public interface Suggestion extends Serializable {
    /**
     * The identifier for this suggestion.
     *
     * @return the identifier for this suggestion.
     */
    public String getIdentifier();

    /**
     * Sets the identifier for this suggestion.
     *
     * @param inIdentifier the identifier value.
     */
    public void  setIdentifier(String inIdentifier);

    /**
     * The score for this suggestion. Higher the value, the stronger
     * is the recommendation for this order. 
     *
     * @return the score for this suggestion.
     */
    public BigDecimal getScore();

    /**
     * The score for this suggestion.
     *
     * @param inScore the score value.
     */
    public void setScore(BigDecimal inScore);
}
