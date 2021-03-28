/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Core.MySocket;
import Core.ServerUtility;
import Core.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class ServerApp {

    private EmailManager em;
    private UserManager um;

    public static void main(String[] args) {

        ServerApp serverApp = new ServerApp();
        serverApp.startApp();
    }

    public void startApp() {
        //used to store emails

        this.em = new EmailManager();
        this.um = new UserManager();

        try {
            ReadingFromFile(um);
            Scanner sc = new Scanner(System.in);
            System.out.println("---- SERVER ----");
            // Create listening socket to accept connections through
            ServerSocket serverSocket = new ServerSocket(ServerUtility.SERVER_PORT);
            System.out.println("Waiting...");
            while (true) {
                // Accept next client

                MySocket client = new MySocket(serverSocket.accept());

                // Build handler to deal with this client's requests
                ClientHandler clientHandlerJob = new ClientHandler(client, em, um);
                // Allocate worker to this handler
                Thread clientWorker = new Thread(clientHandlerJob);
                // Start handler working with client
                clientWorker.start();
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    public static void writeUsertoFile(UserManager um) {
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter("E:\\NetBeans Project\\C-DP_EmailServer\\src\\c\\dp_emailserver\\Users.txt"));
            for (int i = 0; i < um.allUsers().size(); i++) {

                outputWriter.write(um.allUsers().get(i).getUsername() + ServerUtility.USER_CHAR + um.allUsers().get(i).getPassword());
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
            System.out.println("Writting users to file..");
        } catch (IOException io) {
            System.out.println("Something went wrong");
        }
    }

    public static void ReadingFromFile(UserManager um) {
        Scanner sc;
        try {
            sc = new Scanner(new File("E:\\NetBeans Project\\C-DP_EmailServer\\src\\c\\dp_emailserver\\Users.txt"));
            while (sc.hasNext()) {
                String[] components = sc.next().split(ServerUtility.USER_CHAR);
                User u = new User(components[0], components[1]);
                um.Register(u);
            }
            sc.close();
        } catch (Exception e) {
            e.getMessage();

        }
    }

}
