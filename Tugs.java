public class Tugs {

    private int tugsNum;
    private int availableTugs;

    public Tugs(int tugsNum) {
        this.tugsNum = tugsNum;
        this.availableTugs = tugsNum;
    }

    public synchronized int checkAvailableTugs(){
        return this.availableTugs;
    }

    public synchronized void updateAvailableTugs(int availableTugs){
        this.availableTugs = availableTugs;
    }

}