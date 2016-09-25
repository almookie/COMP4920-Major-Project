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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class markingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel current;

	public markingPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel searchPanel = new JPanel();
		JPanel viewPanel = new JPanel();
		
		//lhs stuff
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		//title related stuff
		JLabel filterTitle = new JLabel("Search Options!");
		filterTitle.setPreferredSize(new Dimension(50,60));
		filterTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		searchPanel.add(filterTitle, BorderLayout.NORTH);
		
		//panel with the search fields
		JPanel searchFieldsPanels = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //gbc for search fields
        c.anchor = GridBagConstraints.WEST;
	    c.gridx=1;
	    c.weightx=1;
	    c.insets = new Insets(5,8,15,5);
	    c.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraints c1 = new GridBagConstraints();
        //gbc for ttitles fields
        c1.anchor = GridBagConstraints.WEST;
	    c1.gridx=1;
	    c1.weightx=1;
	    c1.insets = new Insets(5,8,15,5);
	    c1.fill = GridBagConstraints.HORIZONTAL;
	    final JTextField jtfFilter = new JTextField();
	    final JTextField jtfFilter1 = new JTextField();
	    final JTextField jtfFilter2 = new JTextField();
	    final JTextField jtfFilter3 = new JTextField();

	    
	    searchFieldsPanels.add(new JLabel("Option1?"),c1);
	    searchFieldsPanels.add(jtfFilter,c);
	    searchFieldsPanels.add(new JLabel("Option2?"),c1);
	    searchFieldsPanels.add(jtfFilter1,c);
	    searchFieldsPanels.add(new JLabel("Option3?"),c1);
	    searchFieldsPanels.add(jtfFilter2,c);
	    searchFieldsPanels.add(new JLabel("Option4?"),c1);
	    searchFieldsPanels.add(jtfFilter3,c);
	    //final jbutton to searchs
	    c1.fill = GridBagConstraints.NONE;
	    c1.insets = new Insets(100,15,15,5);
	    JButton getResults = new JButton("Get Results");
	    searchFieldsPanels.add(getResults,c1);
		searchPanel.add(searchFieldsPanels);
		
		viewPanel.setLayout(new BorderLayout());
		viewPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));


		JLabel searchTitle = new JLabel("Search Results To Mark On!!");
		searchTitle.setPreferredSize(new Dimension(50,60));
		searchTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		searchTitle.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		viewPanel.add(searchTitle, BorderLayout.NORTH);
		
		
		//SHOULD BE ABLE TO ADD DYNAMICALLY IN A LOOP
		JPanel searchResultsPanel = new JPanel(new BorderLayout());
		//needbuttoon for focus
		JLabel className = new JLabel("Class ??");
		className.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		className.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel className1 = new JLabel("Assignment 1");
		className1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		className1.setBorder(BorderFactory.createLineBorder(Color.RED));

		String[] columnNames = {"First Name", "Last Name", "Mark"};
		Object[][] data =
			{
			    {"Leon	", "x", "0"},
			    {"JAckie ", "x", "0"},
			    {"Ali",  "x", "0"},
			    {"James ",  "x", "0"},
			};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable( model );
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JLabel className2 = new JLabel("Assignment 2");
		className1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		className1.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		DefaultTableModel model1 = new DefaultTableModel(data, columnNames);
		JTable table1 = new JTable( model1 );
		table1.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table1.setFillsViewportHeight(true);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        
		JPanel labelsPanel = new JPanel(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        labelsPanel.add(className, gbc);
        labelsPanel.add(className1, gbc);
        labelsPanel.add(scrollPane, gbc);
        labelsPanel.add(className2, gbc);
        labelsPanel.add(scrollPane1, gbc);

        
        
        searchResultsPanel.add(labelsPanel,BorderLayout.NORTH	);
        
		viewPanel.add(searchResultsPanel, BorderLayout.CENTER);
		
		
		
		//split screen into half
		JSplitPane overall = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, viewPanel);
		
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
           
            }
        });
		
		
		this.add(overall);
	}
	    
    public JPanel getPanel(){
    	return current;
    }
    
    
    
}
