/**
 * Author: Jason Hicks
 * Language: Java
 * lab1, PrimalityTest
 */
package lab01.student;


public class PrimalityTest {

    /**
     *
     * when ran stand alone, the user inputs int values to calculate primality using the isPrime function. 0 to quit.
     */
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.print("Enter number(Enter 0 to quit): ");
        int number = scan.nextInt();

        while (number > 0) {

            boolean isItPrime = isPrime(number);
            System.out.println(number + " is prime. " + isItPrime);
            System.out.println();
            System.out.print("Enter Number(Enter 0 to quit): ");
            number = scan.nextInt();
        }
        if (number <= 0){
            System.out.println("Goodbye!");
        }
    }

    /**
     * This is the function that deciphers whether a number is in fact prime or not
     * @param number: The int being inputted
     * @return: boolean
     */
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int x = 3; x * x <= number; x += 2) {
            if (number % x == 0) {
                return false;
            }
        }
        return (true);
    }

}

