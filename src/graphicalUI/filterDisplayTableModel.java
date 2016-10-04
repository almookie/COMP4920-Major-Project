package graphicalUI;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class filterDisplayTableModel extends AbstractTableModel {
	private String[] columnNames = {"results", "related object"};
    private ArrayList<Object[]> data;
    
    
    filterDisplayTableModel(ArrayList<Object[]> newData) {
    	data = newData;
    }
    
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    
    public int getRowCount() {
        return data.size();
    }
    
    
    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }
    
    
    public void addRow(Object[] newData) {
    	data.add(newData);
    }
    
    //data not editable in GUI
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
