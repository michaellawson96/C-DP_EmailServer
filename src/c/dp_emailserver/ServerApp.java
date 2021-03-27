/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Core.MySocket;
import Core.ServerUtility;
import java.net.ServerSocket;

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
    
        public void startApp()
    {
        //used to store emails
        this.em = new EmailManager();
        this.um = new UserManager();
        try
        {
            System.out.println("---- SERVER ----");
            // Create listening socket to accept connections through
            ServerSocket serverSocket = new ServerSocket(ServerUtility.SERVER_PORT);
            System.out.println("Waiting...");
            while(true)
            {
                // Accept next client
                MySocket client = new MySocket(serverSocket.accept());
                
                // Build handler to deal with this client's requests
                ClientHandler clientHandlerJob = new ClientHandler(client, em , um);
                // Allocate worker to this handler
                Thread clientWorker = new Thread(clientHandlerJob);
                // Start handler working with client
                clientWorker.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
