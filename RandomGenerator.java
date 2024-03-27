/*
Tarun Vaidhyanathan
115510562
R02
 */
/**
 * Provides methods for selecting a ride randomly from an array of rides.
 */
public class RandomGenerator {
    /**
     * Selects a ride randomly from the given array of rides.
     * @param rides an array of rides from which to select
     * @return the randomly selected ride
     */
    public static Ride selectRide(Ride[]rides){
        double random = (Math.random()) * rides.length;
        int intRandom = (int)random;
        return rides[intRandom];

    }
    /**
     * Selects a ride randomly from the given array of rides based on specified probabilities.
     * @param rides an array of rides from which to select
     * @param probabilities an array of probabilities corresponding to each ride
     * @return the randomly selected ride
     */
    public static Ride selectRide(Ride[] rides, double[] probabilities){
        double random = (Math.random()) * rides.length;
        int intRandom = (int)random;
        return rides[intRandom];
    }
}
