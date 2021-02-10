public class PeriodicWithScreen extends Thread {
	private int period;
	private Screen screen;

	public PeriodicWithScreen(int period, Screen screen) {
	    this.period = period;
			this.screen = screen;
	}

	public void run() {
		// Uncomment to print default priority
		//System.out.println("Priority = " + getPriority());

		// Uncomment to set priority
		//setPriority(getPriority() + 1);

		try {
			while(!Thread.interrupted()) {
				screen.writePeriod(period);
				Thread.sleep(period);
			}
		} catch(InterruptedException e){
			//Requested to stop
		}
		System.out.println("Thread stopped.");
	}

	public static void main(String[] args) {
		Screen screen = new Screen();
		for(String arg : args) {
			PeriodicWithScreen p = new PeriodicWithScreen(Integer.parseInt(arg), screen);
			p.start();
		}
		// Uncomment to print the number of active threads.
		//System.out.println(Thread.activeCount());

		//System.out.print("(Number of active threads = " + Thread.activeCount() + "), ");
	}
}
