// PI class to be written by you
public class PI {
	// Current PI parameters
	private PIParameters p;

    private double I = 0; // Integral part of controller
    private double e = 0; // Error signal
    private double v = 0; // Output from controller
	
	// Constructor
	public PI(String name) {
        //TODO C3.E2: Write your code here //
    }
	
	// Calculates the control signal v.
	// Called from BeamRegul.
	public synchronized double calculateOutput(double y, double yref) {
        //TODO C3.E2: Write your code here //
        return 0;
    }
	
	// Updates the controller state.
	// Should use tracking-based anti-windup
	// Called from BeamRegul.
	public synchronized void updateState(double u) {
        //TODO C3.E2: Write your code here //
    }
	
	// Returns the sampling interval expressed as a long.
	// Note: Explicit type casting needed
	public synchronized long getHMillis() {
        //TODO C3.E2: Write your code here //
        return 0;
    }
	
	// Sets the PIParameters.
	// Called from PIGUI.
	// Must clone newParameters.
	public synchronized void setParameters(PIParameters newParameters) {
        //TODO C3.E2: Write your code here //
    }
}
