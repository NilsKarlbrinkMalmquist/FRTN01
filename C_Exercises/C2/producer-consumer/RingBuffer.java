/*
 *  class RingBuffer:
 *
 * Ringbuffer for the producer-consumer exercise
 */
public class RingBuffer implements RingBufferInterface {

    //TODO C2.E14: Declare private variables //
    private Object[] elements;
    private int nbrOfElementsInBuf = 0; //curSize
    private final int bufSize;
    private int nextRead = 0;
    private int nextWrite = 0;


    /*
     * Constructor for the class RingBuffer
     *
     * @param:
     *     bufSize (int): Size of buffer
     */
    public RingBuffer(int bufSize) {
        //TODO C2.E14: Write your code here //
        this.bufSize = bufSize;
        elements = new Object[bufSize];
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
        while(nbrOfElementsInBuf == 0){
          wait();
        }
        Object returnObj = elements[nextRead];
        elements[nextRead] = null;
        nextRead = (nextRead + 1) % bufSize;
        nbrOfElementsInBuf--;
        notifyAll();
        return returnObj;
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
        while(nbrOfElementsInBuf == bufSize){
          wait();
        }
        elements[nextWrite] = o;
        nextWrite = (nextWrite + 1) % bufSize;
        nbrOfElementsInBuf++;
        notifyAll();
    }
}
