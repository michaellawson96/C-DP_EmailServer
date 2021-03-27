/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import c.dp_emailserver.EmailManager;

/**
 *
 * @author SeppQ
 */
public class InvalidCommand implements ServerCommand{

    @Override
    public String executeEmails(EmailManager mails, String[] msgArray) {
        return "INVALID";
    }

}
