/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import c.dp_emailserver.Email;
import c.dp_emailserver.User;

/**
 *
 * @author SeppQ
 */
public class ServerUtility {
    public final static int SERVER_PORT = 5000;
    
    public final static String SEND_MAIL = "send";
    public final static String SUCCESSFUL_SEND = "SUCCESSFUL";
    public final static String FAILED_SEND = "FAILED";
    
    public final static String VIEW_MAIL = "getMail";
    public final static String NO_MAIL = "NONE";
    
    public final static String VIEW_SENT = "getSent";
    
    public final static String EXIT = "exit";
    public final static String EXIT_ACCEPTED = "goodbye";
    
    public final static String COMMAND_BREAKING_CHAR = "%%%";
    public final static String EMAIL_COMPONENT_BREAKING_CHAR = "¬¬";
    public final static String EMAIL_SEPARATOR_CHAR = "~~";
    public final static String EMAIL_RECIPITENTS_CHAR = "%%";
    public final static String USER_CHAR = "%%";
    
    public final static String USER_LOGIN_SUCCESS = "Welcome";
    public final static String USER_LOGIN_FAILED = "password or email incorrect";
    
    public final static String USER_REGISTER_SUCCESS = "You have registered";
    public final static String USER_REGISTER_FAILED = "Email Taken";

       public static Email parseEmail(String email)
    {
        String [] components = email.split(EMAIL_COMPONENT_BREAKING_CHAR);
        // Structure: sender¬¬recipient¬¬subject¬¬body
        String [] Recipitents = email.split(EMAIL_RECIPITENTS_CHAR);
        if(components.length == 4)
        {
            
            
            // Make an email from the components of the String
            Email e = new Email(components[0], components[1], components[2],Recipitents);
            return e;
        }
        return null;
    } 
       
    public static User parseUser(String user){
      String[] components = user.split(USER_CHAR);
      
      if(components.length == 2){
          User u = new User(components[0],components[1]);
          return u;
      }
      return null;
    }   
}
