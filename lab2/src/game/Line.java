/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 2, Line
 */
package game;
import java.util.*;


public class Line {
    public static final String EMPTY = " ";
    public static final String HORI_LINE = "|";
    public static final String VERT_LINE = "-";
    private ArrayList <Box> boxes;
    private Dot first;
    private Player owner;
    private Dot second;

    //Creates the line
    public Line (Dot first, Dot second){
        this.first = first;
        this.second = second;
        this.owner = Player.NONE;
        int rowCheck = first.getRow() - second.getRow();
        int columnCheck = first.getColumn() - second.getColumn();
        assert((rowCheck == 0 && columnCheck == -1) || (rowCheck == -1 && columnCheck == 0));
    }

    //Check if the line has been claimed.
    public boolean hasOwner(){
        return this.owner != Player.NONE;
    }

    //Claim a line, and possibly also claim associated boxes.
    public void claim(Player owner){
        if(this.owner == Player.NONE){
            this.owner = owner;
        }

    }

    /**
     * returns the string representation of the line
     * @return
     */
    @Override
    public String toString(){
        if(this.owner == Player.NONE) {
            return EMPTY;
        }

        int column = first.getColumn() - second.getColumn();
        int row = first.getRow() - second.getRow();

        if(row == -1 && column == 0){
            return HORI_LINE;
        }
        else if(row == 0 && column == -1){
            return VERT_LINE;
        }
        else
            return EMPTY;
    }

    //finds if two lines first and second dots are equal
    @Override
    public boolean equals(Object other){
        if(other instanceof Line){
            Line l = (Line) other;
            if(this.getFirst().equals(l.getFirst()) && this.getSecond().equals(l.getSecond())){
                return true;
            }
        }
        return false;
    }

    //return all boxes from a box array list
    public ArrayList <Box> getBoxes(){
        return boxes;
    }

    //get the first coordinate
    public Dot getFirst() {
        return first;
    }

    //get the second coordinate
    public Dot getSecond() {
        return second;
    }

    //gets the owner of the line
    public Player getOwner() {
        return owner;
    }

    //set a line with a box it is associated with
    public void setBox(Box box){
        this.boxes.add(box);
    }

}
