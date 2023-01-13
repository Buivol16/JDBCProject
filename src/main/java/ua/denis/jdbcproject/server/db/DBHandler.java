package ua.denis.jdbcproject.server.db;

import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DBHandler {

    private SessionFactory sessionFactory = null;
    private Session session = null;

    private static DBHandler INSTANCE = null;
    private DBHandler(){
        setUp();
    }
    public static DBHandler getInstance(){
        if (INSTANCE == null) INSTANCE = new DBHandler();
        return INSTANCE;
    }

    public List getByQueryWithOneParameter(String query, Class aClass, Object parameter){
        List ob = openSession().createQuery(query, aClass).setParameter("first", parameter).list();
        closeSession();
        return ob;
    }

    public List getByQuery(String query){
        List ob = openSession().createQuery(query).list();
        closeSession();
        return ob;
    }

    public Object getEntity(Object id, Class aClass){
        Transaction transaction = openSession().beginTransaction();
        Object ob = openSession().get(aClass, id);
        transaction.commit();
        closeSession();
        return ob;
    }

    public void saveEntity(Object ob){
        Transaction transaction = openSession().beginTransaction();

        transaction.commit();
        closeSession();
    }
    public void deleteEntity(Object ob){
        Transaction transaction = openSession().beginTransaction();
        try{
            openSession().remove(ob);

        }catch (Exception e){

        }
        transaction.commit();
        closeSession();
    }

    private Session openSession(){
        try{
            session = sessionFactory.getCurrentSession();
        }catch(Exception e){
            session = sessionFactory.openSession();
        }finally{
            return session;
        }
        
    }

    private void closeSession(){
        session.close();
    }

    private void setUp(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try{
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
