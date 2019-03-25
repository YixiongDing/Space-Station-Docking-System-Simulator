import com.sun.javafx.tools.packager.Param;

public class Pilot extends Thread{

    private int pilotNo;
    private WaitZone arrivalZone;
    private WaitZone departureZone;
    private Tugs tugs;
    private Berth berth;
    private String currrentState = "idle";
    private boolean needTugs = true;
    private String currentShip;


    public Pilot(int pilotNo, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
        this.pilotNo = pilotNo;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.tugs = tugs;
        this.berth = berth;
    }

    public synchronized void run() {
        while(!isInterrupted()) {
            try {
                if(arrivalZone.checkOccupied() && currrentState.equals("idle") && arrivalZone.getNeedPilot()){
                    arrivalZone.updateNeedPilot(false);
                    currentShip = arrivalZone.getShipNo();
                    currrentState = "waitTugs";
                    System.out.println("pilot " + pilotNo + " acquires " + arrivalZone.getShipNo() + ".");
                }
                else if(currrentState.equals("waitTugs")){
                    if(tugs.checkAvailableTugs() >= Params.DOCKING_TUGS){
                        currrentState = "toBerth";
                        tugs.updateAvailableTugs(tugs.checkAvailableTugs()-Params.DOCKING_TUGS);
                        arrivalZone.updateOccupied(false);
                        needTugs = true;
                        sleep(Params.TRAVEL_TIME);
                    }else{
                        if(needTugs) {
                            System.out.println("pilot " + pilotNo + " acquires 3 tugs (" + tugs.checkAvailableTugs() + " available).");
                            needTugs = false;
                        }
                    }
                }else if(currrentState.equals("toBerth") && !berth.isActive() && !berth.checkOccupied()){
                    currrentState = "docking";
                    berth.updateOccupied(true);
                    tugs.updateAvailableTugs(tugs.checkAvailableTugs()+Params.DOCKING_TUGS);
                    System.out.println(currentShip + " docks at berth.");
                    System.out.println("pilot " + pilotNo + " releases " + Params.DOCKING_TUGS + " tugs (" +tugs.checkAvailableTugs()+" available).");
                    sleep(Params.DOCKING_TIME);
                }
                else if(currrentState.equals("docking")){
                    currrentState = "unloading";
                    System.out.println(currentShip + " being unloaded.");
                    sleep(Params.UNLOADING_TIME);
                }
                else if(currrentState.equals("unloading")){
                    if(tugs.checkAvailableTugs() >= Params.UNDOCKING_TUGS){
                        currrentState = "undocking";
                        tugs.updateAvailableTugs(tugs.checkAvailableTugs()-Params.UNDOCKING_TUGS);
                    } else{
                        if(needTugs) {
                            System.out.println("pilot " + pilotNo + " acquires 2 tugs (" + tugs.checkAvailableTugs() + " available).");
                            needTugs = false;
                        }
                    }
                }
                else if(currrentState.equals("undocking") && !berth.isActive()){
                    currrentState = "outBerth";
                    berth.updateOccupied(false);
                    System.out.println(currentShip + " undocks from berth.");
                    sleep(Params.UNDOCKING_TIME);
                }
                else if(currrentState.equals("outBerth")){
                    departureZone.updateDeparture(currentShip);
                    currrentState = "idle";
                    tugs.updateAvailableTugs(tugs.checkAvailableTugs()+Params.UNDOCKING_TUGS);
                    System.out.println("pilot " + pilotNo + " releases " + currentShip);
                    System.out.println("pilot " + pilotNo + " releases " + Params.UNDOCKING_TUGS + " tugs (" +tugs.checkAvailableTugs()+" available).");
                    sleep(Params.TRAVEL_TIME);
                }
            } catch (Exception e) {
                this.interrupt();
            }
        }
    }



}
