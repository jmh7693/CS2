/**
 * Author: Jason Hicks
 * Language: Java
 * lab1, HTree
 */
package lab01.student;
import static turtle.Turtle.*;
import java.util.Scanner;


public class HTree {

    static int MAX_SEGMENT_LENGTH = 200; //static int for length

    /**
     * This program will recursively draw a tree diagram when ran stand alone
     * @param length: how long the first segment drawn will be
     * @param depth: how many times the drawing will be recursively called
     */
    public static void drawHTree(int length, int depth){

        if (depth > 0){
            Turtle.forward(length / 2);
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.right(90);

            drawHTree(length / 2, depth - 1);

            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            drawHTree(length / 2, depth - 1);

            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.forward(length / 2);
            Turtle.right(90);

            drawHTree(length / 2, depth - 1);

            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            drawHTree(length / 2, depth - 1);

            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.right(90);
            Turtle.forward(length / 2);
        }
    }

    /**
     * asks for a length and depth when ran stand alone, then draws recursively
     * @param args
     */
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a depth: ");
        int depth = scanner.nextInt();
        drawHTree(MAX_SEGMENT_LENGTH, depth);

    }


}
