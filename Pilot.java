public class Pilot extends Thread{

    private int pilotNo;
    private WaitZone arrivalZone;
    private WaitZone departureZone;
    private Tugs tugs;
    private Berth berth;

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

            } catch (Exception e) {
                this.interrupt();
            }
        }
    }

}
