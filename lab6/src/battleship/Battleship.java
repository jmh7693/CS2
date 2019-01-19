/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, Battleship.java
 */
package battleship;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Battleship {

    public static final String BAD_ARG_COUNT = "Wring number of arguments for command";
    public static final String DIM_TOO_BIG = "Board dimensions too big to display.";
    public static final String MISSING_SETUP_FILE = "No setup file specified.";
    public static final String BAD_CONFIG_FILE = "Malformed board text file";
    public static final String ALL_SHIPS_SUNK = "All ships sunk!";
    public static final String WHITESPACE = "\\s+";
    public static final String PROMPT = "> ";
    public static final int MAX_DIM = 20;
    static Board board;

    public static void main(String[] args) throws OverlapException, OutOfBoundsException, NumberFormatException, CellPlayedException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "-7";

        boolean loaded = makeGameboard(args);
        if (loaded) {
            board.display(new PrintStream(System.out));

            while(!Objects.equals(input, "q")){
                displayPrompt();
                try {
                    input = br.readLine();
                    char menu = input.charAt(0);
                    switch (menu) {
                        case 'h':
                            String[] inputArgs = input.split(" ");
                            if (inputArgs.length == 3) {
                                board.getCell(Integer.parseInt(inputArgs[1]), Integer.parseInt(inputArgs[2])).hit();

                                if (board.allSunk()) {
                                    System.out.println(ALL_SHIPS_SUNK);
                                    input = "q";
                                } else {
                                    board.display(new PrintStream(System.out));
                                }
                            } else
                                System.out.println(BAD_ARG_COUNT);
                            break;
                        case 's':
                            saveGame(args[0]);
                            break;
                        case 'q':
                            break;
                        case '!':
                            board.fullDisplay(new PrintStream(System.out));
                            break;
                        default:
                            break;
                    }
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * makes a gameboard for battleship
     * @param args
     * @return
     * @throws OverlapException
     * @throws OutOfBoundsException
     */
    public static boolean makeGameboard(String[] args) throws OverlapException, OutOfBoundsException{
        boolean init = true;

        if (args.length >= 1){
            try
            {
                String file = args[0];
                if(new File(file).exists()){
                    System.out.print("Checking if " + args[0] + " is a saved game file...");

                    if (file.split(Pattern.quote("."))[1].equals("txt")){
                        System.out.print("no; will read as a text setup file.\n");
                        BufferedReader bufferReader = new BufferedReader(new FileReader(file));
                        String line = bufferReader.readLine();
                        String[] line1 = line.split(" ");

                        if (Integer.parseInt(line1[0]) <= MAX_DIM && Integer.parseInt(line1[1]) <= MAX_DIM){
                            board = new Board(Integer.parseInt(line1[0]), Integer.parseInt(line1[1]));
                            board.loadBoard(bufferReader);
                        }else{
                            System.out.println(DIM_TOO_BIG);
                            init = false;
                        }
                    }else if (file.split(Pattern.quote("."))[1].equals("bin")){
                        System.out.println("yes; will read as a saved file.\n");
                        resumeGame(file);
                    }else{
                        System.out.print("Unrecognized File Type!");
                        init = false;
                    }
                }else{
                    System.out.println(BAD_CONFIG_FILE);
                    init = false;
                }
            }catch (IOException e){
                System.out.println(BAD_CONFIG_FILE);
                init = false;
            }
        }else if (args.length == 0){
            init = false;
            System.out.println(MISSING_SETUP_FILE);
        }else{
            init = false;
            System.out.println(BAD_ARG_COUNT);
        }
        return init;
    }

   

    private static void displayPrompt(){
        System.out.print("\nEnter Command <h, s, q, !>: ");
    }

    /**
     * saves a gameboard object into a file to be resumed
     * @param file
     */
    public static void saveGame(String file){
        try{
            ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(file.split(Pattern.quote("."))[0]+".bin"));
            objOutStream.writeObject(board);
            objOutStream.close();
        }
        catch (IOException io){
            io.printStackTrace();
        }
        System.out.println("Saved File Location: " + file.split(Pattern.quote("."))[0]+".bin");
    }

    /**
     * loads a board that has been saved into a file
     * @param file
     */
    public static void resumeGame(String file){
        try{
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(file));
            Board savedBoard;
            try{
                board = (Board) objInStream.readObject();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        catch (IOException  e){
            e.printStackTrace();
        }
    }
}
