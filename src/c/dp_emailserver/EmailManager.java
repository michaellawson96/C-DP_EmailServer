/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Core.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private Object sentLock = new Object();

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
    
    /**
     * get all of the emails sent by a user specified by username (email address)
     * @param username the current user's email address
     * @return an arraylist of sent emails or null if there are none
     */
    public ArrayList<Email> getSentEmails(String username){
        //if there are any emails in this user's sent list
        if(spamLists.containsKey(username)){
            return spamLists.get(username);
        }
        //if there were no sent emails for this user
        else{
            return null;
        }
    }
    
    /**
     * Search a user's emails and return a list of emails that match the type, 
     * location and query combination given
     * @param type the field that will be compared against the query (sender, 
     * recipient, subject, message)
     * @param location which folder will be checked for existing emails (unread,
     * read, spam, sent) 
     * @param query each email in the specified location will be checked to see 
     * if the value in the field type specified contains this query string
     * @param username the email address of the user whose emails are being 
     * searched
     * @return a list of filtered emails that meet the request
     */
    public ArrayList<Email> searchEmails(String type, String location, String query, String username){
        //make an empty array list to populate with emails
        ArrayList<Email> emails = null;
;        //find out which location the method is trying to search
        switch(location){
            case "unread":
                emails = getUnreadEmails(username);
                break;
                
            case "read":
                emails = getReadEmails(username);
                break;
                
            case "spam":
                emails = getSpamEmails(username);
                break;
                
            case "sent":
                emails = getSentEmails(username);
                break;
        }
        //if there are emails to search through
        if(emails!= null){
            //filter the emails out based on search type
            //make a copy first
            ArrayList<Email> filteredEmails = new ArrayList<Email>();
            switch(type){
                //if searching by sender (appropriate for all lists except sent)
                case "sender":
                    //iterate through the emails
                    for(Email e : emails){
                        //if the current email sender's username contains characters from the query
                        if(e.getSender().contains(query)){
                            //add it to the filtered list of emails
                            filteredEmails.add(e);
                        }
                    }
                    break;
                    
                case "recipent":
                    //iterate through the emails
                    for(Email e : emails){
                        //iterate through the recipent addresses of thecurrent email
                        for(String r : Arrays.asList(e.getRecipients())){
                            //if the current email contains a recipent with a username containing the query
                            if(r.contains(query)){
                                //add it to the filtered list of emails
                                filteredEmails.add(e);
                            }
                            break;
                        }
                    }
                    break;
                
                case "subject":
                    //iterate through the emails
                    for(Email e : emails){
                        //if the current email's subject contains characters from the query
                        if(e.getSubject().contains(query)){
                            //add it to the filtered list of emails
                            filteredEmails.add(e);
                        }
                    }
                    break;
                
                case "message":
                    //iterate through the emails
                    for(Email e : emails){
                        //if the current email's message contains characters from the query
                        if(e.getMessage().contains(query)){
                            //add it to the filtered list of emails
                            filteredEmails.add(e);
                        }
                    }
                    break;
            }
            //replace the emails being returned with the filtered emails
            emails = filteredEmails;
        }
        return emails;
    }
    /**
     * deletes an email sent by a user from their sent list
     * @param email the email that should be deleted
     * @param username the email address of the user that requested the delete
     * @return a boolean representation of success
     */
    public boolean deleteSentEmail(Email email, String username){
        //locking the sent email list so that it can't be modified during deleting
        //an attempt will be made to delete the given email from the user's sent list with a boolean representation of success being returned
        return sentLists.get(username).remove(email);
    }
    
    /**
     * deletes an email sent to the current user from a specified list belonging to them
     * @param email the email that should be deleted
     * @param username the email address of the user that requested the delete
     * @param location the list location that the email will be deleted from
     * @return a boolean representation of success
     */
    public boolean deleteReceivedEmail(Email email, String username, String location){
         //locking the sent email list so that it can't be modified during deleting
        //an attempt will be made to delete the given email from the specified email list with a boolean representation of success being returned
        boolean success = false;
        switch(location){
            //delete from the unread list
            case "unread":
                success = unreadLists.get(username).remove(email);
                break;
            //delete from the read list
            case "read":
                success = readLists.get(username).remove(email);
                break;
            //delete from the spam list
            case "spam":
                success = spamLists.get(username).remove(email);
                break;
        }
        return success;
    }
    
    /**
     * moves an email sent to the current user from a specified list belonging to them to spam
     * @param email the email that should be deleted
     * @param username the email address of the user that requested the delete
     * @param location the list location that the email will be moved from
     * @return a boolean representation of success
     */
    public boolean moveEmailToSpam(Email email, String username, String location){
         //locking the sent email list so that it can't be modified during deleting
        //an attempt will be made to delete the given email from the specified email list with a boolean representation of success being returned
        boolean success = false;
        switch(location){
            //delete from the unread list
            case "unread":
                success = unreadLists.get(username).remove(email);
                break;
            //delete from the read list
            case "read":
                success = readLists.get(username).remove(email);
                break;
        }
        if(success){
            spamLists.get(username).add(email);
        }
        return success;
    }
    
    /**
     * clears all emails from the spam list of the current user
     * @param username the email address of the current user
     */
    public void deleteAllSpam(String username){
        spamLists.get(username).clear();
    }
}
