import com.sun.javafx.tools.packager.Param;

public class Pilot extends Thread{

    private int pilotNo;
    private WaitZone arrivalZone;
    private WaitZone departureZone;
    private Tugs tugs;
    private Berth berth;
    private String currrentState = "idle";
    private boolean idle = true;
    private String currentShip;


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
                if(arrivalZone.checkOccupied() && currrentState.equals("idle")){
                    currentShip = arrivalZone.getShipNo();
                    currrentState = "waitTugs";
                    System.out.println("pilot " + pilotNo + " acquires " + arrivalZone.getShipNo() + ".");
                }
                else if(currrentState.equals("waitTugs")){
                    if(tugs.checkAvailableTugs() >= 3){
                        sleep(Params.TRAVEL_TIME);
                        currrentState = "toBerth";
                        tugs.updateAvailableTugs(tugs.checkAvailableTugs()-3);
                        arrivalZone.updateOccupied(false);
                    }else{
                        System.out.println("pilot " + pilotNo + " acquires 3 tugs (" + tugs.checkAvailableTugs()+" available).");
                    }
                }else if(currrentState.equals("toBerth") && !berth.isActive() && !berth.checkOccupied()){
                    sleep(Params.DOCKING_TIME);
                    currrentState = "docking";
                    berth.updateOccupied(true);
                    tugs.updateAvailableTugs(tugs.checkAvailableTugs()+3);
                    System.out.println(currentShip + " docks at berth.");
                }
                else if(currrentState.equals("docking")){
                    sleep(Params.UNLOADING_TIME);
                    currrentState = "unloading";
                    System.out.println(currentShip + " being unloaded.");
                }
                else if(currrentState.equals("unloading")){
                    if(tugs.checkAvailableTugs() >= 2){
                        currrentState = "undocking";
                        tugs.updateAvailableTugs(tugs.checkAvailableTugs()-2);
                    } else{
                        System.out.println("pilot " + pilotNo + " acquires 2 tugs (" + tugs.checkAvailableTugs()+" available).");
                    }
                }
                else if(currrentState.equals("undocking") && !berth.isActive()){
                    sleep(Params.UNDOCKING_TIME);
                    currrentState = "outBerth";
                    berth.updateOccupied(false);
                    System.out.println(currentShip + " undocks from berth.");
                }
                else if(currrentState.equals("outBerth")){
                    sleep(Params.TRAVEL_TIME);
                    currrentState = "idle";
                    tugs.updateAvailableTugs(tugs.checkAvailableTugs()+2);
                    departureZone.updateDeparture(currentShip);
                }
            } catch (Exception e) {
                this.interrupt();
            }
        }
    }



}
