// Code skeleton for the Buttons class in the Buttons exercise

import SimEnvironment.*;

public class Buttons extends Thread {
	private Regul regul;
	private SquareWave square;

	// Inputs and outputs
	private DigitalButtonIn onInput;
	private DigitalButtonIn offInput;
	private DigitalButtonIn incInput;
	private DigitalButtonIn decInput;

	// Constructor
	public Buttons(Regul regul, SquareWave square, int priority, Box b) {
        //TODO C2.E8: Store variables and set priority //'

    }

	// run method
	public void run() {
        //TODO C2.E8: Create help-variables //

        try {
            while (!interrupted()) {
                //TODO C2.E8: Check button-status and take action accordingly, every 10 ms //
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Buttons stopped.");
    }
}
