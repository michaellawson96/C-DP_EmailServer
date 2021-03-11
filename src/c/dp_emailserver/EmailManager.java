/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages the Emails. Stores who's received, opened, set as spam or sent any emails
 * @author Michael Lawson
 */
public class EmailManager {
    
    HashMap<String, ArrayList<Email>> recipentLists = new HashMap(); //records what emails the user (email address of user is the key) has received
    HashMap<String, ArrayList<Email>> unreadLists = new HashMap();
    HashMap<String, ArrayList<Email>> spamLists = new HashMap();
    HashMap<String, ArrayList<Email>> sentLists = new HashMap();

    /**
     * EmailManager constructor
     */
    public EmailManager() {
    }
    
    /**
     * Sends an email to each recipient
     * @param email The email being sent
     * @param sender The email address of the user being 
     * @param recipients An array containing a list of the User's email addresses
     * that the email will be sent to
     */
    public void sendEmail(Email email, String sender, String[] recipients){
       //for each recipent
       for(String r: recipients){
           //add it to the recipent's list of emails
           recipentLists.get(r).add(email);
           //add it to the recipent's list of unread emails
           unreadLists.get(r).add(email);
       }
       //add it to the sender's list of sent emails
       sentLists.get(sender).add(email);
    }
}
