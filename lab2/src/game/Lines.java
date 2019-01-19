/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 2, Lines
 */
package game;
import java.util.*;

public class Lines {
    private ArrayList<Line> lines = new ArrayList<>();

    /**
     * This will construct a line array list from the dot array list
     * @param rows row coordinates
     * @param columns column coordinates
     * @param dots points on the grid
     */
    public Lines(int rows, int columns, Dot[][] dots) {

        for (int row = 0; row <= rows; ++row) {
            for (int column = 0; column <= columns; ++column) {
                if (column != columns) {
                    lines.add(new Line(dots[row][column], dots[row][column + 1]));
                }
                if (row != rows) {
                    lines.add(new Line(dots[row][column], dots[row + 1][column]));
                }
            }
        }
    }

    /**
     * The function will locate the inputted line from the array list of lines
     * @param row1 The first row coordinate
     * @param column1 the first column coordinate
     * @param row2 the second row coordinate
     * @param column2 the second column coordinate
     * @return
     */
    public Line getLine(int row1, int column1, int row2, int column2) {
        Dot firstDot = new Dot(row1, column1);
        Dot secondDot = new Dot(row2, column2);

        if (row2 >= row1 && column2 >= column1) {
            Line line = new Line(firstDot, secondDot);
            return line;
        } else {
            return null;
        }
    }

    /**
     *
     * @return the size of the lines array list
     */
    public int size() {
        return lines.size();
    }

}
