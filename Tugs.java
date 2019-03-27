/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Space tugs for docking and undocking ships at the berth*/
public class Tugs {

    // The total tugs number
    private int tugsNum;

    // Current available tugs
    private int availableTugs;

    public Tugs(int tugsNum) {
        this.tugsNum = tugsNum;
        this.availableTugs = tugsNum;
    }

    public synchronized int checkAvailableTugs(){
        return this.availableTugs;
    }

    public synchronized void updateAvailableTugs(int availableTugs){
        this.availableTugs = availableTugs;
    }
}