/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_client;

import Core.MySocket;
import Core.ServerUtility;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class ClientAdminApp {

    private Scanner sc = new Scanner(System.in);
    private MySocket dataSocket;


    public static void main(String[] args) {

        ClientAdminApp clientAdminApp = new ClientAdminApp();
        try {
            clientAdminApp.startApp();
        } catch (UnknownHostException e) {
            System.out.println("Problem occured when finding host");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problem occured when interacting with server");
            e.printStackTrace();
        }
    }

    public void startApp() throws IOException {
        Object email;
        System.out.println("To Terminate Server type Terminate!!");
        dataSocket = new MySocket("localhost", ServerUtility.SERVER_PORT);
        String opt = sc.next();

        switch (opt) {
            case "Terminate":
                String message = ServerUtility.TERMINATE;
                dataSocket.sendMessage(message);
                String response = dataSocket.receiveMessage();
                System.out.println(response);
                break;

        }

    }
}
