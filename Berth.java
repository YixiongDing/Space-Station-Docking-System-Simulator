public class Berth {

    private String berthType;
    private boolean shieldOn = false;
    private boolean inUse = false;

    public Berth(String berthType) {
        this.berthType = berthType;
    }

    public boolean isActive(){
        return this.shieldOn;
    }

    public boolean checkOccupied(){
        return this.inUse;
    }

    public void operateShield(boolean shieldOn){
        this.shieldOn = shieldOn;
    }

    public void updateOccupied(boolean inUse){
        this.inUse = inUse;
    }


}
