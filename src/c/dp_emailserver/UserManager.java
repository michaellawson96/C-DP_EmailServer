/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import java.util.HashMap;

/**
 *
 * @author arche
 */
public class UserManager {

    private HashMap UserMap;

    public UserManager() {
        this.UserMap = new HashMap();
    }

    public String Register(User u) {
        if (UserMap.isEmpty()) {
            UserMap.put(u.getEmail(), u);
            return "Registered SuccessFully";
        }

        if (UserMap.containsKey(u.getEmail())) {
            return "Email Already In Use";
        } else {
            UserMap.put(u.getEmail(), u);
            return "Registered SuccessFully";
        }
    }
    
    public boolean Login(User u){
        User user;
        if(UserMap.containsKey(u.getEmail())){
            user = (User)UserMap.get(u.getEmail());
            if(user.getPassword().equalsIgnoreCase(u.getPassword())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    } 
}
