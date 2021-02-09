// BallAndBeamRegul class to be written by you
public class BallAndBeamRegul extends Thread {

    // IO Declarations 
    private AnalogSource analogInAngle;
    private AnalogSource analogInPosition;
    private AnalogSink analogOut;
    private AnalogSink analogRef;

    private ReferenceGenerator refGen;
    private PID pid;
    private PI pi;

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

        while (!interrupted()) {
            //TODO C3.E8: Write your code here //
        }
    }
}
