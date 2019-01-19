/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 5, TollRecord.java
 */

/* A few useful items are provided to you. You must write the rest. */

public class TollRecord implements Comparable<TollRecord>{

    /**
     * For printing toll records in reports
     * using {@link String#format(String, Object...)}
     */
    public static final String TOLL_RECORD_FORMAT = "[%11s] on #%2d, time %5d";
    public static final String OFF_FORMAT = "; off #%2d, time %5d";

    /**
     * Value of uninitialized integer fields in this record
     */
    public static final int UNINITIALIZED = -1;

    public String tag;
    public int onExit;
    public int onTime;
    public int offExit = UNINITIALIZED;
    public int offTime = UNINITIALIZED;

    /**
     * Create a new TollRecord given the tag of the
     * vehicle and incoming exit number and time.
     * @param tag license plate number
     * @param onExit the toll number entered on
     * @param onTime the time entered the onExit
     */
    public TollRecord(String tag, int onExit, int onTime){
        this.tag = tag;
        this.onExit = onExit;
        this.onTime = onTime;
    }

    /**
     * Record the exit number and time at which this vehicle left the highway
     * @param offExit the toll number exited on
     * @param offTime the time exited the offExit
     */
    public void setOffExit(int offExit, int offTime){
        this.offExit = offExit;
        this.offTime = offTime;
    }

    /**
     * returns true if the customer is still on the road and false if they are not
     * @return boolean
     */
    public boolean isOnRoad(){
        if(offExit == UNINITIALIZED){
            return true;
        }else{
            return false;
        }
    }

    /**
     * returns the tag String
     * @return tag (String)
     */
    public String getTag(){
        return tag;
    }

    /**
     * returns the onExit int
     * @return onExit (int)
     */
    public int getOnExit(){
        return onExit;
    }

    /**
     * returns the time entered the first toll
     * @return onTime (int)
     */
    public int getOnTime(){
        return onTime;
    }

    /**
     * returns the offExit int
     * @return offExit (int)
     */
    public int getOffExit(){
        return offExit;
    }

    /**
     * returns the time exited the last toll
     * @return offTime (int)
     */
    public int getOffTime(){
        return offTime;
    }

    /**
     * gets the total coast for the tolls used  for a specific car
     */
    public double getFare(){
        return TollSchedule.getFare(onExit, offExit);
    }

    /**
     * compares TollRecord objects as equal or not
     * @param o the object
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof TollRecord){
            TollRecord R = (TollRecord)o;
            return R.tag.equals(this.tag);
        }
        return false;
    }

    /**
     * compares the tag String of TollRecord objects
     * @param o the object
     * @return int
     */
    public int compareTo(TollRecord o){
        return this.tag.compareTo(o.tag);
    }

    /**
     * the string representation of a customers record
     * @return String
     */
    @Override
    public String toString(){
        return "[" + tag + "] on #" + onExit + ", time   " + onTime + "; off #" + offExit + ", time   " + offTime;
    }

    /**
     * the string representation of a customer's report based on whether they are on or off the toll road
     * @return String
     */
    public String report(){
        if(offExit == UNINITIALIZED) {
            return "[" + tag + "] on #" + onExit + ", time   " + onTime;
        } else {
            return "[" + tag + "] on #" + onExit + ", time   " + onTime + "; off #" + offExit + ", time   " + offTime;
        }
    }

    /**
     * calculates a hashCode
     * @return int
     */
    @Override
    public int hashCode(){
        int hashCode = 420;
        hashCode = hashCode * 69 + Integer.parseInt(tag);
        return hashCode + 1 + 2 + 4 + 8 + offTime * 2;
    }

    public void main(String[] args){

    }

}
