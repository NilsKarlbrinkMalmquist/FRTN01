import SimEnvironment.*;

// BallAndBeamRegul class to be written by you
public class BallAndBeamRegul extends Thread {

    // IO Declarations
    private AnalogSource analogInAngle;
    private AnalogSource analogInPosition;
    private AnalogSink analogOut;
    private AnalogSink analogRef;

    private ReferenceGenerator refGen;
    private PID pid_controller;
    private PI pi_controller;

    // Limits
    private final double UMIN = -10, UMAX = 10;

	// Constructor
    public BallAndBeamRegul(ReferenceGenerator refgen, BallAndBeam bb, int priority) {
        // Code to initialize the IO
        analogInPosition = bb.getSource(0);
        analogInAngle = bb.getSource(1);
        analogOut = bb.getSink(0);
        analogRef = bb.getSink(1);

        //TODO C3.E8: Write your code here //
        this.refGen = refgen;
        pi_controller = new PI("PI");
        pid_controller = new PID("PID");
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
    private double limit(double u) {
        if (u < UMIN)
            return UMIN;
        else if (u > UMAX)
            return UMAX;
        else
            return u;
    }

	public void run() {
        //TODO C3.E8: Define help variables here //
		long t = System.currentTimeMillis();
        while (!Thread.interrupted()) {
            //TODO C3.E8: Write your code here //

        	//Read inputs:
        	double yPos = analogInPosition.get();
        	double refPos = refGen.getRef();

        	synchronized(pid_controller) {
        		double refAng = pid_controller.calculateOutput(yPos, refPos);
        		synchronized(pi_controller) {
        			double yAng = analogInAngle.get();
        			double v = pi_controller.calculateOutput(yAng, refAng);
        			double u = limit(v);
        			analogOut.set(u);
        			pi_controller.updateState(u);
        		}
        		pid_controller.updateState(refAng);
        	}
        	analogRef.set(refPos);

        	t = t + pid_controller.getHMillis(); //använda PID eller PI:s tid?
            long duration = t - System.currentTimeMillis();
            if(duration > 0) {
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
