import se.lth.control.realtime.Semaphore;
import se.lth.control.realtime.ConditionVariable;

/*
 *  class RingBufferWithSemaphore:
 *
 * Ringbuffer for the producer-consumer exercise
 */
public class RingBufferWithSemaphore implements RingBufferInterface {

    //TODO C2.E15: Declare private variables //
    
    /*
     * Constructor for the class RingBufferWithSemaphore
     *
     * @param:
     *     bufSize (int): Size of buffer
     */
    public RingBufferWithSemaphore(int bufSize) {
        //TODO C2.E15: Write your code here //
    }

    /*
     * method get:
     *
     * @throws:
     *     InterruptedException
     * @return:
     *     Object: The next object in the buffer
     */
    public Object get() throws InterruptedException {
        //TODO C2.E15: Write your code here //
        Thread.sleep(1);
    	return new Object();
    }

    /*
     * method put:
     *
     * @throws:
     *     InterruptedException
     * @param:
     *     o (Object): Object to enter into ringbuffer.
     */
    public void put(Object o) throws InterruptedException {
        //TODO C2.E15: Write your code here //
        Thread.sleep(1);
    }
}
