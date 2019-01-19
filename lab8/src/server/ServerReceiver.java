/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 8, ServerReceiver.java
 */
package server;

import common.ChatterboxProtocol;
import common.Receiver;
import common.Transmitter;

import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Receiver implements ChatterboxProtocol{

    String username = "";

    /**
     * creates a new ServerReceiver
     * @param currSocket
     */
    public ServerReceiver(Socket currSocket){
        super(currSocket);
    }

    /**
     * this function handles the requests outputs on the server end
     * @param in
     */
    @Override
    public void handle(String in){
        String[] requests = in.split("::");
        
        if(CONNECT.equals(requests[0])) {
            System.out.println("<< unknown user" + SEPARATOR + in);
            username = requests[1];
            Server.addUser(username, currSocket);
            Transmitter.transmit(Transmitter.connected(), super.currSocket);
            System.out.println(">> " + CONNECTED + SEPARATOR + requests[1]); 
        }else {
            if (Server.connectedUsers.containsKey(username)) {
                switch (requests[0]){
                    case DISCONNECT:
                        System.out.println("<< " + username + SEPARATOR + in);
                        try {
                            Server.connectedUsers.remove(username);
                            Transmitter.transmit(Transmitter.disconnected(), currSocket);
                            currSocket.close();
                            Transmitter.transmitAll(Transmitter.user_left(username));
                            System.out.println(">> " + DISCONNECTED + SEPARATOR + username);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case SEND_CHAT:
                        System.out.println("<< " + username + SEPARATOR + in);
                        for (String name : Server.getConnectedUsers().keySet()) {
                            Transmitter.transmit(Transmitter.chat_received(username, requests[1]),
                                    Server.getConnectedUsers().get(name));
                            System.out.println(">> " + username + SEPARATOR + CHAT_RECEIVED +
                                    SEPARATOR + name + SEPARATOR + requests[1]);
                        }
                        break;

                    case SEND_WHISPER:
                        System.out.println("<< " + username + SEPARATOR + in);
                        Transmitter.transmit(Transmitter.whisper_received
                                (username, requests[2]), Server.getConnectedUsers().get(requests[1]));
                        System.out.println(">> " + username + SEPARATOR + WHISPER_RECEIVED + SEPARATOR
                                + requests[1] + SEPARATOR + requests[2]);
                        break;

                    case LIST_USERS:
                        System.out.println("<< " + username + SEPARATOR + in);
                        String userRequest = Transmitter.users();
                        Transmitter.transmit(userRequest, super.currSocket);
                        System.out.println(">> " + username + userRequest);
                        break;

                    default:
                        break;
                }
            }else{
                Transmitter.transmit(Transmitter.error(), currSocket);
            }
        } 
    } 
    
}

