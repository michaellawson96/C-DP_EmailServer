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
import java.util.Arrays;

/**
 *
 * @author SeppQ
 */
public class MarkUnReadAsReadCommand implements ServerCommand {

    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response;
        String[] array = msgArray[1].split(ServerUtility.USERNAME_CHAR);
        Email e = ServerUtility.parseEmail(array[1]);
        
        if(e != null){
            mails.markUnreadAsRead(e, array[0]);
            response = ServerUtility.DONE;
        }else{
            response = ServerUtility.PROBLEM;
        }
        return response;
    }
}
