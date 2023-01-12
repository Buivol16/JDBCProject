package ua.denis.jdbcproject.db.repository.impl;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.db.repository.CRUDRepository;
import ua.denis.jdbcproject.loginapp.model.Session;

public class SessionRepository implements CRUDRepository {
    public static Session getByUserId(Long userId){
        Session session = null;
        try{
            session = (Session) DBHandler.getInstance().getByQueryWithOneParameter("FROM Session WHERE username = :first", Session.class, userId).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }finally{
            return session;
        }
    }
    public static Session getBySessionKey(String sessionKey){
        Session session = null;
        try{
            session = (Session) DBHandler.getInstance().getByQueryWithOneParameter("FROM Session WHERE session_key = :first", Session.class, sessionKey).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }finally{
            return session;
        }
    }
    public static void deleteSession(Session session){
        DBHandler.getInstance().deleteEntity(session);
    }

}
