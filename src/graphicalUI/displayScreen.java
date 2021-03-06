package graphicalUI;
import graphicalUI.statsSearchBar.StatsMainScreen;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

import main.*;

/*	main gui class, setup the gui display
 * 
 */
public class displayScreen {
	private static Color selectedButtonColor = Color.GRAY;
	private static Color defaultButtonColor = Color.LIGHT_GRAY;
	private static Color menuBackground = new Color(167, 161, 157);
	private static Color menuBackgroundOne = new Color(184, 174, 165);

    private static void createAndShowGUI() {
    	 final JFrame frame = new JFrame();
    	 frame.setMinimumSize(new Dimension(1000,1000));
    	 final Markbook mB = new Markbook();
    	 
    	 //sample buttons for menu using box layout vertical span
    	 JPanel menu = new JPanel();
    	 menu.setLayout(new GridBagLayout());
    	 menu.setBackground(menuBackground);
    	 //buttons to change pages
    	 String name1 = "<html><b>" + "Manage" + "<br>" + "Classes" + "</b></html>";
    	 final JButton managmentButton = new JButton(name1);

    	 String name2 = "<html><b>" + "Assessments" + "</b></html>";
    	 final JButton searchingButton = new JButton(name2);
    	 
    	 String name3 = "<html><b>" + "Statistics" + "</b></html>";
    	 final JButton statsButton = new JButton(name3);
    	 
    	 // statsScreen statsScreen = new statsScreen(mB, frame.getSize().getHeight(), frame.getSize().getWidth());
    	 
    	 //center to the pane
    	 managmentButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
    	 searchingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 
    	 
	 ///////	-menu bar
 	 	 JLabel menuTitle = new JLabel("Navigation", SwingConstants.CENTER);
 	 	 
    	 JPanel pageSelectionPanel = new JPanel(new GridBagLayout());
    	 GridBagConstraints c = new GridBagConstraints();
    	 
    	 c.fill = GridBagConstraints.BOTH;
    	 
    	 c.insets = new Insets(0,0,0,1);
    	 c.gridx = 0;
		 c.gridy = 0;
		 c.gridheight = 1;
		 c.gridwidth = 3;
		 c.weighty = 0.03;
	 	 c.weightx = 1;
	 	 pageSelectionPanel.add(menuTitle , c);
    	 
    	 c.gridx = 0;
		 c.gridy = 1;
		 c.gridheight = 1;
		 c.gridwidth = 3;
		 c.weighty = 0.31;
	 	 c.weightx = 1;
    	 
	 	 pageSelectionPanel.add(managmentButton , c);
	 	 
	 	 c.gridy = 2;
	 	 pageSelectionPanel.add(searchingButton, c);
 
	 	 c.gridy = 3;
	 	 pageSelectionPanel.add(statsButton, c);
	 	
	 	c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.8;
		c.weightx = 1;
	 	menu.add(pageSelectionPanel, c);
	 	pageSelectionPanel.setBackground(menuBackground);
 	////////
	 	 
	////////	-options bar
	 	JPanel optionsPanel = new JPanel(new GridBagLayout());
	 	optionsPanel.setBackground(menuBackgroundOne);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		optionsPanel.setBorder(loweredbevel);
	 	JLabel optionsTitle = new JLabel("Options", SwingConstants.CENTER);
	 	String loadName = "<html><b>" + "Load"+ "<br>" + "Database" + "</b></html>";
	 	JButton loadButton = new JButton(loadName);
	 	String saveName = "<html><b>" + "Save"+ "<br>" + "Database" + "</b></html>";
	 	JButton saveButton = new JButton(saveName);
	 	String resetName = "<html><b>" + "Generate"+ "<br>" + "Random" + "<br>" + "Database" + "</b></html>";
	 	JButton resetButton = new JButton(resetName);
	 	c.insets = new Insets(0,0,0,8);
	 	c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.1;
		c.weightx = 1;
		optionsPanel.add(optionsTitle, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.3;
		c.weightx = 1;
		optionsPanel.add(loadButton, c);
		c.gridy = 2;
		optionsPanel.add(saveButton, c);
		c.gridy = 3;
		optionsPanel.add(resetButton, c);
		
		c.insets = new Insets(20,0,0,8);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.2;
		c.weightx = 1;
		menu.add(optionsPanel, c);
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel1 = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel1);
		menu.setBorder(compound);
	////////
    	 
    	 
    	 //cardlayout for the main panel two switch between possible classes of panels we create
         final CardLayout cardLayout = new CardLayout();
    	 final JPanel mainPanel = new JPanel(cardLayout);
    	 
    	 //panels
    	 final managementScreen managmentPanel = new managementScreen(mB);
         final markingPanel mP = new markingPanel(mB);
         
    	 
         mainPanel.add(managmentPanel, "managmentPanel");
         mainPanel.add(mP, "searchingPanel");

         mP.refreshClasses(null, null, null, mB.getClasses());//TODO

         StatsMainScreen statsScreen = new StatsMainScreen(mB);
         mainPanel.add(statsScreen, "statsScreen");

         
         //Note: This gives the effect of the page expanding 
         //from the center of the page
         //Center the display
         
         frame.setLocationRelativeTo(null);
         frame.setLayout(new BorderLayout());
          
         //two panes, menu and mane pane
         frame.add(menu,      BorderLayout.WEST);
         frame.add(mainPanel, BorderLayout.CENTER);
         
////////	-options bar buttons
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mB.loadDatabase();
				managmentPanel.refresh();
				mP.refreshClasses(null, null, null, mB.getClasses());//TODO
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mB.saveDatabase();
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mB.generateRandomData();
				managmentPanel.refresh();
				mP.refreshClasses(null, null, null, mB.getClasses());//TODO
			}
		});
	///////   
         
         managmentButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 cardLayout.show(mainPanel, "managmentPanel");
                 
                 managmentButton.setBackground(selectedButtonColor);
                 searchingButton.setBackground(defaultButtonColor);
                 statsButton.setBackground(defaultButtonColor);
                 
             }
         });
         searchingButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 cardLayout.show(mainPanel, "searchingPanel");
                 
                 managmentButton.setBackground(defaultButtonColor);
                 searchingButton.setBackground(selectedButtonColor);
                 statsButton.setBackground(defaultButtonColor);
                 
             }
         });
         
	   statsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    cardLayout.show(mainPanel, "statsScreen");
			    
			    managmentButton.setBackground(defaultButtonColor);
                searchingButton.setBackground(defaultButtonColor);
                statsButton.setBackground(selectedButtonColor);
                
			}
	   });
         
	   managmentButton.setBackground(selectedButtonColor);
       searchingButton.setBackground(defaultButtonColor);
       statsButton.setBackground(defaultButtonColor);
	   
	   
         //maximise to display size
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.pack();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         //frame.setResizable(false);
         

    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}