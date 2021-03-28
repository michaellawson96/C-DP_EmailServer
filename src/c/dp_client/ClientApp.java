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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class ClientApp {

    private Scanner sc = new Scanner(System.in);
    private MySocket dataSocket;
    
    
    public static void main(String[] args) {
        
         ClientApp clientApp = new ClientApp();
        try
        {
            clientApp.startApp();
        } catch (UnknownHostException e)
        {
            System.out.println("Problem occured when finding host");
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println("Problem occured when interacting with server");
            e.printStackTrace();
        }       
    }

    public int chooseLoginRegister() {
        int opt = -1;

        do {
            System.out.println("________________________________________________");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.println("________________________________________________");
            try{
                opt = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter a number between 1 and 3 as indicated by the menu above.");
                opt = -1;
                sc.nextLine();
            }
        } while (opt < 1 || opt > 3);

        return opt;
    }

    public void startApp() throws IOException {
        Object email ;
        System.out.println("Welcome !!");
        dataSocket = new MySocket("localhost", ServerUtility.SERVER_PORT);
        int opt;
        do {
            opt = chooseLoginRegister();
            switch (opt) {
                case 1:
                    email =  login();
                    if(email instanceof String){
                        System.out.println("Welcome " + email);
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Bye");
                    break;
            }
        } while (opt != 3);
    }

    public Object login() {
        System.out.println("Enter Your Email");
        String email = sc.nextLine();
        System.out.println("Enter Your Password");
        String password = sc.nextLine();

        String message = ServerUtility.LOGIN + ServerUtility.COMMAND_BREAKING_CHAR + email + ServerUtility.USER_CHAR + password;

        dataSocket.sendMessage(message);

        String response = dataSocket.receiveMessage();

        if (response.equals(ServerUtility.USER_LOGIN_SUCCESS)) {
            System.out.println("You have loged in successfully");
            return email;
        } else if (response.equals(ServerUtility.USER_LOGIN_FAILED)) {
            System.out.println("Either email or password is incorrect");
            return 0;
        } else {
            System.out.println("Something went wrong on server side");
            return 0;
        }
    }

    public int register() {
        System.out.println("Enter Your Email");
        String email = sc.nextLine();
        System.out.println("Enter Your Password");
        String password = sc.nextLine();

        String message = ServerUtility.REGISTER + ServerUtility.COMMAND_BREAKING_CHAR + email + ServerUtility.USER_CHAR + password;

        dataSocket.sendMessage(message);

        String response = dataSocket.receiveMessage();

        if (response.equals(ServerUtility.USER_REGISTER_SUCCESS)) {
            System.out.println("You have registered in successfully");
            return 1;
        } else if (response.equals(ServerUtility.USER_REGISTER_FAILED)) {
            System.out.println("Email already exist");
            return 0;
        } else {
            System.out.println("Something went wrong on server side");
            return 0;
        }
    }
}
