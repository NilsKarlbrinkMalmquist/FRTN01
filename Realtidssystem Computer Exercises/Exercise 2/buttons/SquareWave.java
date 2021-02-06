// Code skeleton for the SquareWave class in the Buttons exercise

public class SquareWave extends Thread {
	private Regul regul;
	private int sign;

	private AmplitudeMonitor ampMon = new AmplitudeMonitor();

	// Internal AmplitudeMonitor class
	// Constructor not necessary
	private class AmplitudeMonitor {
		private double amp = 0.0;

		// Synchronized access methods. The amplitude should always be non-negative.
		public synchronized double getAmp() {
            //TODO C2.E9: Write your code //
            return amp;
        }
		public synchronized void setAmp(double amp) {
            //TODO C2.E9: Write your code //
						this.amp = Math.max(amp, 0);
        }
	}

	// Constructor
	public SquareWave(Regul regul, int priority) {
        //TODO C2.E9: Store variables and set priority //
				this.regul = regul;
				setPriority(priority);
    }

	// Public methods to decrease and increase the amplitude by delta. Called from Buttons
	// Should be synchronized on ampMon. Should also call the setRef method in Regul
	public void incAmp(double delta) {
        //TODO C2.E9: Write code that is synchronized on ampMon //
				synchronized(ampMon){
					ampMon.setAmp(ampMon.getAmp() + delta);
				}
				setRef();
  }

	public void decAmp(double delta) {
        synchronized(ampMon){
					ampMon.setAmp(ampMon.getAmp() - delta);
				}
				setRef();
    }

	private void setRef(){
		regul.setRef(sign * ampMon.getAmp());
	}

	// run method
	public void run() {
        //TODO C2.E9: Create help-variables //
				final long h = 10000; //period(ms). Shouln't it be 20000?

        try {
            while (!interrupted()) {
                //TODO C2.E9: Write periodic code here //
								sign = -sign;
								setRef();
                Thread.sleep(h);
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        System.out.println("SquareWave stopped.");
    }
}
