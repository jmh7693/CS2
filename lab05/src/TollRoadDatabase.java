import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 5, TollRoadDatabase.java
 */

/* A few useful items are provided to you. You must write the rest. */

public class TollRoadDatabase {
    /**
     * For printing floating point values in dollar/cents format. Example:
     * System.out.println( String.format( DOLLAR_FORMAT, 10.5 );  // $10.50
     */
    private static final String DOLLAR_FORMAT = "$%5.2f";
    private static final String SPEED_FORMAT = "%5.1f MpH";

    /**
     * Universal new line
     */
    private static final String NL = System.lineSeparator();

    /**
     * Conversion constant from minutes to hours
     */
    public static final double MINUTES_PER_HOUR = 60.0;

    /**
     * This toll road's speed limit, in miles per hour
     */
    public static final double SPEED_LIMIT = 65.0;

    private HashMap<String, ArrayList<TollRecord>> recordsMap = new HashMap<>();

    private int trips = 0;

    /**
     * This method reads the event file and build all data structures
     * @param eventFileName the file(s) being used
     */
    public TollRoadDatabase(String eventFileName) {
        try {
            Scanner scan = new Scanner(new File(eventFileName));
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split(",");
                if (recordsMap.containsKey(line[1])) {
                    ArrayList<TollRecord> records = recordsMap.get(line[1]);
                    TollRecord tollRecord = records.get(records.size() - 1);
                    if (tollRecord.isOnRoad()) {
                        tollRecord.setOffExit(Integer.parseInt(line[2]), Integer.parseInt(line[0]));
                        records.set(records.size() - 1, tollRecord);
                        recordsMap.put(line[1], records);
                        trips++;
                    } else {
                        records.add(new TollRecord(line[1], Integer.parseInt(line[2]), Integer.parseInt(line[0])));
                        recordsMap.put(line[1], records);
                    }
                } else {
                    ArrayList<TollRecord> records = new ArrayList<>();
                    records.add(new TollRecord(line[1], Integer.parseInt(line[2]), Integer.parseInt(line[0])));
                    recordsMap.put(line[1], records);
                }
            }
        } catch (FileNotFoundException obj) {
            System.out.println("This is not a valid file.");
            obj.printStackTrace();
        }
    }

    /**
     * prints out a report listing the vehicles that are still on the toll road.
     * They are printed in order based on license tag.
     */
    public void onRoadReport(){
        System.out.println("On-Road Report");
        System.out.println("==============");
        for(Map.Entry<String, ArrayList<TollRecord>> recordMap : recordsMap.entrySet()){
            String key = recordMap.getKey();
            ArrayList<TollRecord> val = recordMap.getValue();

            if (val.get(0).isOnRoad())
            {
                for(int idx = 0; idx < val.size(); idx++){
                    System.out.println(String.format(TollRecord.TOLL_RECORD_FORMAT, key, val.get(idx).onExit, val.get(idx).onTime));
                }
            }


        }
    }

    /**
     * prints out the number of completed trips
     */
    public void summaryReport(){
        System.out.println(trips + "completed trips");
    }

    /**
     * lists cars that are going above the speed limit (65mph)
     */
    public void speederReport(){
        System.out.println("\nSPEEDER REPORT" + "\n==============");
        for(Map.Entry<String, ArrayList<TollRecord>> recordMap : recordsMap.entrySet()){
            String key = recordMap.getKey();
            ArrayList<TollRecord> val = recordMap.getValue();
            for(int idx = 0; idx < val.size(); idx++){
                TollRecord tollRecord = val.get(idx);
                if(!(tollRecord.isOnRoad())) {
                    double speed = (TollSchedule.getLocation(tollRecord.onExit) - TollSchedule.getLocation
                            (tollRecord.offExit)) / ((tollRecord.offTime - tollRecord.onTime) / MINUTES_PER_HOUR);
                    if (speed > SPEED_LIMIT) {
                        System.out.println("Vehicle " + key + ", starting at time " + tollRecord.onTime);
                        System.out.println("\tfrom" + TollSchedule.getInterchange(tollRecord.onExit));
                        System.out.println("\tto" + TollSchedule.getInterchange(tollRecord.offExit));
                        System.out.println("\t" + String.format(SPEED_FORMAT,speed));
                    }
                }
            }
        }
    }

    /**
     * prints the summary information for a single customer, specified by license tag.
     * A total due is printed at the end
     * @param tag the license plate tag linked to a specific customer
     */
    public void printCustSummary(String tag){
        double cost = 0;
        ArrayList<TollRecord> records = recordsMap.get(tag);
        for(int idx = 0; idx < records.size(); idx++){
            if(!(records.get(idx).isOnRoad())){
                System.out.print(String.format(TollRecord.TOLL_RECORD_FORMAT,
                        tag, records.get(idx).onExit, records.get(idx).onTime));
                System.out.print(String.format(TollRecord.OFF_FORMAT,
                        records.get(idx).offExit, records.get(idx).offTime) + " ");
                System.out.print(String.format(DOLLAR_FORMAT,
                        TollSchedule.getFare(records.get(idx).onExit, records.get(idx).offExit)));
                cost += TollSchedule.getFare(records.get(idx).onExit, records.get(idx).offExit);
            }
        }
    }

    /**
     * prints out the billing report for the vehicles that completed trips on the toll road
     */
    public void printBills(){
        double cost = 0;
        double totalCost = 0;
        System.out.println("\nBILLING REPORT" + "\n==============");
        for(Map.Entry<String, ArrayList<TollRecord>> recordMap : recordsMap.entrySet()){
            String key = recordMap.getKey();
            ArrayList<TollRecord> val = recordMap.getValue();
            for(int idx = 0; idx < val.size(); idx++){
                if(!(val.get(idx).isOnRoad())){
                    System.out.println(val.get(idx).report() + String.format(DOLLAR_FORMAT, val.get(idx).getFare() + cost));
                    totalCost = totalCost + val.get(idx).getFare();
                }
            }
        }
        System.out.println("Total: " + String.format(DOLLAR_FORMAT, totalCost));
    }

    /**
     * prints all toll records that include a specific exit as their on or off point
     * @param exit the exit toll
     */
    void printExitActivity(int exit) {
        System.out.println("\nExit " + exit + " Report\n==========");
        ArrayList<TollRecord> allGucciRecords = new ArrayList<>();
        ArrayList<TollRecord> notGucciRecords = new ArrayList<>();
        for (Map.Entry<String, ArrayList<TollRecord>> recordMap : recordsMap.entrySet()) {
            String key = recordMap.getKey();
            ArrayList<TollRecord> val = recordMap.getValue();
            for(int idx = 0; idx < val.size(); idx++) {
                TollRecord record = val.get(idx);
                if(record.onExit == exit || record.offExit == exit){
                    if(record.isOnRoad()){
                        allGucciRecords.add(record);
                    }else{
                        notGucciRecords.add(record);
                    }
                }
            }
        }
        for(TollRecord record : allGucciRecords){
            System.out.println(record.report());
        }
        for(TollRecord record : notGucciRecords){
            System.out.println(record.report());
        }
    }

}
