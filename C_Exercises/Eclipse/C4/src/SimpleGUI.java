import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 *  class SimpleGUI:
 *
 * Playground for the swing environment
 */
public class SimpleGUI {

    private String name;

    /*
     * Constructor for the class SimpleGUI
     */
    public SimpleGUI(String name) {
        this.name = name;
    }

    public void initializeGUI() {
        //TODO C4.E1: Write your GUI code here //
    	JFrame frame = new JFrame(name);
    	JPanel pane = new JPanel();
    	JButton button = new JButton("Press");
    	JButton quitbutton = new JButton("Quit");
    	JLabel label = new JLabel(" ");
    	
    	pane.setLayout(new BorderLayout());
    	pane.add(button, BorderLayout.SOUTH);
    	pane.add(quitbutton, BorderLayout.NORTH);
    	pane.add(label, BorderLayout.CENTER);
    	    	
    	frame.getContentPane().add(pane, BorderLayout.CENTER);
    	frame.pack();
    	frame.setVisible(true);

        //TODO C4.E2: Write your anonymous listener here //
    	button.addActionListener(event -> label.setText("Hello World!"));
    	
    	quitbutton.addActionListener(new ActionListener() {
    		public  void actionPerformed(ActionEvent e) {
    			 System.out.println(Thread.currentThread().getPriority());
    			 System.exit(0);
    	    }
    	});
    	
    	
        //TODO C4.E3: Write code to find the event-dispatching thread's priority, here //
    }

    public static void main(String[] args) {
        new SimpleGUI("My GUI").initializeGUI();
    }
}
