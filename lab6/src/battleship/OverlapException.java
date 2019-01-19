/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, OverlapException.java
 */
package battleship;

public class OverlapException extends BattleshipException{

    public static final String OVERLAP = "The ships are overlapping.";

    /**
     * A BattleshipException that informs the program that it
     * attempted to place a ship where there is already another ship
     * @param row int
     * @param column int
     */
    public OverlapException(int row, int column){
        super(row, column, OVERLAP);
    }
}
