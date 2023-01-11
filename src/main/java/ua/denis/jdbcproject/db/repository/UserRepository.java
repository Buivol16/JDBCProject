package ua.denis.jdbcproject.db.repository;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.loginapp.session.model.User;

public class UserRepository {
    public static User getUserByUsername(String username){
        User user = null;
        try{
           user = (User) DBHandler.getInstance().getByQueryWithOneParameter("FROM User WHERE username = :first", User.class, username).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }finally{
            return user;
        }
    }

}
