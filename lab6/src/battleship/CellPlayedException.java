/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, CellPlayedException.java
 */
package battleship;

public class CellPlayedException extends BattleshipException{

    public static final String ALREADY_HIT = "This cell is already hit";

    /**
     * A BattleshipException that informs the program that it attempted to
     * "hit" the same Cell instance more than once
     * @param row int
     * @param column int
     */
    public CellPlayedException(int row, int column){
        super(row, column, ALREADY_HIT);
    }
}