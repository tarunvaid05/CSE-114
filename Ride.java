/*
Tarun Vaidhyanathan
115510562
R02
 */
public class Ride {
    private int duration;
    private int timeLeft;
    private int capacity;
    private String name;
    private VirtualLine virtualLine = new VirtualLine();
    private HoldingQueue holdingQueue = new HoldingQueue(0);
    private Person[] peopleOnRide;

    public Ride(int duration,int capacity, String name){
        VirtualLine line = new VirtualLine();
        this.virtualLine = line;
        HoldingQueue holdingQueue = new HoldingQueue(0);
        this.holdingQueue = holdingQueue;
        this.duration = duration;
        this.capacity = capacity;
        this.name = name;
    }
    public int getDuration(){
        return this.duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public int getTimeLeft(){
        return this.timeLeft;
    }
    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public VirtualLine getVirtualLine(){
        return this.virtualLine;
    }
    public void setVirtualLine(VirtualLine virtualLine){
        this.virtualLine = virtualLine;
    }
    public HoldingQueue getHoldingQueue(){
        return this.holdingQueue;
    }
    public void setHoldingQueue(HoldingQueue holdingQueue){
        this.holdingQueue = holdingQueue;
    }
    public Person[] getPeopleOnRide(){
        return this.peopleOnRide;
    }
    public void setPeopleOnRide(Person[] peopleOnRide){
        this.peopleOnRide = peopleOnRide;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
}
