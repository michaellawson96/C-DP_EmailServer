/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author SeppQ
 */
public class MySocket extends Socket
{
    
    protected java.net.Socket socket;
    protected BufferedReader sockIn;
    protected PrintWriter sockOut;
    private String msgIn = null;
    private String remoteAddress = null;

    public MySocket(java.net.Socket socket) throws IOException
    {
        this.socket = socket;
        this.remoteAddress = this.socket.getRemoteSocketAddress().toString();
        setStreams();
    }

    public MySocket(String IP, int port) throws UnknownHostException, IOException
    {
        this.socket = new java.net.Socket(IP, port);
        this.remoteAddress = this.socket.getRemoteSocketAddress().toString();
        setStreams();
    }

    protected void setStreams() throws IOException
    {
        this.sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.sockOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    public void sendMessage(String msg)
    {
        sockOut.println(msg);
        sockOut.flush();
    }

    public String receiveMessage()
    {
        try
        {
            this.msgIn = sockIn.readLine();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return msgIn;
    }

    public String getRemoteAddress()
    {
        return this.remoteAddress;
    }    
}
