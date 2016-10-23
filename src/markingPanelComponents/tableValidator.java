package markingPanelComponents;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

class tableValidator extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
	public Component getTableCellRendererComponent(JTable table,
            Object mark,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column){
    	
        Component currentCell = super.getTableCellRendererComponent(
        		table, mark, isSelected, hasFocus,row, column);

        if(!mark.toString().matches("-?\\d+(\\.\\d+)?")){
        	((JComponent) currentCell).setBorder(new MatteBorder(1, 1, 1, 1, Color.RED) );
        } else {

        }

        return currentCell;
    }
}