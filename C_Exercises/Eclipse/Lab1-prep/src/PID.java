// PID class to be written by you
public class PID {
	// Current PID parameters
	private PIDParameters p;

    private double I = 0; // Integral part of PID
    private double D = 0; // Derivative part of PID
    private double v = 0; // Computed control signal
    private double e = 0; // Error signal
    private double y = 0; // Measurement signal
    private double yOld = 0; // Old measurement signal
    private double ad; // Help variable for Derivative calculation
    private double bd; // Help variable for Derivative calculation
	
	// Constructor
	public PID(String name) {
        //TODO C3.E8: Write your code here //
		p = new PIDParameters();
		p.Beta = 1.0;
		p.H = 0.08;	 //0.02-0.1 - same as for the inner controller.
		p.integratorOn = false;
		p.K = -0.1;	//-0.2 - -0.01
		p.Ti = 0.0;
		p.Tr = 10.0;
		p.Td = 2; //0.5 - 4
		p.N = 7; //5 - 10
		ad = p.Td / (p.Td + p.N * p.H);
		bd = p.K * ad * p.N;
		new PIDGUI(this, p, name);
		setParameters(p);
    }
	
	// Calculates the control signal v.
	// Called from BeamRegul.
	public synchronized double calculateOutput(double y, double yref) {
        //TODO C3.E8: Write your code here //
		e = yref - y;
		D = ad * D - bd * (y - yOld);
		v = p.K * (p.Beta * yref - y) + I + D;
		this.y = y;
		return v;
    }
	
	// Updates the controller state.
	// Should use tracking-based anti-windup
	// Called from BeamRegul.
	public synchronized void updateState(double u) {
        //TODO C3.E8: Write your code here //
		if(p.integratorOn) {
			I = I + (p.K * p.H / p.Ti) * e + (p.H / p.Tr) * (u - v);
		}
		else {
			I = 0.0;
		}
		yOld = y; //Enl föreläsningsanteckningar?
    }
	
	// Returns the sampling interval expressed as a long.
	// Note: Explicit type casting needed
	public synchronized long getHMillis() {
        //TODO C3.E8: Write your code here //
		return (long)(p.H * 1000.0);
    }
	
	// Sets the PIDParameters.
	// Called from PIDGUI.
	// Must clone newParameters.
	public synchronized void setParameters(PIDParameters newParameters) {
        //TODO C3.E8: Write your code here //
		p = (PIDParameters)newParameters.clone();
		if(!p.integratorOn) {
			I = 0.0;
		}
    }
}
