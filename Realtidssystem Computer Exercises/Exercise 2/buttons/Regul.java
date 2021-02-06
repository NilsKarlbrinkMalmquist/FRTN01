// Code skeleton for the Regul class in the Buttons exercise

import SimEnvironment.*;

public class Regul extends Thread {
	// Analog inputs and outputs
	private AnalogSource yIn;
	private AnalogSink uOut;
	private AnalogSink rOut;

	// Box lamp outputs
	private DigitalButtonOut onButtonLamp;
	private DigitalButtonOut offButtonLamp;

	// Internal Monitors
	private ParameterMonitor paramMon;
	private ReferenceMonitor refMon;
	private OnMonitor onMon;

	// Constructor
	// Here the internal monitor objects should be created and
	// the inputs and outputs should be initialized.
	public Regul(int priority, Box b, FirstOrderProcess proc) {
        //TODO C2.E11: Initialize the sources and sinks //
				yIn = proc.getSource(0);
				uOut = proc.getSink(0);
				rOut = proc.getSink(1);
        //TODO C2.E11: Set the buttons and thread priority //
				onButtonLamp = b.getOnButtonLamp();
				offButtonLamp = b.getOffButtonLamp();
				updateLamps();
				setPriority(priority);
    }

	// Public method to set K. Should not be synchronized.
	public void setK(double K) {
        //TODO C2.E11: Write your code here //
				paramMon.setK(K);
    }

	// Public method to set the reference. Should not be synchronized.
	public void setRef(double ref) {
        //TODO C2.E11: Write your code here //
				refMon.setRef(ref);
    }

	// Method to check if the controller  is on. Should be private
	// since it is only called from Regul itself.
	private boolean isOn() {
        //TODO C2.E11: Write your code here //
        return onMon.isOn();
    }

	// Public methods to turn off and on the controller
	// Should not be synchronized. Should update the button lamps
	public void turnOff() {
        //TODO C2.E11: Write your code here //
				onMon.setOn(false);
				updateLamps();
    }
	public void turnOn() {
        //TODO C2.E11: Write your code here //
				onMon.setOn(true);
				updateLamps();
    }

	private void updateLamps(){
		boolean isOn = isOn();
		onButtonLamp.set(isOn);
		offButtonLamp.set(!isOn);
	}

	// Class definition for internal ParameterMonitor
	private class ParameterMonitor {
		private double K = 1.0;

		// Synchronized access methods. K should always be non-negative.
		public synchronized double getK() {
            //TODO C2.E11: Write your code here //
            return K;
        }
		public synchronized void setK(double K) {
            //TODO C2.E11: Write your code here //
						if(K >= 0){
							this.K = K;
						}
						else{
							System.out.println("Illegal K value ignored: " + K);
						}
        }
	}

	// Class definition for internal ReferenceMonitor
	private class ReferenceMonitor {
		private double ref = 0.0;

		// Synchronized access methods
		public synchronized double getRef() {
            //TODO C2.E11: Write your code here //
            return ref;
        }
		public synchronized void setRef(double ref) {
            //TODO C2.E11: Write your code here //
						this.ref = ref;
        }
	}

	// Class definition for internal OnMonitor
	private class OnMonitor {
		private boolean on = false;

		// Synchronized access methods
		public synchronized boolean isOn() {
            //TODO C2.E11: Write your code here //
            return on;
        }
		public synchronized void setOn(boolean on) {
            //TODO C2.E11: Write your code here //
						this.on = on;
        }
	}

	// Run method
	public void run() {
        //TODO C2.E11: Define help variables //
				final long h = 100; //period (ms)
        try {
            while (!Thread.interrupted()) {
               //TODO C2.E11: Write your code here //
							 //Get inputs:
							 double y = yIn.get();
							 double r = refMon.getRef();
							 double K = paramMon.getK();

							 //Compute control signal:
							 double u = 0.0;
							 if(isOn()){
								 u = K * (r - y); //P-controller
							 }

							 //Set outputs:
							 uOut.set(u);
							 rOut.set(r);

               Thread.sleep(h);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Regul stopped.");
    }
}
