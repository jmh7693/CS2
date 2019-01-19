package game;

//This is the main class for representing and playing the Dots And Boxes game
public class GameBoard {
    private int blueScore;
    private int redScore;
    private int columns;
    private int rows;
    private Box[][] boxes;
    private Dot[][] dots;
    private Lines lines;
    private int moves;
    private Player player;

    //creates the game board
    public GameBoard(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
    }

    //occurs when the number of lines claimed equals the number of moves made
    public boolean gameOver(){
        return false;
    }

    //returns the player whose turn it is
    public Player whoseTurn(){
        return player;
    }

    //makes sure that the line exists and is unclaimed
    public boolean isLineValid(int row1, int column1, int row2, int column2){
        return false;
    }

    //places the line for a player for their move if the line is valid to claim
    public void makeMove(int row1, int column1, int row2, int column2){

    }

    //returns a string representation of the game board
    public String toString(){
        return "";
    }


}
