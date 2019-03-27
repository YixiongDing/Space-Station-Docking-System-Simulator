/**
 * Yixiong Ding, 671499
 * 26 March, 2019
 * Assignment 1a, 2019
 * SWEN90004 Modelling Complex Software Systems
 * The University of Melbourne
 * */

/* Periodically activates the shield to protect the space station from space debris */
public class Operator extends Thread {

    private  Berth berth;

    Operator(Berth berth) {
        this.berth = berth;
    }


    public void run() {
        while(!isInterrupted()) {
            try {

                // Let some time pass before the next debris
                sleep(Params.debrisLapse());

                // Activate the shield
                berth.operateShield(true);
                System.out.println("Shield is activated.");

                // Deactivate the shield when the debris is over
                sleep(Params.DEBRIS_TIME);
                berth.operateShield(false);
                System.out.println("Shield is deactivated.");

            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
