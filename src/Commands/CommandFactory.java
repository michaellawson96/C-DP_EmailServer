/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.ServerUtility;

/**
 *
 * @author SeppQ
 */
public class CommandFactory {

    private CommandFactory() {

    }

    public static ServerCommand createServerCommand(String commandName) {
        ServerCommand com;

        System.out.println(commandName);
        switch (commandName) {
            case ServerUtility.LOGIN:
                com = new LoginCommand();
                break;
            case ServerUtility.REGISTER:
                com = new RegisterCommand();
                break;
            case ServerUtility.VIEW_UNREAD_MAILS:
                com = new ViewUnreadMailsCommand();
                break;
            case ServerUtility.DELETE_ALL_SPAM:
                com = new DeleteAllSpamCommand();
                break;
            case ServerUtility.DELETE_SENT_EMAIL:
                com = new DeleteSentEmailCommand();
                break;
            case ServerUtility.MOVE_TO_SPAM:
                com = new MoveToSpamCommand();
                break;
            case ServerUtility.SEARCH_EMAIL:
                com = new SearchEmailsCommand();
                break;
            case ServerUtility.VIEW_READ_EMAIL:
                com = new ViewReadEmailsCommand();
                break;
            case ServerUtility.VIEW_SENT_EMAIL:
                com = new ViewSentEmailsCommand();
                break;
            case ServerUtility.VIEW_SPAM_EMAIL:
                com = new ViewSpamEmailsCommand();
                break;
            case ServerUtility.DELETE_RECEIVED_EMAIL:
                com = new DeleteReceivedEmailCommand();
                break;
            case ServerUtility.MARK_UNREAD_AS_READ:
                com = new MarkUnReadAsReadCommand();
                break;
            case ServerUtility.SEND_MAIL:
                com = new SendMailCommand();
                break;
            case ServerUtility.TERMINATE:
                com = new TerminateCommand();
                break;
            default:
                com = new InvalidCommand();
                break;
        }

        return com;
    }
}
