public class Operator extends  Thread {
    // the wait zone at which ships will arrive
    private Berth berth;

    // create a new producer
    Operator(Berth berth) {
        this.berth = berth;
    }

    // cargo ships arrive at the arrival zone at random intervals.
    public void run() {
        while(!isInterrupted()) {
            try {

            } catch (Exception e) {
                this.interrupt();
            }
        }
    }
}
