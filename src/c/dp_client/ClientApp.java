/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_client;

import Core.Email;
import Core.MySocket;
import Core.ServerUtility;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class ClientApp {

    private String username;
    private Scanner sc = new Scanner(System.in);
    private MySocket dataSocket;
    private String currentFolder;
    
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

    public int chooseUserMenu(){
            int opt = -1;

        do {
            System.out.println("________________________________________________");
            System.out.println("1) Send Email");
            System.out.println("2) Unread");
            System.out.println("3) Read");
            System.out.println("4) Sent");
            System.out.println("5) Spam");
            System.out.println("6) Delete All Spam");
            System.out.println("7) Logout");
            System.out.println("________________________________________________");
            try{
                opt = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter a number between 1 and 6 as indicated by the menu above.");
                opt = -1;
                sc.nextLine();
            }
        } while (opt < 1 || opt > 7);

        return opt;
    }
    
    public int chooseEmailListMenu(ArrayList<Email> emails){
        int opt = -1;
        
        do {
            System.out.println("________________________________________________");
            System.out.println("____________SELECT AN EMAIL TO OPEN_____________");
            System.out.println("________________________________________________");
            String message;
            for (int i = 0; i < emails.size(); i++) {
                System.out.println((i+1)+") From: " + emails.get(i).getSender() + " Subject: " + emails.get(i).getSubject());
            }
            System.out.println((emails.size()+1)+") Back");
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
        } while (opt < 1 || opt > (emails.size()+1));
        return opt;
    }
    
    public int chooseEmailMenu(Email email){
        int opt = -1;
        System.out.println("________________________________________________");
        System.out.println("From: " + email.getSender());
        String recipients = "";
        for (int i = 0; i < email.getRecipients().length; i++) {
            recipients += email.getRecipients()[i] + "; ";
        }
        System.out.println("To: " + recipients);
        System.out.println(email.getMessage());
        System.out.println("________________________________________________");
        
        do {
            System.out.println("________________________________________________");
            System.out.println("1) Move to Spam");
            System.out.println("2) Delete");
            System.out.println("3) Back");
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
                        username = (String)email;
                        userMenu();
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

    
    public void userMenu(){
        int opt;
        do {
            opt = chooseUserMenu();
            switch (opt) {
                //send email
                case 1:
                    newEmailMenu();
                    break;
                //list unread email
                case 2:
                    unreadEmailMenu();
                    break;
                //list read email
                case 3:
                    readEmailMenu();
                    break;
                //list sent email
                case 4:
                    sentEmailMenu();
                    break;
                //list spam email
                case 5:
                    spamEmailMenu();
                    break;
                //delete all spam emails for this user
                case 6:
                    deleteAllSpam();
                    break;
                //logout == 7
                
            }
        } while (opt != 7);
        username = null;
        System.out.println("Logout successful");
    }
    
    public void newEmailMenu(){
        ArrayList<String> recipients = new ArrayList<String>();
        System.out.println("Enter a user's email address to send to");
        recipients.add(sc.nextLine());
        
        String output = null;
        //recipients
        do{
            System.out.println("Enter another users email address to send to or -1 to move on");
            output = sc.nextLine();
            if(!output.equals("-1")){
             if(!recipients.contains(output)){
                 recipients.add(output);
             }    
             else{
                 System.out.println("recipient already set");
             }
            }
        }while(!output.equals("-1"));
        //subject
        System.out.println("Enter a subject");
        String subject = sc.nextLine();
        //message
        System.out.println("Enter a message");
        String message = sc.nextLine();
        
        String[] recArray = recipients.toArray(new String [recipients.size()]);
                
        //email object made
        Email newEmail = new Email(username, subject, message, recArray);
        //make the message        
        //add everything to the msg except the recipient
        String clientMsg = ServerUtility.SEND_MAIL + ServerUtility.COMMAND_BREAKING_CHAR + username + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + subject + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + message + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR;
        //add first recipient
        clientMsg += recipients.get(0);
        for (int i = 1; i < recipients.size(); i++) {
            //add the seperator and current recipient to message
            clientMsg += ServerUtility.EMAIL_RECIPITENTS_CHAR + recipients.get(i);
        }
        //send server request and get response
        System.out.println(clientMsg);
        String response = serverRequest(clientMsg);
        //if the expected responce is received
        if(response!=null){
            System.out.println(response);
        }
        else{
            System.out.println("Something went wrong on server side");
        }
    }
    
    public void unreadEmailMenu(){
        //make the message
        String clientMsg = ServerUtility.VIEW_UNREAD_MAILS + ServerUtility.COMMAND_BREAKING_CHAR + username;
        String response = serverRequest(clientMsg);
        System.out.println("+++++++++++++++++" + response);
        if(response.equals(ServerUtility.NO_MAIL)){
            System.out.println("no unread mail was found");
        }
        else if(response.isEmpty()||response == null){
            System.out.println("Empty response");
        }
        else{
            System.out.println(response);
            ArrayList<Email> emails = objectifyEmailList(response);
            chooseEmailListMenu(emails);
        }
    }
    
    public ArrayList<Email> objectifyEmailList(String emails){
        //seperate the emails
        ArrayList<Email> emailList = new ArrayList<Email>();
        String[] emailStrings = emails.split(ServerUtility.EMAIL_SEPARATOR_CHAR);
        //for each email component
        for(int i = 0; i < emailStrings.length; i++){
            //sender%%subject%%message%%recipient##recipient
            String[] emailComponents = emailStrings[i].split(ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR);
            String[] recipients = emailComponents[3].split(ServerUtility.EMAIL_RECIPITENTS_CHAR);
            emailList.add(new Email(emailComponents[0],emailComponents[0],emailComponents[0],recipients));
        }
        return emailList;
    }
    
    public void readEmailMenu(){
        //make the message
        String clientMsg = ServerUtility.VIEW_READ_EMAIL + ServerUtility.COMMAND_BREAKING_CHAR + username;
        String response = serverRequest(clientMsg);
        if(response.equals(ServerUtility.NO_MAIL)){
            System.out.println("no read mail was found");
        }
        else{
            ArrayList<Email> emails = objectifyEmailList(response);
            chooseEmailListMenu(emails);
        }
    }
    
    public void sentEmailMenu(){
        //make the message
        String clientMsg = ServerUtility.VIEW_SENT_EMAIL + ServerUtility.COMMAND_BREAKING_CHAR + username;
        String response = serverRequest(clientMsg);
        if(response.equals(ServerUtility.NO_MAIL)){
            System.out.println("no sent mail was found");
        }
        else{
            ArrayList<Email> emails = objectifyEmailList(response);
            chooseEmailListMenu(emails);
        }
    }
    
    public void spamEmailMenu() {
//make the message
        String clientMsg = ServerUtility.VIEW_SPAM_EMAIL + ServerUtility.COMMAND_BREAKING_CHAR + username;
        String response = serverRequest(clientMsg);
        if(response.equals(ServerUtility.NO_MAIL)){
            System.out.println("no spam mail was found");
        }
        else{
            ArrayList<Email> emails = objectifyEmailList(response);
            chooseEmailListMenu(emails);
        }
    }
    
    public void deleteAllSpam(){
        //make the message
        String clientMsg = ServerUtility.DELETE_ALL_SPAM + ServerUtility.COMMAND_BREAKING_CHAR + username;
        String response = serverRequest(clientMsg);
        if(response.equals(ServerUtility.DONE)){
            System.out.println("spam deleted");
        }
        else{
            System.out.println("problem occured");
        }
    }
    
    public String serverRequest(String message){

        dataSocket.sendMessage(message);

        String response = dataSocket.receiveMessage();
        return response;
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
