// Code skeleton for the SquareWave class in the Buttons exercise

public class SquareWave extends Thread {
	private Regul regul;
	private int sign;
	
	private AmplitudeMonitor ampMon;
	
	// Internal AmplitudeMonitor class
	// Constructor not necessary
	private class AmplitudeMonitor {
		private double amp = 0.0;
		
		// Synchronized access methods. The amplitude should always be non-negative.
		public synchronized double getAmp() {
            //TODO C2.E9: Write your code //
            return 0.0;
        }
		public synchronized void setAmp(double amp) {
            //TODO C2.E9: Write your code //
        }
	}
	
	// Constructor
	public SquareWave(Regul regul, int priority) {
        //TODO C2.E9: Store variables and set priority //
    }
	
	// Public methods to decrease and increase the amplitude by delta. Called from Buttons
	// Should be synchronized on ampMon. Should also call the setRef method in Regul
	public void incAmp(double delta) {
        //TODO C2.E9: Write code that is synchronized on ampMon //
    }
	public void decAmp(double delta) {
        //TODO C2.E9: Write code that is synchronized on ampMon //
    }
	
	// run method
	public void run() {
        //TODO C2.E9: Create help-variables //

        try {
            while (!interrupted()) {
                //TODO C2.E9: Write periodic code here //
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SquareWave stopped.");
    }
}

