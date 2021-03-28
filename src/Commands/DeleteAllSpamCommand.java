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
public class DeleteAllSpamCommand implements ServerCommand{
    @Override
    public String execute(EmailManager mails, UserManager user, String[] msgArray) {

        mails.deleteAllSpam(msgArray[1]);
        String response = ServerUtility.EMAIL_DELETE_SUCCESS;
        return response;
    }
}
