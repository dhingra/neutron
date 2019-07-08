package com.rd.neutron.quickfix.order;

import com.rd.neutron.pattern.Cacheable;

/**
 *
 * @author  Rohit Dhingra     
 * @since   1.0.0
 */
public class TradableInstrument implements Cacheable{

	private static final long serialVersionUID = 1L;
	private final SecurityType securityType;
	private final String fullSymbol;


	/**
	 * Creates a new instance.
	 *
	 * @param fullSymbol the symbol value.
	 */
	public TradableInstrument(final String fullSymbol){
		this(fullSymbol, null);
	}

	/**
	 * Creates a new instance.
	 *
	 * @param fullSymbol the symbol value.
	 * @param securityType the security type.
	 */
	public TradableInstrument(final String fullSymbol, final SecurityType securityType) {
		if (fullSymbol == null){
			throw new IllegalArgumentException("Null Symbol");
		}
		this.fullSymbol = fullSymbol;
		this.securityType = securityType;
	}



	/**
	 * Returns the full symbol value.
	 * 
	 * @return the full symbol value.
	 */
	public String getFullSymbol() {
		return fullSymbol;
	}

	/**
	 * Returns the Security Type for this Symbol.
	 *
	 * @return the security type of this symbol.
	 */
	public SecurityType getSecurityType() {
		return securityType;
	}

	@Override
	public String toString(){
		return getFullSymbol();
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof TradableInstrument) {
			TradableInstrument aSymbol = (TradableInstrument) obj;
			return getFullSymbol().equals(aSymbol.getFullSymbol()) &&
					getSecurityType().equals( aSymbol.getSecurityType());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getFullSymbol() == null? 0: getFullSymbol().hashCode();
	}
}

