/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 8, Transmitter.java
 */
package common;

import server.Server;

import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;

public class Transmitter implements ChatterboxProtocol {

    /**
     * sends a request to the server
     * @param request
     * @param socket
     */
    public static void transmit(String request, Socket socket){
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends all requests to the server
     * @param request
     */
    public static void transmitAll(String request){
        try {
            for(Socket val : Server.getConnectedUsers().values()) {
                PrintWriter pw = new PrintWriter(val.getOutputStream(), true);
                pw.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /////////////client/////////////client////////////client///////////////////client/////////////client///////////////

    public static String connect(String username){
        return CONNECT + SEPARATOR + username;
    }

    public static String list_users(){
        return LIST_USERS + SEPARATOR;
    }

    public static String disconnect(){
        return DISCONNECT;
    }

    public static String send_chat(String msg){
        return SEND_CHAT + SEPARATOR + msg;
    }

    public static String send_whisper(String msg, String user){
        return SEND_WHISPER + SEPARATOR + user + SEPARATOR + msg;
    }

    ////////////server///////////////server//////////////server//////////////////////server/////////////server/////////

    public static String connected(){
        return CONNECTED + SEPARATOR;
    }

    public static String disconnected(){
        return DISCONNECTED;
    }

    public static String chat_received(String from, String msg){
        return CHAT_RECEIVED + SEPARATOR + from + SEPARATOR + msg;
    }

    public static String whisper_received(String username, String msg){
        return WHISPER_RECEIVED + SEPARATOR + username + SEPARATOR + msg;
    }

    public static String users(){
        String request = USERS;
        for(String key : Server.getConnectedUsers().keySet()){
            request += SEPARATOR + key;
        }
        return request;
    }

    public static String user_joined(String username){
        return USER_JOINED + SEPARATOR + username;
    }

    public static String user_left(String username){
        return USER_LEFT + SEPARATOR + username;
    }

    public static String error(){
        return FATAL_ERROR;
    }
}
