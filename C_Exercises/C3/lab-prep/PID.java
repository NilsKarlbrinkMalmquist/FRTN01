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
    }
	
	// Calculates the control signal v.
	// Called from BeamRegul.
	public synchronized double calculateOutput(double y, double yref) {
        //TODO C3.E8: Write your code here //
        return 0;
    }
	
	// Updates the controller state.
	// Should use tracking-based anti-windup
	// Called from BeamRegul.
	public synchronized void updateState(double u) {
        //TODO C3.E8: Write your code here //
    }
	
	// Returns the sampling interval expressed as a long.
	// Note: Explicit type casting needed
	public synchronized long getHMillis() {
        //TODO C3.E8: Write your code here //
        return 0;
    }
	
	// Sets the PIDParameters.
	// Called from PIDGUI.
	// Must clone newParameters.
	public synchronized void setParameters(PIDParameters newParameters) {
        //TODO C3.E8: Write your code here //
    }
}
