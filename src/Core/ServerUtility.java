/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author SeppQ
 */
public class ServerUtility {

    public final static int SERVER_PORT = 5000;
    public final static String TERMINATE = "terminate";
    public final static String SEND_MAIL = "send";
    public final static String SUCCESSFUL_SEND = "SUCCESSFUL";
    public final static String FAILED_SEND = "FAILED";

    public final static String VIEW_MAIL = "getMail";
    public final static String NO_MAIL = "NONE";
    public final static String VIEW_UNREAD_MAILS = "getunreadmails";
    public final static String VIEW_SENT = "getSent";
    public final static String DELETE_ALL_SPAM = "deleteAllSpam";
    public final static String DELETE_RECEIVED_EMAIL = "deleteReceivedEmail";
    public final static String DELETE_SENT_EMAIL = "deleteSentEmail";
    public final static String MOVE_TO_SPAM = "moveToSpam";
    public final static String SEARCH_EMAIL = "searchEmail";
    public final static String VIEW_READ_EMAIL = "viewReadEmail";
    public final static String VIEW_SENT_EMAIL = "viewSentEmail";
    public final static String VIEW_SPAM_EMAIL = "viewSpamEmail";
    public final static String MARK_UNREAD_AS_READ = "markAsRead";

    public final static String EXIT = "exit";
    public final static String EXIT_ACCEPTED = "goodbye";
    public final static String DONE = "done";
    public final static String PROBLEM = "problem occured";

    public final static String COMMAND_BREAKING_CHAR = "%%%";
    public final static String EMAIL_SEPARATOR_CHAR = "¬¬";
    public final static String EMAIL_COMPONENT_BREAKING_CHAR = "%%";
    public final static String EMAIL_RECIPITENTS_CHAR = "##";
    public final static String USER_CHAR = "%%";
    public final static String USERNAME_CHAR = "&&&";

    public final static String LOGIN = "login";
    public final static String REGISTER = "register";

    public final static String USER_LOGIN_SUCCESS = "Welcome";
    public final static String USER_LOGIN_FAILED = "password or email incorrect";

    public final static String USER_REGISTER_SUCCESS = "You have registered";
    public final static String USER_REGISTER_FAILED = "Email Taken";

    public final static String EMAIL_DELETE_SUCCESS = "Deleted successfully";
    public final static String EMAIL_DELETE_FAILED = "Delete failed";

    public static Email parseEmail(String email) {
        String[] components = email.split(EMAIL_COMPONENT_BREAKING_CHAR);
        String[] Recipitents = new String[1];

        System.out.println("you got here");

        if (components[3].contains(EMAIL_RECIPITENTS_CHAR)) {
            Recipitents = components[3].split(EMAIL_RECIPITENTS_CHAR);
        } else {
            Recipitents[0] = components[3];
        }

        if (components.length == 4) {

            // Make an email from the components of the String
            Email e = new Email(components[0], components[1], components[2], Recipitents);
            System.out.println(e.toString());
            return e;
        }
        return null;
    }

    public static User parseUser(String user) {
        String[] components = user.split(USER_CHAR);

        if (components.length == 2) {
            User u = new User(components[0], components[1]);
            return u;
        }
        return null;
    }

    public static String parseResponse(ArrayList<Email> e) {
        String[] recipitentArr = e.get(0).getRecipients();
        String recipitent = recipitentArr[0];
        for (int i = 0; i < recipitentArr.length; i++) {
            recipitent += ServerUtility.EMAIL_RECIPITENTS_CHAR + recipitent;
        }

        String response = e.get(0).getSender() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e.get(0).getSubject() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e.get(0).getMessage() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + recipitent;
        for (int i = 1; i < e.size(); i++) {
            response += ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e.get(0).getSender() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e.get(0).getSubject() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e.get(0).getMessage() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + recipitent;
        }

        return response;
    }

    public static String stringify(ArrayList<Email> emails) {
        String response = "";
        //if the unread emails  string isnt null
        if (emails != null) {
            Email e1 = emails.get(0);
            //insert the first email first
            //get ready to collect the recipients
            String recipitents = "";
            //put the first recipient into the string
            recipitents += e1.getRecipients()[0];
            //put the rest of the recipients in with a seperator a the start of each one
            for (int i = 1; i < e1.getRecipients().length; i++) {
                recipitents += ServerUtility.EMAIL_RECIPITENTS_CHAR + e1.getRecipients()[i];
            }

            //add all to the message
            response += e1.getSender() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e1.getSubject() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e1.getMessage() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + recipitents;
            //now do the same on each other email
            System.out.println(emails.size());
                for (int i = 1; i < emails.size(); i++) {
                    //except now you put a email seperator at the start of each
                    response += ServerUtility.EMAIL_SEPARATOR_CHAR;
                    Email ei = emails.get(i);
                    recipitents = "";
                    recipitents += ei.getRecipients()[0];
                    for (int j = 1; j < ei.getRecipients().length; j++) {
                        recipitents += ServerUtility.EMAIL_RECIPITENTS_CHAR + ei.getRecipients()[j];
                    }
                    response += ei.getSender() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + ei.getSubject() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + ei.getMessage() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + recipitents;

                }
          
            return response;
        }

        return ServerUtility.NO_MAIL;

    }

}
