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
				this.regul = regul;
				this.square = square;

				//Inputs and outputs:
				onInput = b.getOnButtonInput();
				offInput = b.getOffButtonInput();
				incInput = b.getIncButtonInput();
				decInput = b.getDecButtonInput();

				setPriority(priority);
    }

	// run method
	public void run() {
        //TODO C2.E8: Create help-variables //
				final int h = 10; //peiod (ms)
				final double delta = 10.0/(60.0 * 1000.0) * h; //10V per minute

        try {
            while (!Thread.interrupted()) {
                //TODO C2.E8: Check button-status and take action accordingly, every 10 ms //
								if(onInput.get()){
									regul.turnOn();
								}
								if(offInput.get()){
									regul.turnOff();
								}
								if(incInput.get()){
									square.incAmp(delta);
								}
								if(decInput.get()){
									square.decAmp(delta);
								}
                Thread.sleep(h);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Buttons stopped.");
    }
}
