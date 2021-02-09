import SimEnvironment.*;

// BeamRegul class to be written by you
public class BeamRegul extends Thread {

    private ReferenceGenerator refGen;
    private PI controller;

    private final double uMin = -10, uMax = 10;

	// IO interface declarations
	private AnalogSource analogIn;
	private AnalogSink analogOut;
	private AnalogSink analogRef;
	
	//Define min and max control output:
	//private double uMin = -10.0;
	//private double uMax = 10.0;
	
	public BeamRegul(ReferenceGenerator ref, Beam beam, int priority) {
		// Code to initialize the IO
		analogIn = beam.getSource(0);
		analogOut = beam.getSink(0);
		analogRef = beam.getSink(1);

        //TODO C3.E3: Write your code here //
		refGen = ref;
		controller = new PI("PI");
		setPriority(priority);
	}

    /**
     * Method limit:
     *
     * @param:
     *     u (double): The signal to saturate
     * @return:
     *     double: the saturated value
     */
    private double limit(double u, double umin, double umax) {
        if (u < umin) 
             return umin;
        else if (u > umax) 
            return umax;
        else 
            return u;
    }
	
	public void run() {
        //TODO C3.E3: Write your code here and help variables here //
		long t = System.currentTimeMillis();
        while (true) {
            // Read inputs
            double y = analogIn.get();
            double ref = refGen.getRef();
            
            //TODO C3.E3: Write your code here //
            synchronized(controller) { //To avoid parameter changes in between
            	//Compute control signal
            	double u = limit(controller.calculateOutput(y, ref), uMin, uMax);
            	
            	//Set output
            	analogOut.set(u);
            	
            	//update state
            	controller.updateState(u);
            }
            analogRef.set(ref); //Only for the plotter animation
            
            t = t + controller.getHMillis();
            long duration = t - System.currentTimeMillis();
            if(duration > t) {
            	try {
            		sleep(duration);
            	}
            	catch(InterruptedException e){
            		e.printStackTrace();
            	}
            }
        }
	}
}
