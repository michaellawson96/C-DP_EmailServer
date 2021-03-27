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

        switch (commandName) {
            case ServerUtility.LOGIN:
                com = new LoginCommand();
                break;
            case ServerUtility.REGISTER:
                com = new RegisterCommand();
                break;
            default:
                com = new InvalidCommand();
                break;
        }

        return com;
    }
}
