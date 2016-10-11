package graphicalUI;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;

public class filterDisplayComboBoxModel extends AbstractListModel implements MutableComboBoxModel  {
	//private String[] columnNames = {"name", "related object"};
    private ArrayList<Object[]> data;
    private JComboBox comboBox;
    Object[] selectedItem = null;
	
    public filterDisplayComboBoxModel(ArrayList<Object[]> newData, JComboBox self) {
    	data = newData;
    	comboBox = self;
    }
    
    
    public void refreshData(ArrayList<Object[]> newData) {
    	for (Object[] item: newData) {
    		boolean flag = false;
    		for (Object[] item2: data) {
    			if (!item[0].equals(item2[0])) {
    				flag = true;
    			}
    		}
    		if (flag == true) {
    			comboBox.addItem(item);
    		}
    		
    	}
    }
    
    
    public Object getSelectedObject() {
    	if (selectedItem != null) {
			return selectedItem[1];
		} else {
			return null;
		}
    }
    
	@Override
	public Object getElementAt(int index) {
		return data.get(index)[0];
	}

	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public Object getSelectedItem() {
		if (selectedItem != null) {
			return selectedItem[0];
		} else {
			return null;
		}
	}

	@Override
	public void setSelectedItem(Object theObject) {
		for (Object[] item: data) {
			if ((theObject).equals(item[0])) {
				selectedItem = item;
			}
		}
	}


	@Override
	public void addElement(Object item) {
		data.add((Object[])item);
		
	}


	@Override
	public void insertElementAt(Object arg0, int arg1) {
		
	}


	@Override
	public void removeElement(Object theObject) {
		for (Object[] item: data) {
			if ((theObject).equals(item[0])) {
				data.remove(item);
			}
		}
	}


	@Override
	public void removeElementAt(int arg0) {
		
	}

		
}
