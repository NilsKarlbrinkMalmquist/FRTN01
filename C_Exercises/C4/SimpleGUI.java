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
        
        // ActionListener
        //TODO C4.E2: Write your anonymous listener here //
        //TODO C4.E3: Write code to find the event-dispatching thread's priority, here //
    }

    public static void main(String[] args) {
        new SimpleGUI("My GUI").initializeGUI();
    }
}
