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

/**
 *
 * @author SeppQ
 */
public class SearchEmailsCommand implements ServerCommand {

    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response;
        
        String[] arr = msgArray[1].split(ServerUtility.EMAIL_SEPARATOR_CHAR);
        
       
        response = ServerUtility.parseResponse( mails.searchEmails(arr[0], arr[1], arr[2], arr[3]));
        
        return response;

    }

}
