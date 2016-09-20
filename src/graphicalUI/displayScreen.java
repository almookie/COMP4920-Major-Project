package graphicalUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class displayScreen {
	
    private static void createAndShowGUI() {
    	 final JFrame frame = new JFrame();
    	 
    	 
    	 //sample buttons for menue using box layout verticl span
    	 Panel menu = new Panel();
    	 menu.add(new Button("A"));
    	 menu.add(new Button("B"));
    	 menu.add(new Button("C"));
    	 menu.add(new Button("D"));
    	 menu.add(new Button("E"));
    	 menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));


         //Note: This gives the effect of the page expanding 
         //from the center of the page
         
         //Center the display
         frame.setLocationRelativeTo(null);

         frame.setLayout(new BorderLayout());
          
         //two panes, menu and mane pane
         frame.add(menu,   BorderLayout.WEST);
         frame.add(new Button("PAne"), BorderLayout.CENTER);
         //maximise to display size
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
