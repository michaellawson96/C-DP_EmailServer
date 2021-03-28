/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.Email;
import Core.ServerUtility;
import c.dp_emailserver.EmailManager;
import c.dp_emailserver.StringifyEmailList;
import c.dp_emailserver.UserManager;
import java.util.ArrayList;

/**
 *
 * @author SeppQ
 */
public class ViewSentEmailsCommand implements ServerCommand {

    StringifyEmailList sel = new StringifyEmailList();
    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response = "";
        ArrayList<Email> sentEmails = mails.getSentEmails(msgArray[1]);
        
        return sel.stringify(sentEmails);
    }
}
