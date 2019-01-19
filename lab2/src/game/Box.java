/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 2, Box
 */
package game;


public class Box {
    private int row;
    private Line bottom;
    private Player owner;
    private Line top;
    private Line right;
    private Line left;
    private int column;

    //Creates the box and associates each line with the box
    public Box(int row, int column, Lines lines){


        //i gave up at this exact point and choose to stop slowly dying. please have mercy on my soul...



        //....  I now accept my fate

    }

    //attempts to claim possible boxes
    public void claim(Player owner){
        if(this.owner == Player.NONE){
            this.owner = owner;
        }
    }

    //Returns the label of the boxes owner and looks at the Player enum
    public String toString(){
        return this.owner.getLabel();
    }

    //checks if two boxes are equal if they have the same row, column, owner, & four lines
    public boolean equals(Object other) {
        if (other instanceof Box) {
            Box b = (Box) other;
            if (this.getRow() == b.getRow() && this.getColumn() == b.getColumn() && this.owner == b.getOwner() &&
                    this.top == b.getTopLine() && this.bottom == b.getBottomLine() && this.left == b.getLeftLine() &&
                    this.right == b.getRightLine()) {
                return true;
            }
        }
        return false;
    }

    //gets the column
    public int getColumn() {
        return column;
    }

    //gets the bottom line
    public Line getBottomLine() {
        return bottom;
    }

    //gets the left line
    public Line getLeftLine() {
        return left;
    }

    //gets the owner of the box
    public Player getOwner() {
        if(this.owner != Player.NONE){
            return owner;
        }
        else
            return Player.NONE;
    }

    //gets the top line
    public Line getTopLine() {
        return top;
    }

    //gets the row
    public int getRow() {
        return row;
    }

    //gets the right line
    public Line getRightLine() {
        return right;
    }
}
