/*
Tarun Vaidhyanathan
115510562
R02
 */
import java.util.LinkedList;

/**
 * Represents a virtualline where you can queue in from your phone for rides at an amusement park.
 */
public class VirtualLine extends LinkedList<Person>{
   /**
    * The linked list representing the virtual line.
    */
    LinkedList<Person> virtualLine = new LinkedList<>();
    /**
     * Adds a person to the end of the virtual line.
     * @param p the person to be added to the virtual line
     */
    public void enqueue(Person p){
        this.virtualLine.addLast(p);
    }
    /**
     * Removes and returns the person at the front of the virtual line.
     *
     * @return the person removed from the front of the virtual line
     */
    public Person dequeue(){
        Person p = this.virtualLine.getFirst();
        this.virtualLine.removeFirst();
        return p;
    }
    /**
     * Retrieves, but does not remove, the person at the front of the virtual line.
     * @return the person at the front of the virtual line
     */
    public Person peek(){
        return this.virtualLine.getFirst();
    }
    /**
     * Checks if the virtual line is empty.
     * @return {@code true} if the virtual line is empty, {@code false} otherwise
     */
    public boolean isEmpty(){
        return this.virtualLine.isEmpty();
    }
}
