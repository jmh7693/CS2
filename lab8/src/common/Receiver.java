/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 8, Receiver.java
 */
package common;

import java.io.IOException;
import java.util.Scanner;
import java.net.Socket;

public abstract class Receiver implements Runnable{
    public Socket currSocket;
    private Scanner scan;

    /**
     * retrieves commands from the client end from a specific socket
     * @param currSocket socket being used
     */
    public Receiver(Socket currSocket){
        this.currSocket = currSocket;
        {
            try {
                scan = new Scanner(currSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * always searching for input commands
     */
    @Override
    public void run(){
        while(true){
            if(scan.hasNextLine()){
                handle(scan.nextLine());
            }
        }
    }

    /**
     * abstract method used in ClientReceiver and ServerReceiver
     * @param in input
     */
    public abstract void handle(String in);

}
