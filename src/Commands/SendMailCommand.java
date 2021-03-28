/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.ServerUtility;
import Core.Email;
import c.dp_emailserver.EmailManager;
import c.dp_emailserver.UserManager;

/**
 *
 * @author SeppQ
 */
public class SendMailCommand implements ServerCommand {

    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {
        String response;
        
        //Parse and store email
        System.out.println("asdasdadddddddd" + msgArray[1]);
        Email e = ServerUtility.parseEmail(msgArray[1]);
        if (e != null) {
            mails.sendEmail(e);
            response = ServerUtility.SUCCESSFUL_SEND;
        } else {
            response = ServerUtility.FAILED_SEND;
        }
        return response;
    }

}
