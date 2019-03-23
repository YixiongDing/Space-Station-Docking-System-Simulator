public class WaitZone {

    private String zoneType;

    public WaitZone(String zoneType) {
        this.zoneType = zoneType;

    }

    public void arrive(Ship ship){
        System.out.println("i got a ship");
    }


    public void depart(){
        System.out.println("a ship is gone");
    }
}