/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Main class for the simulation program */
public class Main {

    //The driver of the space station docking simulation
    public static void main(String [] args) throws InterruptedException {

        // Generate the locations and tugs
        WaitZone arrivalZone = new WaitZone("arrival");
        WaitZone departureZone = new WaitZone("departure");
        Tugs tugs = new Tugs(Params.NUM_TUGS);
        Berth berth = new Berth("berth");

        // Generate the producer, consumer and operator processes
        Producer producer = new Producer(arrivalZone);
        Consumer consumer = new Consumer(departureZone);
        Operator operator = new Operator(berth);

        // Create an array trains to hold the pilots
        Pilot[] pilot = new Pilot[Params.NUM_PILOTS];

        // Generate and start the individual pilot processes
        for (int i = 0; i < Params.NUM_PILOTS; i++) {
            pilot[i] = new Pilot(i, arrivalZone, departureZone, tugs, berth);
            pilot[i].start();
        }

        // Start the remaining processes
        producer.start();
        consumer.start();
        operator.start();

        // Join all processes
        for (int i = 0; i < Params.NUM_PILOTS; i++) {
            pilot[i].join();
        }
        producer.join();
        consumer.join();
        operator.join();
    }
}