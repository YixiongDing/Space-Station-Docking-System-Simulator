public class Berth {

    private String berthType;
    private boolean shieldOn = false;
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
