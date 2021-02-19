import SimEnvironment.AnalogSink;
import SimEnvironment.AnalogSource;

public class Regul extends Thread {

    private PI piInner = new PI();
    private PID pidOuter = new PID();

    private BallBeamAnimator ballBeam;

    private ReferenceGenerator refGen;
    private OpCom opCom;

    private int priority;
    private boolean shouldRun = true;
    private long starttime;

    private ModeMonitor modeMon;


    public Regul(int pri, ModeMonitor modeMon) {
        priority = pri;
        setPriority(priority);

        ballBeam = new BallBeamAnimator(modeMon);

        this.modeMon = modeMon;
    }

    /** Sets OpCom (called from main) */
    public void setOpCom(OpCom opCom) {
        /** Written by you */
    	this.opCom = opCom;
    }

    /** Sets ReferenceGenerator (called from main) */
    public void setRefGen(ReferenceGenerator refGen) {
        /** Written by you */
    	this.refGen = refGen;
    }

    // Called in every sample in order to send plot data to OpCom
    private void sendDataToOpCom(double yRef, double y, double u) {
        double x = (double) (System.currentTimeMillis() - starttime) / 1000.0;
        opCom.putControlData(x, u);
        opCom.putMeasurementData(x, yRef, y);
    }

    // Sets the inner controller's parameters
    public void setInnerParameters(PIParameters p) {
        /** Written by you */
    	piInner.setParameters(p);
    }

    // Gets the inner controller's parameters
    public PIParameters getInnerParameters() {
        /** Written by you */
    	return piInner.getParameters();
    }

    // Sets the outer controller's parameters
    public void setOuterParameters(PIDParameters p) {
        /** Written by you */
    	pidOuter.setParameters(p);
    }

    // Gets the outer controller's parameters
    public PIDParameters getOuterParameters(){
        /** Written by you */
    	return pidOuter.getParameters();
    }

    // Called from OpCom when shutting down
    public void shutDown() {
        shouldRun = false;
    }

    // Saturation function
    private double limit(double v) {
        return limit(v, -10, 10);
    }

    // Saturation function
    private double limit(double v, double min, double max) {
        if (v < min) v = min;
        else if (v > max) v = max;
        return v;
    }

    public void run() {

        long duration;
        long t = System.currentTimeMillis();
        starttime = t;

        while (shouldRun) {
            /** Written by you */
        	//Read inputs:
        	double yAng;
        	double yPos;
        	double v;
        	double u;
        	double refPos;
        	double uff;
        	double phiff;
        	
            switch (modeMon.getMode()) {
                case OFF: {
                    piInner.updateState(0);
                    pidOuter.updateState(0);
                    ballBeam.setControlSignal(0);
                    break;
                }
                case BEAM: {
                    synchronized(piInner) { //To avoid parameter changes in between
                    	//Compute control signal
                    	refPos = refGen.getRef();
                    	yAng = ballBeam.getBeamAngle();
                    	uff = refGen.getUff();
                    	u = limit(piInner.calculateOutput(yAng, refPos) + uff);
                    	
                    	//Set output
                    	ballBeam.setControlSignal(u);
                    	
                    	//update state
                    	piInner.updateState(u - uff);
                    	
                    	//Send data to opCom for plotting
                    	sendDataToOpCom(refPos, yAng, u);
                    }
                    break;
                }
                case BALL: {
                    /** Written by you */
                	synchronized(pidOuter) {
                		yPos = ballBeam.getBallPos();
                		refPos = refGen.getRef();
                		phiff = refGen.getPhiff();
                		double refAng = pidOuter.calculateOutput(yPos, refPos) + phiff;
                		
                		synchronized(piInner) {
                			uff = refGen.getUff();
                			yAng = ballBeam.getBeamAngle();
                			v = piInner.calculateOutput(yAng, refAng) + uff;
                   			u = limit(v);
                			ballBeam.setControlSignal(u);
                			piInner.updateState(u - uff);
                		}
                		
                		pidOuter.updateState(refAng - phiff);
                	}
                	sendDataToOpCom(refPos, yPos, u);
                    break;
                }
                default: {
                    System.out.println("Error: Illegal mode.");
                    break;
                }
            }

            //sendDataToOpCom(refPos, yPos, u);

            // sleep
            t = t + piInner.getHMillis();
            duration = t - System.currentTimeMillis();
            if (duration > 0) {
                try {
                    sleep(duration);
                } catch (InterruptedException x) {}
            } else {
                System.out.println("Lagging behind...");
            }
        }
        ballBeam.setControlSignal(0.0);
    }
}
