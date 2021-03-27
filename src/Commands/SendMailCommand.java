/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.ServerUtility;
import c.dp_emailserver.Email;
import c.dp_emailserver.EmailManager;

/**
 *
 * @author SeppQ
 */
public class SendMailCommand implements ServerCommand{

    @Override
    public String executeEmails(EmailManager mails, String[] msgArray) {
        String response;
        
        //Parse and store email
        Email e = ServerUtility.parseEmail(msgArray[1]);
        if(e != null){
            mails.sendEmail(e);
            response = ServerUtility.SUCCESSFUL_SEND;
        }else{
            response = ServerUtility.FAILED_SEND;
        }        
        return response;
    }

}