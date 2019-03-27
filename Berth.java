/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Berth for unloading ships, only one ship at a time */
public class Berth {

    private String berthType;

    // Flag indicating whether the shelid is activated
    private boolean shieldOn = false;

    // Flag indicating whether the berth is in use
    private boolean inUse = false;

    public Berth(String berthType) {
        this.berthType = berthType;
    }

    public synchronized boolean isActive(){
        return this.shieldOn;
    }

    public synchronized boolean checkOccupied(){
        return this.inUse;
    }

    public synchronized void operateShield(boolean shieldOn){
        this.shieldOn = shieldOn;
    }

    public synchronized void updateOccupied(boolean inUse){
        this.inUse = inUse;
    }
}
