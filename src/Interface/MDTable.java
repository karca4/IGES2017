package Interface;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class MDTable extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int column;
	private int row;
	private  static int COLUMN=5;
	private  static int ROW=5;
	private Object[] columnName;
	private Object[][] data;
	
	public MDTable(int row,int column) {
		this.column=column;
		this.row=row;
		this.columnName=new Object[column];
		this.data=new Object[row][column];
	}
	
	public MDTable() {
		this.column=COLUMN;
		this.row=ROW;
		this.columnName=new Object[column];
		this.data=new Object[row][column];
	}
	
	public void setColumnName(Object ob,int i){
		columnName[i]=ob;
	}
	
	
	public Object[] getColumnName() {
		return columnName;
	}

	public void setColumnName(Object[] columnName) {
		this.columnName = columnName;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return row;
	}

	@Override
	public int getColumnCount() {
		return column;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
//	public Class getColumnClass(int c) {
//        return getValueAt(0, c).getClass();
//    }
	
	 public void setValueAt(Object value, int row, int col) {
	        data[row][col] = value;
	        fireTableCellUpdated(row, col);
	    }
	
//	 @Override
//	public void addTableModelListener(TableModelListener l) {
//		super.addTableModelListener(l);
//	}
	 
	 @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return super.isCellEditable(rowIndex, columnIndex);
		 return true;
	}
	 
	 
	 
}
