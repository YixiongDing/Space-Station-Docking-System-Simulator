/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

// Produces new cargo ships wanting to unload cargo at the space station. *
public class Producer extends Thread {

    // The wait zone at which ships will arrive
    private WaitZone arrivalZone;

    // Create a new producer
    Producer(WaitZone newArrivalZone) {
        this.arrivalZone = newArrivalZone;
    }

    // Cargo ships arrive at the arrival zone at random intervals.
    public void run() {
        while(!isInterrupted()) {
            try {

                // If currently there's no ship in the arrival zone
                if(!arrivalZone.checkOccupied()) {

                    // Create a new cargo ship and send it to the arrvial zone.
                    Ship ship = Ship.getNewShip();
                    arrivalZone.arrive(ship);

                    // Let some time pass before the next ship arrives
                    sleep(Params.arrivalLapse());
                }
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
