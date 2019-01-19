/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 8, Server.java
 */
package server;

import common.ChatterboxProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    //hashMap of all currently connected users
    public static HashMap<String, Socket> connectedUsers = new HashMap<>();

    public Server(){}

    /**
     * creates the server ready to be connected to
     * @param args
     */
    public static void main(String[] args) {
        int port = ChatterboxProtocol.PORT;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(true){
                System.out.println("Waiting for connections on port " + port);
                Socket socket = serverSocket.accept();
                System.out.println("ChatterboxClient connection receives from " + socket.getRemoteSocketAddress());
                new Thread(new ServerReceiver(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds connected users to the hashMap
     * @param username a user
     * @param socket socket being used
     */
    public static void addUser(String username, Socket socket){
        connectedUsers.put(username, socket);
    }

    /**
     * retrieves the hashMap of connected users
     * @return
     */
    public static HashMap<String, Socket> getConnectedUsers(){
        return connectedUsers;
    }

}
