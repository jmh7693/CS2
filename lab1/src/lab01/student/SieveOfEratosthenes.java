/**
 * Author: Jason Hicks
 * Language: Java
 * lab1, SieveOfEratosthenes
 */
package lab01.student;
import java.util.Arrays;
import java.util.Scanner;


public class SieveOfEratosthenes {
    /**
     * This function will run through a given number of ints to determine primality of an input
     * @param upperBound: how large of a truth table to be constructed and tested through
     * @return: []
     */
    public static int[] makeSieve(int upperBound) {
        int[] sieveArr = new int[upperBound];
        Arrays.fill(sieveArr, 0);
        sieveArr[0] = 1;
        sieveArr[1] = 1;

        for(int i = 2; i < upperBound; i++) {
            if (sieveArr[i] == 0) {
                for (int j = i + 1; j < upperBound; j++) {
                    if (j % i == 0) {
                        sieveArr[j] = 1;
                    }
                }
            }
        }
        return sieveArr;
    }

    /**
     * When ran stand alone, the user will input an upper bound for the truth table and an int that
     * will be found in the truth table. If the value is 0, the int is prime, else (0), it's not prime.
     * @param args
     */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an upper bound: ");
        int upperBound = scan.nextInt();
        int[] sieveArr = makeSieve(upperBound);
        int num = 1;

        while(num > 0){
            System.out.print("Enter a number (input 0 to quit): ");
            num = scan.nextInt();
            if (num != 0){
                System.out.print(num);
                if (sieveArr[num] == 0){
                    System.out.println(" is prime!");
                    System.out.println();
                }
                else{
                    System.out.println(" is not prime.");
                    System.out.println();
                }
            }
        }
        if (num <= 0){
            System.out.println("Goodbye!");
        }
    }
}
