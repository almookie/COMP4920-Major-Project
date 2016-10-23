package markingPanelComponents;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

class assementTableModel extends DefaultTableModel {
      /**
	 * source from http://www.java2s.com/
	 * by leon fo r cs4920
	 */
	private static final long serialVersionUID = 1L;

	public assementTableModel(Object rowData[][], Object columnNames[]) {
        super(rowData, columnNames);
      }


      public boolean isCellEditable(int row, int col) {
    	  if(col==1||col==0){
    		  return false;
    	  }
        return true;
      }
    }