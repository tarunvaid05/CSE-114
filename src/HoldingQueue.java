/*
Tarun Vaidhyanathan
115510562
R02
 */

/**
 * Represents a holding queue at an amusement park.
 * Holding queue extends Virtual Line.
 */
public class HoldingQueue extends VirtualLine{
    /**
     * Represents the maxsize of a holding queue.
     */
    private int maxSize;
    /**
     * Constructs a holding queue with a maximum size.
     * @param maxSize the maximum size of the holding queue
     */
    public HoldingQueue(int maxSize){
        super();
        this.maxSize = maxSize;
    }
    /**
     * Retrieves the maximum size of the holding queue.
     * @return the maximum size of the holding queue
     */

    public int getMaxSize(){
        return this.maxSize;
    }
    /**
     * Sets the maximum size of the holding queue.
     * @param maxSize the maximum size of the holding queue to be set
     */

    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }
}
