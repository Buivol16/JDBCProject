package ua.denis.jdbcproject.server.db.repository.impl;


import ua.denis.jdbcproject.server.db.DBHandler;
import ua.denis.jdbcproject.server.db.model.User;
import ua.denis.jdbcproject.server.db.repository.CRUDRepository;

import javax.inject.Singleton;

@Singleton
public class UserRepository implements CRUDRepository<User, Long> {

    public User findByUsername(String username){
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
