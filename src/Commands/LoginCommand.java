/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Core.ServerUtility;
import c.dp_emailserver.User;
import c.dp_emailserver.UserManager;

/**
 *
 * @author SeppQ
 */
public class LoginCommand implements ServerUserCommands {
    @Override
    public String executeUser(UserManager user , String[] msgArray){
        String response = "";
        
        User u = ServerUtility.parseUser(msgArray[1]);
        if(u != null){
            if(user.Login(u)){
                response = ServerUtility.USER_LOGIN_SUCCESS;
            }else{
                response = ServerUtility.USER_LOGIN_FAILED;
            }
            
        }
        return response;
    }          
}
