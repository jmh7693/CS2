/**
 * Author: Jason Hicks
 * Language: Java
 * lab1, GoodHashFunc
 */
package lab01.student;
import java.util.Scanner;


public class GoodHashFunc {

    /**
     * This function will compute the sum of the hash values in a given String
     * @param input: a String value being inputted
     * @return: int
     */
    public static int computeHash(String input){
        int[] hashArray = new int[input.length()];

        for (int i = 0; i < input.length(); i++){
            hashArray[i] = input.charAt(i)*(int)Math.pow(31, input.length() - (i+1));
        }
        int ind = 0;
        int sumOfHash = 0;

        while(ind < input.length()){
            sumOfHash += hashArray[ind];
            ind++;
        }
        return sumOfHash;
    }

    /**
     *
     * This class can be ran stand alone
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scan.nextLine();
        System.out.println(computeHash(input));




    }

}


