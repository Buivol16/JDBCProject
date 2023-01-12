package ua.denis.jdbcproject.db.repository.impl;


import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.db.repository.CRUDRepository;
import ua.denis.jdbcproject.loginapp.model.User;

import javax.inject.Singleton;

@Singleton
public class UserRepository implements CRUDRepository<User, Long> {

    public User getUserByUsername(String username){
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
