import SimEnvironment.*;

// BeamRegul class to be written by you
public class BeamRegul extends Thread {

    private ReferenceGenerator refGen;
    private PI controller;

    private final double UMIN = -10, UMAX = 10;

	// IO interface declarations
	private AnalogSource analogIn;
	private AnalogSink analogOut;
	private AnalogSink analogRef;
	
	public BeamRegul(ReferenceGenerator refgen, Beam beam, int priority) {
		// Code to initialize the IO
		analogIn = beam.getSource(0);
		analogOut = beam.getSink(0);
		analogRef = beam.getSink(1);

        //TODO C3.E3: Write your code here //
	}

    /**
     * Method limit:
     *
     * @param:
     *     u (double): The signal to saturate
     * @return:
     *     double: the saturated value
     */
    private double limit(double u) {
        if (u < UMIN) 
            return UMIN;
        else if (u > UMAX) 
            return UMAX;
        else 
            return u;
    }
	
	public void run() {

        //TODO C3.E3: Write your code here and help variables here //

        while (!interrupted()) {
            // Read inputs
            y = analogIn.get();
            //TODO C3.E3: Write your code here //


            //TODO C3.E3: Write code for control computation here //
            // Set output
            analogOut.set(u);

            // Set reference in gui
            analogRef.set(ref);

            //TODO C3.E3: Write code to make run method periodic here //
        }
	}
}
