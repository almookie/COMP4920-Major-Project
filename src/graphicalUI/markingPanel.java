package graphicalUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JFrame;
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
import markingPanelComponents.ClassDisplay;

public class markingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel current;
	private JPanel labelsPanel;
	private JPanel viewPanel;
	private Markbook mB;
	private JSplitPane overall;
	private JPanel searchPanel;
	
	public markingPanel(final Markbook mB) {
		 this.mB=mB;
		 //sample buttons for menu using box layout vertical span

	  	 
	  	 //cardlayout for the main panel two switch between possible classes of panels we create
	     final CardLayout cardLayout = new CardLayout();
	  	 final JPanel main = new JPanel(cardLayout);
	  	 
	  	 //example panels
	       
	       
	       //filler content remove later
	  	JPanel newAssesment = new JPanel();

	 
	  	
		this.setLayout(new BorderLayout());
		
		searchPanel = new JPanel();
		viewPanel = new JPanel();
		
		//lhs stuff
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		//title related stuff
		JLabel filterTitle = new JLabel("Search Options!");
		filterTitle.setPreferredSize(new Dimension(50,60));
		filterTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		searchPanel.add(filterTitle, BorderLayout.NORTH);
		
		JPanel lhs = new JPanel(new BorderLayout());
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

	    
	    searchFieldsPanels.add(new JLabel("Search for a Class"),c1);
	    searchFieldsPanels.add(jtfFilter,c);
	    searchFieldsPanels.add(new JLabel("Search for an Assesment"),c1);
	    searchFieldsPanels.add(jtfFilter1,c);
	    searchFieldsPanels.add(new JLabel("Search for a name (first, last or  first last)"),c1);
	    searchFieldsPanels.add(jtfFilter2,c);

	    //final jbutton to searchs
	    c1.fill = GridBagConstraints.NONE;
	    c1.insets = new Insets(10,15,0,5);
	    JButton getResults = new JButton("Get Results");
	    searchFieldsPanels.add(getResults,c1);
	    searchFieldsPanels.setPreferredSize(new Dimension(300,400));
	    
	    lhs.add(searchFieldsPanels, BorderLayout.NORTH);
		searchPanel.add(lhs);
		
		
		refreshClasses(null,null,null,mB.getClasses());

		
		//split screen into half
		overall = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, viewPanel);
		
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
            	
            			//class // assesement //first name //last name//classes
            	refreshClasses(jtfFilter.getText(),jtfFilter1.getText(),jtfFilter2.getText(), mB.getClasses());
            	
            	
      	       	main.remove(overall);

            	overall = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, viewPanel);
            	
            	main.add(overall, "overall");
                cardLayout.show(main, "overall");


            }
        });
		
		
		

	       main.add(overall, "overall");
	       main.add(newAssesment, "newAssesment");

	  
	
			this.add(main);

	
	
	
	}
	
	
	public void refreshClasses(String classRegex,String assesmentRegex,String nameRegex,ArrayList<Class> classes){
		
		viewPanel = new JPanel();
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
        
		this.labelsPanel = new JPanel(new GridBagLayout());
		for(int i=0; i<classes.size() ; i++){

 			 Class c11 = classes.get(i);
				//display label with class name
				String className = mB.getLongName(c11);
				JLabel displayName = new JLabel(className);
				displayName.setOpaque(true);
				displayName.setBackground(Color.GRAY);
				displayName.setForeground(Color.WHITE);
				
 			 if(classRegex==null || classRegex.equals("")){
 				if(assesmentRegex!=null){
 					 if(nameRegex!=null){

 						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex, nameRegex);
 						 labelsPanel.add(displayName, gbc);

 						 labelsPanel.add(clas, gbc);
 					 }else{
 						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex);
 						 labelsPanel.add(displayName, gbc);

 			             labelsPanel.add(clas, gbc);
		             }
				 }else{
					 if(nameRegex!=null){
 						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex, nameRegex);
 						 labelsPanel.add(displayName, gbc);

 						 labelsPanel.add(clas, gbc);
 					 }else{
 						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex);
 						 labelsPanel.add(displayName, gbc);

 			             labelsPanel.add(clas, gbc);
		             }

				 }
 			 }
 			 else if(mB.getLongName(c11).contains(classRegex)){
 				 if(assesmentRegex!=null){
 					if(nameRegex!=null){
						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex, nameRegex);
 						 labelsPanel.add(displayName, gbc);

						 labelsPanel.add(clas, gbc);
					 }else{
						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex);
 						 labelsPanel.add(displayName, gbc);

			             labelsPanel.add(clas, gbc);
		             }
 				 }else{
 					if(nameRegex!=null){
						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex, nameRegex);
 						 labelsPanel.add(displayName, gbc);

						 labelsPanel.add(clas, gbc);
					 }else{
						 ClassDisplay clas = new ClassDisplay(this,c11, mB,assesmentRegex);
 						 labelsPanel.add(displayName, gbc);

			             labelsPanel.add(clas, gbc);
		             }

 				 }
 			 }
         
         }

	    
        JScrollPane scroll = new JScrollPane(labelsPanel);
		viewPanel.add(scroll, BorderLayout.CENTER);
		
		
		
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
    
    
    public void setMyMb(Markbook mB){
    	this.mB = mB;
    }

}




