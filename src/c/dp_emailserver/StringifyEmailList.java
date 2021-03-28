/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Core.Email;
import Core.ServerUtility;
import java.util.ArrayList;

/**
 *
 * @author Michael Lawson
 */
public class StringifyEmailList {

    public StringifyEmailList() {
    }
    
    public String stringify(ArrayList<Email> emails){
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
                for (int i = 1;i < e1.getRecipients().length; i++) {
                    recipitents += ServerUtility.EMAIL_RECIPITENTS_CHAR+ e1.getRecipients()[i] ;
                }
            //add all to the message
            response += e1.getSender() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e1.getSubject() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + e1.getMessage() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + recipitents;
                //now do the same on each other email
            for (int i = 1 ; i < emails.size(); i++) {
                //except now you put a email seperator at the start of each
                response += ServerUtility.EMAIL_SEPARATOR_CHAR;
                Email ei = emails.get(i);
                recipitents = "";
                recipitents += ei.getRecipients()[0]; 
                for (int j = 1;j < ei.getRecipients().length; j++) {
                    recipitents += ServerUtility.EMAIL_RECIPITENTS_CHAR+ ei.getRecipients()[j] ;
                }
                response += ei.getSender() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + ei.getSubject() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + ei.getMessage() + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR + recipitents;  
                return response;
            }
        }
        
        return ServerUtility.NO_MAIL;

    }
}
