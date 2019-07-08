package com.rd.neutron.ui.orderbook;

import javax.swing.table.AbstractTableModel;

import com.rd.neutron.core.orderbook.LimitOrderBook;
import com.rd.neutron.core.orderbook.PriceLadderListener;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author  Rohit Dhingra 
 * @see     
 * @since   1.0.0
 */
public class PriceLadderTableModel extends AbstractTableModel implements PriceLadderListener{

	//private SortedSet  priceLadderSink = new TreeSet<>();
	private List<List<String>>  priceLadderSink = new ArrayList<>();
	
	
	public PriceLadderTableModel(){
		LimitOrderBook.getInstance().addPriceLadderListener(this);
	}
	@Override
	public int getRowCount() {		
		return priceLadderSink.size();
	}

	@Override
	public int getColumnCount() {		
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {		
		List<String> row = priceLadderSink.get(rowIndex);
		return row.get(columnIndex);
	}
	
	@Override
	public void update(List<List<String>>  priceLadderModel) {
		priceLadderSink = priceLadderModel;
        fireTableDataChanged();
    }

}

