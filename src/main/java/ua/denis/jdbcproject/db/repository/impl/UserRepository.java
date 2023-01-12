package ua.denis.jdbcproject.db.repository.impl;

import lombok.Singular;
import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.db.repository.CRUDRepository;
import ua.denis.jdbcproject.loginapp.model.User;


public class UserRepository implements CRUDRepository<User, Long> {


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

    public User findById(Long id, User user) {
        return CRUDRepository.super.findById(id, user);
    }

    @Override
    public void saveEntity(User entity) {
        CRUDRepository.super.saveEntity(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        CRUDRepository.super.deleteEntity(entity);
    }
}
