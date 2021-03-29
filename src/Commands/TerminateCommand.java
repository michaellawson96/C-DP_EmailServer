/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.ServerUtility;
import c.dp_emailserver.EmailManager;
import c.dp_emailserver.ServerApp;
import c.dp_emailserver.UserManager;

/**
 *
 * @author SeppQ
 */
public class TerminateCommand implements ServerCommand{
    @Override
    public String execute(EmailManager mails,UserManager user, String[] msgArray) {
        ServerApp app = new ServerApp();
        app.serverSatus = false;
        return ServerUtility.TERMINATE;
    }    

}