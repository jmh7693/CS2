/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, OutOfBoundsException.java
 */
package battleship;

public class OutOfBoundsException extends BattleshipException{

    public static final String PAST_EDGE = "Oh no! You have gone off the edge of the board.";

    /**
     * A BattleshipException that informs the program that it attempted to
     * place a ship outside the bounds of the board
     * @param row int
     * @param column int
     */
    public OutOfBoundsException(int row, int column){
        super(row, column, PAST_EDGE);
    }
}
