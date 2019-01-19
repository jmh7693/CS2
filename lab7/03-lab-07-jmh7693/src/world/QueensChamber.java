package world;

import bee.Drone;
import bee.Queen;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The queen's chamber is where the mating ritual between the queen and her
 * drones is conducted.  The drones will enter the chamber in order.
 * If the queen is ready and a drone is in here, the first drone will
 * be summoned and mate with the queen.  Otherwise the drone has to wait.
 * After a drone mates they perish, which is why there is no routine
 * for exiting (like with the worker bees and the flower field).
 *
 * @author Sean Strout @ RIT CS
 * @author Jason Hicks
 */
public class QueensChamber {

    private ConcurrentLinkedQueue<Drone> droneTrain;
    private BeeHive hive;

    /**
     * creates a chamber.
     * no drones.
     * queen is not ready to mate.
     */
    public QueensChamber(){
        droneTrain = new ConcurrentLinkedQueue<>();
    }

//    /**
//     * determines if the queen is ready to mate or not
//     * @return boolean
//     */
//    public boolean isHorny(){
//        if(hive.hasResources() && !droneTrain.isEmpty()){
//            return true;
//        }else
//            return false;
//    }

    /**
     * simulates what occurs when a drone enters the queens chamber ;)
     * @param drone bee.Drone object entering the chamber
     */
    public synchronized void enterChamber(Drone drone){
        System.out.println("*QC* " + drone + " enters chamber");

        droneTrain.add(drone);
        if(drone.equals(droneTrain.peek()) && Queen.isHorny){
                droneTrain.remove(drone);
                drone.setMated();
        }else{
            try {
                    this.wait(Queen.MATE_TIME_MS);
                    //Queen.isHorny = true;
                    this.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("*QC* " + drone + " leaves chamber");
    }

    /**
     * The Queen is ready.
     * She must summon the next drone from t he collection.
     * (if at least one is there)
     */
    public synchronized void summonDrone(){
        if(!droneTrain.isEmpty()){
            Drone drone = droneTrain.poll();
            System.out.println("*QC* Queen mates with " + drone);
            droneTrain.notifyAll();
        }
    }

    /**
     * Dismiss all drones ready to mate. routinely...
     */
    public synchronized void dismissDrone(){
        for(Drone drone : droneTrain){
            droneTrain.remove(drone);
        }
    }

    /**
     * checks if their are any drones ready to mate in the collection
     */
    public synchronized boolean hasDrone(){
        if(!droneTrain.isEmpty()){
            return true;
        }else
            return false;
    }
    
}