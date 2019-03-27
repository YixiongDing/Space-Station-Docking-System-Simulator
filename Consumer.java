/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Consumes unloaded cargo ships from the departure zone */
public class Consumer extends Thread {

    // The wait zone from which cargo ships depart
    private WaitZone departureZone;

    // Creates a new consumer for the given wait zone
    Consumer(WaitZone newDepartureZone) {
        this.departureZone = newDepartureZone;
    }

    // Repeatedly collect waiting ships from the departure zone
    public void run() {
        while (!isInterrupted()) {
            try {

                // If there is a ship in the departure zone
                if(!departureZone.getDepartShip().equals("noship")) {

                    // Remove a vessel that is in the departure wait zone
                    departureZone.depart();

                    // Let some time pass before the next departure
                    sleep(Params.departureLapse());
                    departureZone.updateDeparture("noship");
                }
            }
            catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
