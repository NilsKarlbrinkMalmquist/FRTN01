/*
 *  class RingBuffer:
 *
 * Ringbuffer for the producer-consumer exercise
 */
public class RingBuffer implements RingBufferInterface {

    //TODO C2.E14: Declare private variables //
    
    /*
     * Constructor for the class RingBuffer
     *
     * @param:
     *     bufSize (int): Size of buffer
     */
    public RingBuffer(int bufSize) {
        //TODO C2.E14: Write your code here //
    }

    /*
     * Synchronized method get:
     *
     * @throws:
     *     InterruptedException
     * @return:
     *     Object: The next object in the buffer
     */
    public synchronized Object get() throws InterruptedException {
        //TODO C2.E14: Write your code here //
        Thread.sleep(1);
    	return new Object();
    }

    /*
     * Synchronized method put:
     *
     * @throws:
     *     InterruptedException
     * @param:
     *     o (Object): the object to put into the RingBuffer
     */
    public synchronized void put(Object o) throws InterruptedException {
        //TODO C2.E14: Write your code here //
        Thread.sleep(1);
    }
}
