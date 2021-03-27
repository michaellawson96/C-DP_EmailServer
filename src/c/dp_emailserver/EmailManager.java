/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Core.Email;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages the Emails. Stores who's received, opened, set as spam or sent any emails
 * @author Michael Lawson
 */
public class EmailManager {
    
    private HashMap<String, ArrayList<Email>> readLists = new HashMap(); //records what emails the user (email address of user is the key) has received and read
    private HashMap<String, ArrayList<Email>> unreadLists = new HashMap();
    private HashMap<String, ArrayList<Email>> spamLists = new HashMap();
    private HashMap<String, ArrayList<Email>> sentLists = new HashMap();

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
    public void sendEmail(Email email){
       //for each recipent
       for(String r: email.getRecipients()){
           //if the recipient is not a key in the unreadlists
           if (!unreadLists.containsKey(r)) {
               unreadLists.put(r, new ArrayList<Email>());
           }
           //add the email into the recipient's unread email list
           unreadLists.get(r).add(email);
       }
       //if the recipient is not a key in the unreadlists
        if (!sentLists.containsKey(email.getSender())) {
            unreadLists.put(email.getSender(), new ArrayList<Email>());
        }
        //add the email into the recipient's unread email list
        unreadLists.get(email.getSender()).add(email);
    }
    
    /**
     * Get all of the unread emails for the given username (email address)
     * @param username the current user's email address
     * @return an arraylist of unread emails or null if there are none
     */
    public ArrayList<Email> getUnreadEmails(String username){
        //if there are any emails in this user's unread list
        if(unreadLists.containsKey(username)){
            return unreadLists.get(username);
        }
        //if there were no unread emails for this user
        else{
            return null;
        }
    }
    
    /**
     * Get all of the read emails for the given username (email address)
     * @param username the current user's email address
     * @return an arraylist of read emails or null if there are none
     */
    public ArrayList<Email> getReadEmails(String username){
        //if there are any emails in this user's read list
        if(readLists.containsKey(username)){
            return readLists.get(username);
        }
        //if there were no read emails for this user
        else{
            return null;
        }
    }
    
    /**
     * Get all of the spam emails for the given username (email address)
     * @param username the current user's email address
     * @return an arraylist of spam emails or null if there are none
     */
    public ArrayList<Email> getSpamEmails(String username){
        //if there are any emails in this user's spam list
        if(spamLists.containsKey(username)){
            return spamLists.get(username);
        }
        //if there were no spam emails for this user
        else{
            return null;
        }
    }
}
