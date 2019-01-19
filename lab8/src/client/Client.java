/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 8, Client.java
 */
package client;

import common.*;

import java.io.IOException;
import java.util.Scanner;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

public class Client implements ChatterboxProtocol{
    public static String username = "-7";
    public static Socket cs;

    /**
     * creates the Client
     * @param username a user
     */
    public Client(String username){
        this.username = username;
    }

    /**
     * creates the client end of a server and holds all of its commands
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Chatterbox server host(enter 'L' for local host): ");
        String host = scan.nextLine();
        host = (host.equals("L"))? "localhost": host;

        System.out.print("Chatterbox server port: ");
        String input = scan.nextLine();
        int port = Integer.parseInt(input);

        System.out.print("Enter username: ");
        username = scan.nextLine();

        System.out.println("welcome to Chatterbox! Type '/help' to see a list of commands.\n");
        connect(host, port);

        String command = "";
        while(!command.equals("/quit")){
            command = scan.nextLine();
            String[] splitCommands = command.split(" ");

            switch(splitCommands[0]){
                case "/list":
                    Transmitter.transmit(Transmitter.list_users(), cs);
                    break;

                case "/c":
                    try {
                        Transmitter.transmit(Transmitter.send_chat(command.substring(3)), cs);
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/w":
                    try {
                        Transmitter.transmit(Transmitter.send_whisper(command.substring(4 + splitCommands[1].length()), splitCommands[1]), cs);
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/help":
                    System.out.println("/help - displays this message");
                    System.out.println("/quit - quit Chatterbox");
                    System.out.println("/c <message> - send a message to all currently connected users");
                    System.out.println("/w <recipient> <message> - send a private message to the recipient");
                    System.out.println("/list - display a list of currently connected users");
                    break;

                case "/quit":
                    System.out.println("Are you sure: (y/n): ");
                    String response = scan.nextLine();
                    if (response.equals("y"))
                        Transmitter.transmit(Transmitter.disconnect(), cs);
                    else
                        command = " ";
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * connects the client to the server
     * @param host host ip address
     * @param port port number being used (6789)
     */
    public static void connect(String host, int port){
        try {
            cs = new Socket(host, port);
            new Thread(new ClientReceiver(cs)).start();
            Transmitter.transmit(Transmitter.connect(username), cs);
        }catch(ConnectException e){
            e.printStackTrace();
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
