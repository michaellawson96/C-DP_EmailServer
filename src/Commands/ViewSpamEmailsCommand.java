/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.Email;
import Core.ServerUtility;
import c.dp_emailserver.EmailManager;
import c.dp_emailserver.UserManager;
import java.util.ArrayList;

/**
 *
 * @author SeppQ
 */
public class ViewSpamEmailsCommand implements ServerCommand {
    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response = "";
        ArrayList<Email> spamEmails = mails.getSpamEmails(msgArray[1]);
        if (spamEmails != null) {
            for (Email e : spamEmails) {
                String recipitents = "";
                recipitents += e.getRecipients()[0]; 
                for (int i = 1;i < e.getRecipients().length; i++) {
                    recipitents += ServerUtility.EMAIL_RECIPITENTS_CHAR+ e.getRecipients()[i] ;
                }
                response += e.getSender() + ServerUtility.EMAIL_SEPARATOR_CHAR + e.getSubject() + ServerUtility.EMAIL_SEPARATOR_CHAR + e.getMessage() + ServerUtility.EMAIL_RECIPITENTS_CHAR + recipitents + ServerUtility.EMAIL_COMPONENT_BREAKING_CHAR;  
            }
        }
        return response;
    }
}
