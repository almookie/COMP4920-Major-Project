package graphicalUI;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class filterDisplayComboBoxModel extends AbstractListModel implements ComboBoxModel  {
	//private String[] columnNames = {"name", "related object"};
    private ArrayList<Object[]> data;
    
    Object[] selectedItem = null;
	
    public filterDisplayComboBoxModel(ArrayList<Object[]> newData) {
    	data = newData;
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
			if (((Integer) theObject).equals((Integer) item[0])) {
				selectedItem = item;
			}
		}
	}

		
}
