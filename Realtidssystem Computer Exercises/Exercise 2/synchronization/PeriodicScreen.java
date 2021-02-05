/*
 *  class PeriodicScreen extends Thread:
 *
 * PeriodicScreenally executing class
 */
public class PeriodicScreen extends Thread {

    private int period;
    private Screen screen;
    
    /*
     * Constructor for the class PeriodicScreen
     *
     * @param:
     *     period (int): the period of the periodically executing object
     *     screen (Screen): The screen to write to
     */
    public PeriodicScreen(int period, Screen screen) {

        // TODO C2.E4: Write initialization code here //

    }

    /*
     * Method run:
     *
     * Run the thread
     */
    public void run() {
        System.out.println("Period " + period + " => Priority " + getPriority());
        try {
            while (!Thread.interrupted()) {

                 // TODO C2.E4: Write code to write to the shared screen resource //

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Screen s = new Screen();
        for (String arg : args) {
            new PeriodicScreen(Integer.parseInt(arg), s).start();
        }
    }
}
