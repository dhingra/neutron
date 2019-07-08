package com.rd.neutron.quickfix.order;



import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;


/**
 * Base class for orders. 
 * 
 * @author Rohit Dhingra
 * @version 
 * @since 1.0.0
 */


public class OrderBaseImpl implements OrderBase {
	
	private OrderID mOrderID;
    private OrderSide mSide;
    private BigDecimal mQuantity;
    private BigDecimal mLeavesQty;/** qopen */
    private BigDecimal mLastShares;
    private BigDecimal mCumQty; /** qdone */
    
    private Map<String,String> mCustomFields;    

	private BrokerID mBrokerID;
    private String mAccount;
    private TradableInstrument mSymbol;
    private static final long serialVersionUID = 1L;
    
    
    public OrderBaseImpl(){
    	mLeavesQty = BigDecimal.ZERO;
    	mCumQty = BigDecimal.ZERO;
    }
    
    @Override
    public OrderID getOrderID() {
        return mOrderID;
    }

    @Override
    public void setOrderID(OrderID inOrderID) {
        mOrderID = inOrderID;
    }

    @Override
    public OrderSide getSide() {
        return mSide;
    }

    @Override
    public void setSide(OrderSide inSide) {
        mSide = inSide;
    }

    @Override
    public TradableInstrument getSymbol() {
        return mSymbol;
    }

    @Override
    public void setSymbol(TradableInstrument inSymbol) {
        mSymbol = inSymbol;
    }

    @Override
    public BigDecimal getQuantity() {
        return mQuantity;
    }

    @Override
    public void setQuantity(BigDecimal inQuantity) {
        mQuantity = inQuantity;
    }

    @Override
    public Map<String, String> getCustomFields() {
        return mCustomFields == null
                ? null
                : new HashMap<String,String>(mCustomFields);
    }

    @Override
    public void setCustomFields(Map<String, String> inCustomFields) {
        mCustomFields = inCustomFields == null
                ? null
                : new HashMap<String,String>(inCustomFields);
    }

    @Override
    public SecurityType getSecurityType() {
        return mSymbol == null
                ? null
                : mSymbol.getSecurityType(); 
    }

    @Override
    public BrokerID getBrokerID() {
        return mBrokerID;
    }

    @Override
    public void setBrokerID(BrokerID inBrokerID) {
        mBrokerID = inBrokerID;
    }

    @Override
    public String getAccount() {
        return mAccount;
    }

    @Override
    public void setAccount(String inAccount) {
        mAccount = inAccount;
    }

	@Override
	public BigDecimal getLeavesQty() {
		return mLeavesQty;
	}

	@Override
	public void setLeavesQty(BigDecimal inLeavesQty) {
		mLeavesQty = inLeavesQty;		
	}

	@Override
	public BigDecimal getLastShares() {		
		return mLastShares;
	}

	@Override
	public void setLastShares(BigDecimal inLastShares) {
		mLastShares = inLastShares;
		
	}

	@Override
	public BigDecimal getCumQty() {		
		return mCumQty;
	}

	@Override
	public void setCumQty(BigDecimal inCumQty) {
		mLastShares = inCumQty;		
	}
  
}
