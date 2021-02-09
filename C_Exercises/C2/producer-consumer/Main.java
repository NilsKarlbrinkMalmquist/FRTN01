import se.lth.control.realtime.ConditionVariable;
import se.lth.control.realtime.Semaphore;

public class Main extends Thread{
	public static void main(String[] args) {
		//RingBuffer rb = new RingBuffer(5);
		RingBufferWithSemaphore rb = new RingBufferWithSemaphore(5);

		Producer p = new Producer(rb);
		Consumer c = new Consumer(rb);

		p.start();
		c.start();
	}
}
