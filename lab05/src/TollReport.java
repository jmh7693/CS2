import java.util.*;
/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 5, TollReport.java
 */
public class TollReport {

    /**
     * reads through the given files and returns a total interactive customer toll report
     * @param args
     */
    public static void main(String[] args) {
        TollRoadDatabase tr= new TollRoadDatabase(args[0]);
        tr.summaryReport();
        tr.onRoadReport();
        tr.speederReport();
        tr.printBills();

        Scanner scan = new Scanner(System.in);
        String input = "-1";

        while(input.charAt(0) != 'q'){
            System.out.println("\n'b <string>' to see bill for license tag\n'e " +
                    "<number>' to see activity at exit\n'q' to quit");
            input = scan.nextLine();
            String[] inputArr = input.split(" ");
            if(input.charAt(0) != 'q'){
                if(input.charAt(0) == 'b'){
                    tr.printCustSummary(inputArr[1]);
                }
                else if(input.charAt(0) == 'e'){
                    tr.printExitActivity(Integer.parseInt(inputArr[1]));
                }
            }
        }
    }

}
