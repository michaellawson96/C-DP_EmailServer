/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import java.util.Scanner;

/**
 *
 * @author Michael Lawson
 */
public class CDP_EmailServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserManager um = new UserManager();
        User user;
        int input;
        String email;
        String password;
        Scanner sc = new Scanner(System.in);
        while (true) {
            input = sc.nextInt();

            if (input == 1) {
                System.out.println("email");
                email = sc.next();
                System.out.println("password");
                password = sc.next();
                user = new User(email, password);
                System.out.println(um.Register(user));
            } else if (input == 2) {
                System.out.println("email");
                email = sc.next();
                System.out.println("password");
                password = sc.next();
                user = new User(email, password);
                System.out.println(um.Login(user));
            }
        }
    }

}
