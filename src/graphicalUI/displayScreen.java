package graphicalUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class displayScreen {
	
    private static void createAndShowGUI() {
    	 final JFrame frame = new JFrame();
    	 
    	 
    	 //sample buttons for menu using box layout vertical span
    	 JPanel menu = new JPanel();
    	 //buttons
    	 JButton creation = new JButton("creation");
    	 JButton searching = new JButton("searching");
    	 
    	 //center to the pane
    	 creation.setAlignmentX(Component.CENTER_ALIGNMENT); 
    	 searching.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 
    	 //adding to the menu panel
    	 menu.add(creation);
    	 menu.add(searching);
    	 
    	 //such that its vertical
    	 menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
    	 menu.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    	 //cardlayout for the main panel two switch between possible classes of panels we create
         final CardLayout cardLayout = new CardLayout();
    	 final JPanel mainPanel = new JPanel(cardLayout);
    	 
    	 //example panels
    	 JPanel creationPanel = new JPanel(new BorderLayout() );
    	 creationPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
         JPanel searchingPanel = new JPanel(new BorderLayout() );
         searchingPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
         
         //filler content remove later
         JLabel label1 = new JLabel("here goes stuff about CREATION");
         JLabel label2 = new JLabel("here SEARCHING stuff may be");
    	 
         creationPanel.add(label1);
         searchingPanel.add(label2);
         
         mainPanel.add(creationPanel, "panel1");
         mainPanel.add(searchingPanel, "panel2");
         
         //Note: This gives the effect of the page expanding 
         //from the center of the page
         //Center the display
         frame.setLocationRelativeTo(null);
         frame.setLayout(new BorderLayout());
          
         //two panes, menu and mane pane
         frame.add(menu,      BorderLayout.WEST);
         frame.add(mainPanel, BorderLayout.CENTER);
         
         creation.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 cardLayout.show(mainPanel, "panel1");
             }
         });
         searching.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 cardLayout.show(mainPanel, "panel2");
             }
         });
         
         
         
         //maximise to display size
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.pack();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.setResizable(false);

    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
