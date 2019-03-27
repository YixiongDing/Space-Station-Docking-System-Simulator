/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Acquires a newly arrived ship and required number of tugs to dock the ship. After unloaded, acquires tugs for undocking.*/
public class Pilot extends Thread{

    private int pilotNo;
    private WaitZone arrivalZone;
    private WaitZone departureZone;
    private Tugs tugs;
    private Berth berth;

    // Plot's current ship
    private String currentShip;

    // Indicates what the pilot is doing at the moment
    private String currrentState = "idle";

    // Indicates the pilot needs more than available tugs
    private boolean needTugs = true;


    public Pilot(int pilotNo, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
        this.pilotNo = pilotNo;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.tugs = tugs;
        this.berth = berth;
    }

    public void run() {
        while(!isInterrupted()) {
            try {

                // If there's a ship in the arrival zone and requires a pilot and the pilot is free, the pilot will acquires the ship
                if(arrivalZone.checkOccupied() && currrentState.equals("idle") && arrivalZone.getNeedPilot()){

                    // Update pilot's ship and state
                    arrivalZone.updateNeedPilot(false);
                    currentShip = arrivalZone.getShipNo();
                    System.out.println("pilot " + pilotNo + " acquires " + arrivalZone.getShipNo() + ".");
                    currrentState = "waitDockingTugs";
                }
                else if(currrentState.equals("waitDockingTugs")){

                    // If the available tugs are more than 3, the pilot acquires tugs
                    if(tugs.checkAvailableTugs() >= Params.DOCKING_TUGS){

                        // Update available tugs number
                        tugs.updateAvailableTugs(tugs.checkAvailableTugs()-Params.DOCKING_TUGS);
                        needTugs = true;
                        currrentState = "Docking";

                        // The ship gets out of the arrival zone, starts to travel to the vicinity of the berth
                        arrivalZone.updateOccupied(false);
                        sleep(Params.TRAVEL_TIME);

                    }else{

                        // If the available tugs are less than 3, the pilot waits
                        if(needTugs) {
                            System.out.println("pilot " + pilotNo + " acquires 3 tugs (" + tugs.checkAvailableTugs() + " available).");
                            needTugs = false;
                        }
                    }
                }

                // If there's no other ships in the berth and the shield is not activated, the pilot docks the ship
                else if(currrentState.equals("Docking") && !berth.isActive() && !berth.checkOccupied()){

                    // Update the berth is occupied
                    berth.updateOccupied(true);
                    System.out.println(currentShip + " docks at berth.");
                    sleep(Params.DOCKING_TIME);
                    tugs.updateAvailableTugs(tugs.checkAvailableTugs()+Params.DOCKING_TUGS);
                    System.out.println("pilot " + pilotNo + " releases " + Params.DOCKING_TUGS + " tugs (" +tugs.checkAvailableTugs()+" available).");
                    currrentState = "Unloading";
                }
                else if(currrentState.equals("Unloading")){
                    System.out.println(currentShip + " being unloaded.");
                    currrentState = "waitUndockingTugs";
                    sleep(Params.UNLOADING_TIME);
                }
                else if(currrentState.equals("waitUndockingTugs")){

                    // If the available tugs are more than 2, the pilot acquires tugs
                    if(tugs.checkAvailableTugs() >= Params.UNDOCKING_TUGS){
                        tugs.updateAvailableTugs(tugs.checkAvailableTugs()-Params.UNDOCKING_TUGS);
                        currrentState = "Undocking";
                    } else{
                        if(needTugs) {
                            System.out.println("pilot " + pilotNo + " acquires 2 tugs (" + tugs.checkAvailableTugs() + " available).");
                            needTugs = false;
                        }
                    }
                }

                // If the shield is not activated, ship can undock
                else if(currrentState.equals("Undocking") && !berth.isActive()){
                    System.out.println(currentShip + " undocks from berth.");
                    sleep(Params.UNDOCKING_TIME);
                    berth.updateOccupied(false);
                    currrentState = "Departuring";
                }
                else if(currrentState.equals("Departuring")){
                    tugs.updateAvailableTugs(tugs.checkAvailableTugs()+Params.UNDOCKING_TUGS);
                    System.out.println("pilot " + pilotNo + " releases " + currentShip);
                    System.out.println("pilot " + pilotNo + " releases " + Params.UNDOCKING_TUGS + " tugs (" +tugs.checkAvailableTugs()+" available).");
                    currrentState = "idle";
                    departureZone.updateDeparture(currentShip);
                    sleep(Params.TRAVEL_TIME);
                }
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
