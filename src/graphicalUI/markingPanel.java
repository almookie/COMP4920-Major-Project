package graphicalUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import main.*;
import main.Class;

public class markingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel current;

	public markingPanel(final Markbook mB) {
		
		this.setLayout(new BorderLayout());
		
		final JPanel searchPanel = new JPanel();
		JPanel viewPanel = new JPanel();
		//lhs stuff
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		//title related stuff
		JLabel filterTitle = new JLabel("Search Options!");
		filterTitle.setPreferredSize(new Dimension(50,60));
		filterTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		searchPanel.add(filterTitle, BorderLayout.NORTH);
		
		JPanel lhs = new JPanel(new BorderLayout());
		lhs.add(new JButton());
		searchPanel.add(lhs,BorderLayout.WEST);
		
		//panel with the search fields
		JPanel searchFieldsPanels = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //gbc for search fields
        c.anchor = GridBagConstraints.WEST;
	    c.gridx=1;
	    c.weightx=1;
	    c.insets = new Insets(0,8,0,5);
	    c.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraints c1 = new GridBagConstraints();
        //gbc for titles fields
        c1.anchor = GridBagConstraints.WEST;
	    c1.gridx=1;
	    c1.weightx=1;
	    c1.insets = new Insets(0,8,0,5);
	    c1.fill = GridBagConstraints.HORIZONTAL;
	    final JTextField jtfFilter = new JTextField();
	    final JTextField jtfFilter1 = new JTextField();
	    final JTextField jtfFilter2 = new JTextField();
	    final JTextField jtfFilter3 = new JTextField();

	    
	    searchFieldsPanels.add(new JLabel("classname?"),c1);
	    searchFieldsPanels.add(jtfFilter,c);
	    searchFieldsPanels.add(new JLabel("firstname?"),c1);
	    searchFieldsPanels.add(jtfFilter1,c);
	    searchFieldsPanels.add(new JLabel("Option3?"),c1);
	    searchFieldsPanels.add(jtfFilter2,c);
	    searchFieldsPanels.add(new JLabel("Option4?"),c1);
	    searchFieldsPanels.add(jtfFilter3,c);
	    //final jbutton to searchs
	    c1.fill = GridBagConstraints.NONE;
	    c1.insets = new Insets(10,15,0,5);
	    JButton getResults = new JButton("Get Results");
	    searchFieldsPanels.add(getResults,c1);
	    searchFieldsPanels.setPreferredSize(new Dimension(300,400));
	    
	    lhs.add(searchFieldsPanels, BorderLayout.NORTH);
		searchPanel.add(lhs);
		
		viewPanel.setLayout(new BorderLayout());
		viewPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));


		JLabel searchTitle = new JLabel("Search Results To Mark On!!");
		searchTitle.setPreferredSize(new Dimension(50,60));
		searchTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		searchTitle.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		viewPanel.add(searchTitle, BorderLayout.NORTH);
		

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		JPanel labelsPanel = new JPanel(new GridBagLayout());
		String[] columnNames = new String[2];

    	final ArrayList<Class> classes = mB.getClasses();

		String[][] data1 = new String[1][3];
//		for(Class c11 : classes){ 
//			data1 = new String[1][3];
//			columnNames = new String[2];
//			columnNames[0] = "fistName";
//			columnNames[1] = "lastName";
//					
//
//			System.out.println( c11.getGrade().getYear(mB.getCurrentYear()) + c11.getSubject().getName()+c11.getGrade().getYear(mB.getCurrentYear()));
//				for(Assessment a: c11.getAssessments()){
//					columnNames = appendArray(columnNames, a.getName() );
//					System.out.println( a.getName());
//					
//					for(Student s :c11.getStudents() ){
//						System.out.print( "    name =" + s.getGivenName()+ " " + s.getSurname() + " ");
//						System.out.println("     mark =>" + a.getMark(s));
//						String temp[][] = new String[1][3];
//						temp[0][0] =s.getGivenName();
//						temp[0][1] =s.getSurname();
//						temp[0][2] = Double.toString(a.getMark(s));
//						
//						data1 = concat(data1,temp);
//					}
//				}
//			}
		
		for(int i=0; i<classes.size() ; i++){
 			data1 = new String[1][3];
 			columnNames = new String[2];
 			columnNames[0] = "fistName";
 			columnNames[1] = "lastName";
 					
 			Class c11 = classes.get(i);
 			String cName = c11.getGrade().getYear(mB.getCurrentYear()) + c11.getSubject().getName()+c11.getClassNumber();
 			System.out.println(cName);
 				for(Assessment a: c11.getAssessments()){
 					columnNames = appendArray(columnNames, a.getName() );
 					System.out.println( a.getName());
 					
 					for(Student s :c11.getStudents() ){
 						System.out.print( "    name =" + s.getGivenName()+ " " + s.getSurname() + " ");
 						System.out.println("     mark =>" + a.getMark(s));
 						String temp[][] = new String[1][3];
 						temp[0][0] =s.getGivenName();
 						temp[0][1] =s.getSurname();
 						temp[0][2] = Double.toString(a.getMark(s));
 						
 						data1 = concat(data1,temp);
 					}
 				}
         	//SHOULD BE ABLE TO ADD DYNAMICALLY IN A LOOP
     		//needbuttoon for focus
     		JLabel className = new JLabel("Class: " + cName);
     		className.setBorder(new EtchedBorder(EtchedBorder.RAISED));
     		className.setBorder(BorderFactory.createLineBorder(Color.BLACK));

     		
     		final DefaultTableModel model = new DefaultTableModel(data1, columnNames);
     		final JTable table = new JTable( model );
     		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
     		table.setFillsViewportHeight(true);
             JScrollPane scrollPane = new JScrollPane(table);
             model.addTableModelListener(new TableModelListener(){

 				@Override
 				public void tableChanged(TableModelEvent e) {
 					// TODO Auto-generated method stub
 					if(table.isEditing()){
 		            	System.out.println("DEBUG INFO: marking TABLES data edited");
 						String value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
 						System.out.println(value);
 					}
 					
 				}	
             	
             });

             labelsPanel.add(className, gbc);
             labelsPanel.add(scrollPane, gbc);
             
         }
		

	        

        JScrollPane scroll = new JScrollPane(labelsPanel);
		viewPanel.add(scroll, BorderLayout.CENTER);
		
		
		
		//split screen into half
		final JSplitPane overall = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, viewPanel);
		
		//enough space for search items
		overall.setDividerLocation(350);
		overall.setOneTouchExpandable(true);

		
		
		//LOCATION WHERE INPUT IS RECIEVED for Backend
		getResults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("DEBUG INFO: marking menu FILTER results");
            	System.out.println("jtfFilter: "+jtfFilter.getText());
            	System.out.println("jtfFilter1: "+jtfFilter1.getText());
            	System.out.println("jtfFilter2: "+jtfFilter2.getText());
            	System.out.println("jtfFilter3: "+jtfFilter3.getText()); 
        		JPanel viewPanel = new JPanel();
        		
        		
        		viewPanel.setLayout(new BorderLayout());
        		viewPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));


        		JLabel searchTitle = new JLabel("Search Results To Mark On!!");
        		searchTitle.setPreferredSize(new Dimension(50,60));
        		searchTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        		searchTitle.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        		viewPanel.add(searchTitle, BorderLayout.NORTH);
        		

                final GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
        		JPanel labelsPanel = new JPanel(new GridBagLayout());
        		overall.removeAll();
        		String[] columnNames = new String[2];

            	final ArrayList<Class> classes = mB.getClasses();

        		String[][] data1 = new String[1][3];
        		
        		for(int i=0; i<classes.size() ; i++){
         			data1 = new String[1][3];
         			columnNames = new String[2];
         			columnNames[0] = "fistName";
         			columnNames[1] = "lastName";
         					
         			Class c11 = classes.get(i);
         			String cName = c11.getGrade().getYear(mB.getCurrentYear()) + c11.getSubject().getName()+c11.getClassNumber();
         			System.out.println(cName);
         			if(cName.equals(jtfFilter.getText()) || jtfFilter.getText().equals("")){

         				for(Assessment a: c11.getAssessments()){
         					
         					columnNames = appendArray(columnNames, a.getName() );
         					System.out.println( a.getName());
         					
         					for(Student s :c11.getStudents() ){
         						if(jtfFilter1.getText().equals(s.getGivenName()) || jtfFilter1.getText().equals("")){
         						System.out.print( "    name =" + s.getGivenName()+ " " + s.getSurname() + " ");
         						System.out.println("     mark =>" + a.getMark(s));
         						String temp[][] = new String[1][3];
         						temp[0][0] =s.getGivenName();
         						temp[0][1] =s.getSurname();
         						temp[0][2] = Double.toString(a.getMark(s));
         						
         						data1 = concat(data1,temp);
         						}
         					}
         				}
                 	//SHOULD BE ABLE TO ADD DYNAMICALLY IN A LOOP
             		//needbuttoon for focus
             		JLabel className = new JLabel("Class: " + cName);
             		className.setBorder(new EtchedBorder(EtchedBorder.RAISED));
             		className.setBorder(BorderFactory.createLineBorder(Color.BLACK));

             		
             		final DefaultTableModel model = new DefaultTableModel(data1, columnNames);
             		final JTable table = new JTable( model );
             		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
             		table.setFillsViewportHeight(true);
                     JScrollPane scrollPane = new JScrollPane(table);
                     model.addTableModelListener(new TableModelListener(){

         				@Override
         				public void tableChanged(TableModelEvent e) {
         					// TODO Auto-generated method stub
         					if(table.isEditing()){
         		            	System.out.println("DEBUG INFO: marking TABLES data edited");
         						String value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
         						System.out.println(value);
         					}
         					
         				}	
                     	
                     });

                     labelsPanel.add(className, gbc);
                     labelsPanel.add(scrollPane, gbc);
                     
                 }
        		

        	        
        		}
                JScrollPane scroll = new JScrollPane(labelsPanel);

        		viewPanel.add(scroll, BorderLayout.CENTER);
        	
        		viewPanel.validate();
        		viewPanel.repaint();
            	
        		overall.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, viewPanel));
        		
        		//enough space for search items
        		overall.setDividerLocation(350);
        		overall.setOneTouchExpandable(true);

            	
                //System.out.println("in5");
            
            }
        });
		
		
		
		
		this.add(overall);
	}
	    
    public JPanel getPanel(){
    	return current;
    }
    
    private String[] appendArray(String[] array, String x){
    	String[] result = new String[array.length + 1];

        for(int i = 0; i < array.length; i++)
            result[i] = array[i];

        result[result.length - 1] = x;

        return result;
    }
    public String[][] concat(String[][] a, String[][] b) {
    	if(a[0][0]==null){
    		return b;
    	}
        String[][] result = new String[a.length + b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

}




