package graphicalUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.*;
public class displayScreen {
	
    private static void createAndShowGUI() {
    	 final JFrame frame = new JFrame();
    	 frame.setMinimumSize(new Dimension(1000,1000));
    	 Markbook mB = new Markbook();
    	 mB.generateRandomData();
    	 
    	 //sample buttons for menu using box layout vertical span
    	 JPanel menu = new JPanel();
    	 //buttons
    	 JButton managmentButton = new JButton("creation");
    	 JButton searchingButton = new JButton("searching");
    	 
    	 JButton statsButton = new JButton("Statistics");
    	 
    	 // statsScreen statsScreen = new statsScreen(mB, frame.getSize().getHeight(), frame.getSize().getWidth());
    	 
    	 //center to the pane
    	 managmentButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
    	 searchingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 
    	 statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 
    	 //adding to the menu panel
    	 menu.add(managmentButton);
    	 menu.add(searchingButton);
    	 
    	 menu.add(statsButton);
    	 
    	 //such that its vertical
    	 menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
    	 menu.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    	 
    	 
    	 //cardlayout for the main panel two switch between possible classes of panels we create
         final CardLayout cardLayout = new CardLayout();
    	 final JPanel mainPanel = new JPanel(cardLayout);
    	 
    	 //example panels
    	 JPanel managmentPanel = new managementScreen(mB);
         markingPanel mP = new markingPanel(mB);
         
         managmentPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
         mP.setBorder(BorderFactory.createLineBorder(Color.BLUE));
         
         //filler content remove later
         JLabel label1 = new JLabel("here goes stuff about CREATION");
         JLabel label2 = new JLabel("here SEARCHING stuff may be");
    	 
         
         mainPanel.add(managmentPanel, "managmentPanel");
         mainPanel.add(mP, "searchingPanel");
         
         // mainPanel.add(statsScreen, "statsScreen");
         
         //Note: This gives the effect of the page expanding 
         //from the center of the page
         //Center the display
         
         frame.setLocationRelativeTo(null);
         frame.setLayout(new BorderLayout());
          
         //two panes, menu and mane pane
         frame.add(menu,      BorderLayout.WEST);
         frame.add(mainPanel, BorderLayout.CENTER);
         
         
         managmentButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 cardLayout.show(mainPanel, "managmentPanel");
             }
         });
         searchingButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 cardLayout.show(mainPanel, "searchingPanel");
             }
         });
         
	   statsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    cardLayout.show(mainPanel, "statsScreen");
			}
	   });
         
         //maximise to display size
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.pack();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.setResizable(false);
         

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