package ua.denis.jdbcproject.server.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.denis.jdbcproject.server.db.model.User;

import java.util.List;

public class DBHandler {

    private SessionFactory sessionFactory = null;
    private Session session = null;
    private EntityManager entityManager = null;

    private static DBHandler INSTANCE = null;
    private DBHandler(){
        setUp();
        entityManager = sessionFactory.createEntityManager();
    }
    public static DBHandler getInstance(){
        if (INSTANCE == null) INSTANCE = new DBHandler();
        return INSTANCE;
    }

    public List getByQueryWithOneParameter(String query, Class aClass, Object parameter){
        List ob = entityManager.createQuery(query).setParameter("first", parameter).getResultList();
        closeSession();
        return ob;
    }

    public List getByQuery(String query){
        List ob = openSession().createQuery(query).list();
        closeSession();
        return ob;
    }

    public Object getEntity(Object entity){
        entityManager.getTransaction().begin();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public void saveEntity(Object ob){
        entityManager.getTransaction().begin();
        entityManager.persist(ob);
        entityManager.getTransaction().commit();
    }
    public void deleteEntity(Object ob){
        entityManager.getTransaction().begin();
        entityManager.remove(ob);
        entityManager.getTransaction().commit();
    }

    public Session openSession(){
        try{
            session = sessionFactory.getCurrentSession();
        }catch(Exception e){
            session = sessionFactory.openSession();
        }finally{
            return session;
        }
        
    }

    public void closeSession(){
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
