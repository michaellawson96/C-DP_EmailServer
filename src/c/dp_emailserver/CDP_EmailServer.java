/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Michael Lawson
 */
public class CDP_EmailServer {

    
    private static EmailManager emailManager = new EmailManager();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Email newEmail = new Email("michael.c.k.lawson@gmail.com", "Test Email", "This is a test email", new String[] {"d00185184@student.dkit.ie"});
        emailManager.sendEmail(newEmail);
        ArrayList<Email> unreadEmails = emailManager.getUnreadEmails("d00185184@student.dkit.ie");
        for(Email unreadEmail : unreadEmails){
            System.out.println("Sender: " + unreadEmail.getSender());
            System.out.println("Subject: " + unreadEmail.getSubject());
            System.out.println("Message: " + unreadEmail.getMessage());
        }
        /*UserManager um = new UserManager();
        User user;
        int input;
        String email;
        String password;
        Scanner sc = new Scanner(System.in);
        while (true) {
            input = sc.nextInt();

            if (input == 1) {
                System.out.println("username");
                email = sc.next();
                System.out.println("password");
                password = sc.next();
                user = new User(email, password);
                System.out.println(um.Register(user));
            } else if (input == 2) {
                System.out.println("username");
                email = sc.next();
                System.out.println("password");
                password = sc.next();
                user = new User(email, password);
                System.out.println(um.Login(user));
            }
        }*/
    }

}
