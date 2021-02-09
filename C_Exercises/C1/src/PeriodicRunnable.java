
public class PeriodicRunnable extends Base implements Runnable {
	private int period;
	
	public PeriodicRunnable(int period) {
		this.period = period;
	}

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("Priority = " + t.getPriority());
		t.setPriority(t.getPriority() + 1);
		System.out.println("Priority = " + t.getPriority());
		try {
			while(!Thread.interrupted()) {
				System.out.print(period);
				System.out.print(", ");
				
				Thread.sleep(period);
			}
		} catch(InterruptedException e){
			//Requested to stop
		}
		System.out.println("Thread stopped.");
	}
	
	public static void main(String[] args) {
		for(String arg : args) {
			PeriodicRunnable m = new PeriodicRunnable(Integer.parseInt(arg));
			Thread t = new Thread(m);
			t.start();
		}	
	}
	
}
