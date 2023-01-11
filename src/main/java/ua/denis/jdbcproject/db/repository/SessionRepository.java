package ua.denis.jdbcproject.db.repository;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.db.DbHelper;
import ua.denis.jdbcproject.loginapp.session.model.Session;

public class SessionRepository {
    public static Session getByUsername(String username){
        Session session = null;
        try{
            session = (Session) DBHandler.getInstance().getByQueryWithOneParameter("FROM Session WHERE username = :first", Session.class, username).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }finally{
            return session;
        }
    }
    public static void deleteBySessionKey(Session session){
        DBHandler.getInstance().deleteEntity(session);
    }

}
