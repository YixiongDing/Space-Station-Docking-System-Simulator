public class Operator extends  Thread {

    private Berth berth;


    Operator(Berth berth) {
        this.berth = berth;
    }


    public void run() {
        while(!isInterrupted()) {
            try {

                if(berth.isActive()){
                    System.out.println("Shield is activated.");
                }else{
                    System.out.println("Shield is deactivated.");
                }

                sleep(Params.debrisLapse());
                berth.operateShield(!berth.isActive());

            } catch (Exception e) {
                this.interrupt();
            }
        }
    }
}
