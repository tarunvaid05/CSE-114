/*
Tarun Vaidhyanathan
115510562
R02
 */
/**
 * Th person class represents a person visiting an amusement park.
 */
public class Person {
    /** Unique identifier for person */
    private int number;
    /** Signifies if a person is regular,silver,or gold */
    private int maxLines;
    /** if the person is on a ride, on the line, or available via status */
    private Status status;
    /** array of rides that the person is queued for */
    private Ride[] line;
    /**
     * Constructs a person with a specified number and maximum lines they can join.
     * @param number    The unique identifier for the person
     * @param maxLines  The maximum number of lines the person can join
     */
    public Person(int number, int maxLines){
        try{
            if(number <= 0) {
                throw new IllegalArgumentException();
            }
            else{
                this.number = number;
                this.status = Status.Available;
                this.maxLines = maxLines;
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Please input a positive number!");
        }
    }
    /**
     * Gets the customer type based on the maximum number of lines they can join.
     * @return The customer type Regular, Silver, or Gold
     */
    public String customerType(){
        if(this.getMaxLines() == 1){
            return "Regular";
        }
        if(this.getMaxLines() == 2){
            return "Silver";
        }
        else{
            return "Gold";
        }
    }
    /**
     * Gets the number of the person.
     * @return The number of the person
     */
    public int getNumber(){
        return this.number;
    }
    /**
     * Sets the number of the person.
     * @param number The number to set
     */
    public void setNumber(int number){
        this.number = number;
    }
    /**
     * Gets the maximum number of lines the person can join.
     * @return The maximum number of lines
     */
    public int getMaxLines(){
        return this.maxLines;
    }
    /**
     * Sets the maximum number of lines the person can join.
     * @param maxLines The maximum number of lines to set
     */
    public void setMaxLines(int maxLines){
        this.maxLines = maxLines;
    }
    /**
     * Gets the status of the person.
     * @return The status of the person
     */
    public Status getStatus(){
        return this.status;
    }
    /**
     * Sets the status of the person.
     * @param status The status to set
     */
    public void setStatus(Status status){
        this.status = status;
    }
    /**
     * Gets the array of rides the person is currently queued for.
     * @return The array of rides
     */
    public Ride[] getLine(){
        return this.line;
    }
    /**
     * Sets the array of rides the person is currently queued for.
     * @param line The array of rides to set
     */
    public void setLine(Ride[] line){
        this.line = line;
    }

}