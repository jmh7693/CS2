/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 8, ClientReceiver.java
 */
package client;

import common.ChatterboxProtocol;
import common.Receiver;

import java.net.Socket;

public class ClientReceiver extends Receiver implements ChatterboxProtocol {

    private static Socket currSocket;

    /**
     * creates a ClientReceiver
     * @param currSocket socket being used
     */
    public ClientReceiver(Socket currSocket){
        super(currSocket);
        this.currSocket = currSocket;
    }

    /**
     * this function handles the requests outputs on the client end
     * @param in
     */
    @Override
    public void handle(String in){
        String[] requests = in.split("::");
        System.out.println();

        switch(requests[0]){
            case CONNECTED:
                break;

            case DISCONNECTED:
                System.out.println("Good Bye!");
                break;

            case CHAT_RECEIVED:
                System.out.println(requests[1] + " said: " + requests[2]);
                break;

            case WHISPER_RECEIVED:
                System.out.println(requests[1] + " whispers to you: " + requests[2]);
                break;

            case WHISPER_SENT:
                break;

            case USERS:
                System.out.println("The following users are connected: ");
                for(int idx = 1; idx < requests.length; idx++){
                    System.out.println(requests[idx]);
                }
                break;

            case USER_JOINED:
                if (!requests[1].equals(Client.username))
                    System.out.println("A user has joined the Chatterbox Server: " + requests[1]);
                break;

            case USER_LEFT:
                System.out.println("A user has left the Chatterbox Server: " + requests[1]);
                break;

            case ERROR:
                System.out.println("ERROR");
                break;

            case FATAL_ERROR:
                System.out.println("FATAL ERROR");
                System.exit(1);
                break;

            default:
                System.out.println("Input not valid.");
                break;
        }
    }

}
