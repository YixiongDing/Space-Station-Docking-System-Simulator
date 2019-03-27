/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* A cargo ship, with a unique id, that arrives at the space station to deliver its cargo */
public class Ship {

    // A unique identifier for this cargo ship
    private int id;

    // The next ID to be allocated
    private static int nextId = 1;

    // A flag indicating whether the ship is currently loaded
    boolean loaded;

    // Create a new vessel with a given identifier
    private Ship(int id) {
        this.id = id;
        this.loaded = true;
    }

    // Get a new Ship instance with a unique identifier
    public static Ship getNewShip() {
        return new Ship(nextId++);
    }

    // Produce an identifying string for the cargo ship
    public String toString() {
        return "ship [" + id + "]";
    }
}
