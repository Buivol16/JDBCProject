package ua.denis.jdbcproject.server.db.repository.impl;

import ua.denis.jdbcproject.server.db.DBHandler;
import ua.denis.jdbcproject.server.db.model.Session;
import ua.denis.jdbcproject.server.db.repository.CRUDRepository;


public class SessionRepository implements CRUDRepository<Session, Long> {
    private static SessionRepository INSTANCE = null;

    private SessionRepository(){}

    public static SessionRepository getInstance(){
        if (INSTANCE == null) INSTANCE = new SessionRepository();
        return INSTANCE;
    }

    public Session getByUserId(Long userId){
        Session session = null;
        try{
            session = (Session) DBHandler.getInstance().getByQueryWithOneParameter("FROM Session WHERE username = :first", Session.class, userId).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }finally{
            return session;
        }
    }
    public Session getBySessionKey(String sessionKey){
        Session session = null;
        try{
            session = (Session) DBHandler.getInstance().getByQueryWithOneParameter("FROM Session WHERE session_key = :first", Session.class, sessionKey).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }finally{
            return session;
        }
    }

}
