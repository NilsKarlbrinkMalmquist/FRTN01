public class PID {
    // Current PID parameters
    private PIDParameters p;

    /** Add more private variables here if needed */
    private double I = 0; // Integral part of PID
    private double D = 0; // Derivative part of PID
    private double v = 0; // Computed control signal
    private double e = 0; // Error signal
    private double y = 0; // Measurement signal
    private double yOld = 0; // Old measurement signal
    private double eold = 0;
    private double ad; // Help variable for Derivative calculation
    private double bd; // Help variable for Derivative calculation

    // Constructor
    public PID() {
        p = new PIDParameters();
        // Initial PID Variables
        p.Beta          = 1.0;
        p.H             = 0.02;
        p.integratorOn  = false;
        p.K             = -0.2;
        p.N             = 5;
        p.Td            = 1.0;
        p.Ti            = 0.0;
        p.Tr            = 10.0;
        ad = p.Td / (p.Td + p.N * p.H);
        bd = p.K * ad * p.N;
        setParameters(p);
    }

    // Calculates the control signal v.
    // Called from BallAndBeamRegul.
    public synchronized double calculateOutput(double y, double yref) {
        /** Written by you */
    	this.e = yref -y;
    	this.D = ad * D + bd * (e - eold);
    	this.v = p.K * (p.Beta * yref - y) + I + D;
		this.y = y;
		return this.v;
    }

    // Updates the controller state.
    // Should use tracking-based anti-windup
    // Called from BallAndBeamRegul.
    public synchronized void updateState(double u) {
        /** Written by you */
		if(p.integratorOn) {
			I = I + (p.K * p.H / p.Ti) * e + (p.H / p.Tr) * (u - v);
		}
		else {
			reset();
		}
		this.yOld = y;
		this.eold = e;
    }

    // Returns the sampling interval expressed as a long.
    // Explicit type casting needed.
    public synchronized long getHMillis() {
        /** Written by you */
    	return (long)(p.H * 1000.0);
    }

    // Sets the PIDParameters.
    // Called from PIDGUI.
    // Must clone newParameters.
    public synchronized void setParameters(PIDParameters newParameters) {
        /** Written by you */
    	p = (PIDParameters)newParameters.clone();
		if(!p.integratorOn) {
			reset();
		}
        ad = p.Td / (p.Td + p.N * p.H);
        bd = p.K * ad * p.N;
    }

    // Sets the I-part of the controller to 0.
    // For example needed when changing controller mode.	
    public synchronized void reset() {
        /** Written by you */
    	I = 0.0;
    }

    // Returns the current PIDParameters.
    public synchronized PIDParameters getParameters() {
        /** Written by you */
    	return p;
    }
}