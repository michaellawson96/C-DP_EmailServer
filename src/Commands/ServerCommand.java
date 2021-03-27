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
public interface ServerCommand {
    public String executeEmails(EmailManager mails, String [] msgArray);
}
