/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Commands.CommandFactory;
import Commands.ServerCommand;
import Core.MySocket;
import Core.ServerUtility;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SeppQ
 */
public class ClientHandler implements Runnable {

    private MySocket client;
    private EmailManager em;
    private UserManager um;

    public ClientHandler(MySocket client, EmailManager em, UserManager um) {
        this.client = client;
        this.em = em;
        this.um = um;
    }

    @Override
    public void run() {
        System.out.println("Client on " + client.getRemoteAddress());

        String message = "";

        while (!message.equals(ServerUtility.EXIT)) {
            message = client.receiveMessage();
            String response = executeCommand(message);
            client.sendMessage(response);
        }

        System.out.println("Now terminating with client on " + client.getRemoteAddress());
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String executeCommand(String msg) {
        String[] msgArray = msg.split(ServerUtility.COMMAND_BREAKING_CHAR);

        ServerCommand c = CommandFactory.createServerCommand(msgArray[0]);

        return c.execute(em, um, msgArray);
    }

}
