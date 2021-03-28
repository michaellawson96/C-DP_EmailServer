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
public class DeleteSentEmailCommand implements ServerCommand {

    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response = "";
        String[] array = msgArray[1].split(ServerUtility.USERNAME_CHAR);
        Email e = ServerUtility.parseEmail(array[1]);
        
        if(e != null){
            mails.deleteSentEmail(e, array[0]);
            response = ServerUtility.EMAIL_DELETE_SUCCESS;
        }else{
            response = ServerUtility.EMAIL_DELETE_FAILED;
        }
        return response;
    }
}