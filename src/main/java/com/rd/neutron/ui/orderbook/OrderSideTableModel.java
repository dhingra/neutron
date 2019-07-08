package com.rd.neutron.ui.orderbook;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.rd.neutron.core.orderbook.LimitOrderBook;
import com.rd.neutron.core.orderbook.PriceLadderListener;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public class OrderSideTableModel extends AbstractTableModel implements PriceLadderListener{

	//private SortedSet  priceLadderSink = new TreeSet<>();
	private List<String>  priceLadderSink = new ArrayList<>();
	
	
	public OrderSideTableModel(){
		LimitOrderBook.getInstance().addPriceLadderListener(this);
	}
	@Override
	public int getRowCount() {		
		return priceLadderSink.size();
	}

	@Override
	public int getColumnCount() {		
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {		
		return priceLadderSink.get(rowIndex);
	}
	
	@Override
	public void update(List<String>  priceLadderModel) {
		priceLadderSink = priceLadderModel;
        fireTableDataChanged();
    }

}

