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
public class DeleteReceivedEmailCommand implements ServerCommand {

    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response;
        
        String[] arr = msgArray[1].split(ServerUtility.EMAIL_SEPARATOR_CHAR);
        
        
        String username = arr[0];
        String location = arr[1];
        Email e = ServerUtility.parseEmail(arr[2]);
        if (e != null) {
            mails.deleteReceivedEmail(e,username,location);
            response = ServerUtility.EMAIL_DELETE_SUCCESS;
        } else {
            response = ServerUtility.EMAIL_DELETE_FAILED;
        }
        return response;
    }

}