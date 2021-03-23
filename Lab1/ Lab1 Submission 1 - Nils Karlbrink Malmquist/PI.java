public class PI {
    // Current PI parameters
    private PIParameters p;

    /** Add more private variables here if needed */
    private double I = 0; // Integral part of controller
    private double e = 0; // Error signal
    private double v = 0; // Output from controller

    // Constructor
    public PI() {
        p = new PIParameters();
        // Initial PI Variables
        p.Beta          = 1.0;
        p.H             = 0.02;
        p.integratorOn  = false;
        p.K             = 2.5;
        p.Ti            = 0.0;
        p.Tr            = 10.0;
        //Should we call opCom here or in e.g main?
        setParameters(p);
    }

    // Calculates the control signal v.
    // Called from BeamRegul.
    public synchronized double calculateOutput(double y, double yref) {
        /** Written by you */
    	e = yref - y;
    	v = p.K * (p.Beta * yref - y) + I;
    	return v;
    }

    // Updates the controller state.
    // Should use tracking-based anti-windup
    // Called from BeamRegul.
    public synchronized void updateState(double u) {
        /** Written by you */
    	if(p.integratorOn) {
    		I = I + (p.K*p.H/p.Ti) * e + (p.H / p.Tr) * (u - v);
    	}
    	else {
    		I = 0.0;
    	}
    }

    // Returns the sampling interval expressed as a long.
    // Note: Explicit type casting needed
    public synchronized long getHMillis() {
        /** Written by you */
    	return (long)(p.H*1000);
    }

    // Sets the PIParameters.
    // Called from PIGUI.
    // Must clone newParameters.
    public synchronized void setParameters(PIParameters newParameters) {
        /** Written by you */
    	p = (PIParameters)newParameters.clone();
    	if(!p.integratorOn) {
    		I = 0.0;
    	}
    }

    // Sets the I-part of the controller to 0.
    // For example needed when changing controller mode.
    public synchronized void reset() {
        /** Written by you */
    	I = 0.0;
    }

    // Returns the current PIParameters.
    public synchronized PIParameters getParameters() {
        /** Written by you */
    	return p;
    }
}
