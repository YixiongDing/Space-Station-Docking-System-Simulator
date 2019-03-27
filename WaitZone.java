/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Wait zone for ships' arrival and departure */

/* Assuming that the arrival zone can only hold one ship, no limits for the departure zone */
public class WaitZone {

    private String zoneType;
    private String shipNo;

    // Indicating which the ship will depart at the departure zone
    private String departShip = "noship";

    // Flag indicating a new ship arrives the arrival zone and requires a pilot
    private boolean needPiolt = true;

    // Flag indicating the wait zone is in use
    private boolean inUse = false;

    public WaitZone(String zoneType) {
        this.zoneType = zoneType;
    }

    public synchronized void arrive(Ship ship){
        System.out.println(ship.toString()+" arrives at arrival zone");
        this.inUse = true;
        this.shipNo = ship.toString();
        this.needPiolt = true;
    }

    public synchronized void depart(){
        System.out.println(departShip+" departs departure zones");
        departShip = "noship";
    }

    public synchronized boolean checkOccupied(){
        return this.inUse;
    }

    public synchronized String getShipNo(){
        return this.shipNo;
    }

    public synchronized String getDepartShip(){
        return this.departShip;
    }

    public synchronized boolean getNeedPilot() {
        return this.needPiolt;
    }

    public synchronized void updateDeparture(String departShip){
        this.departShip = departShip;
    }

    public synchronized void updateOccupied(boolean inUse){
        this.inUse = inUse;
    }

    public synchronized void updateNeedPilot(boolean needPiolt){
        this.needPiolt = needPiolt;
    }
}