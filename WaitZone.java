public class WaitZone {

    private String zoneType;
    private boolean inUse = false;
    private String shipNo;
    private String departShip = "noship";
    private boolean needPiolt = true;

    public WaitZone(String zoneType) {
        this.zoneType = zoneType;

    }

    public synchronized boolean checkOccupied(){
        return this.inUse;
    }

    public synchronized String getShipNo(){
        return this.shipNo;
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