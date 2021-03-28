/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import Core.User;
import java.util.ArrayList;
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

    public boolean Register(User u) {
        if (UserMap.isEmpty()) {
            UserMap.put(u.getUsername(), u);
            return true;
        }

        if (UserMap.containsKey(u.getUsername())) {
            return false;
        } else {
            UserMap.put(u.getUsername(), u);
            return true;
        }
    }
    
    public boolean Login(User u){
        User user;
        if(UserMap.containsKey(u.getUsername())){
            user = (User)UserMap.get(u.getUsername());
            if(user.getPassword().equalsIgnoreCase(u.getPassword())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    } 
    
    public ArrayList<User> allUsers(){
        ArrayList<User> all = new ArrayList();
        for(int i =0 ; i<UserMap.size();i++){
            all.add((User)UserMap.get(i));
        }
        return all;
    }
}
