public class PeriodicStaticLock extends Thread {

	private int period;
	
	public PeriodicStaticLock(int p) {
		period = p;	
	}
	
	public void run() {
		// Uncomment to print out default priority
		//System.out.println("Priority = " + getPriority());
		
		// Uncomment to set priority
		//setPriority(getPriority() + 1);
		
		try {
			while (!Thread.interrupted()) {
                /**
                 * Synchronize on the class lock instead of the object lock.
                 * Problems might occur if classes other than
                 * PeriodicStaticLock is trying to write to the screen.
                 */
				synchronized (this.getClass()) {
					System.out.print(period + ", ");
				}
				sleep(period);
			}
		} catch (InterruptedException e) {
			// Requested to stop
		}
		System.out.println("Thread stopped.");
	}
	
	public static void main(String[] argv) {
		for (int i = 0; i < argv.length; i++) {
			PeriodicStaticLock m = new PeriodicStaticLock(Integer.parseInt(argv[i]));
			m.start();
		}
	}
}
