package graphicalUI;

import javax.swing.table.AbstractTableModel;

public class filterDisplayTableModel extends AbstractTableModel {
	private String[] columnNames = {"results"};
    private Object[][] data;
    
    
    filterDisplayTableModel(Object[][] newData) {
    	data = newData;
    }
    
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    
    public int getRowCount() {
        return data.length;
    }
    
    
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    
    //data not editable in GUI
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
