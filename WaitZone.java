public class WaitZone {

    private String zoneType;
    private boolean inUse = false;
    private String shipNo;
    private String departShip = "noship";

    public WaitZone(String zoneType) {
        this.zoneType = zoneType;

    }

    public boolean checkOccupied(){
        return this.inUse;
    }

    public String getShipNo(){
        return this.shipNo;
    }

    public void arrive(Ship ship){
        System.out.println(ship.toString()+" arrives at arrival zone");
        this.inUse = true;
        this.shipNo = ship.toString();
    }

    public void depart(){
    }

    public String getDepartShip(){
        return this.departShip;
    }

    public void updateDeparture(String departShip){
        this.departShip = departShip;
    }

    public void updateOccupied(boolean inUse){
        this.inUse = inUse;
    }
}