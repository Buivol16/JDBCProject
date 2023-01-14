package ua.denis.jdbcproject.server.db.repository;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import ua.denis.jdbcproject.server.db.DBHandler;

import java.util.List;

public interface CRUDRepository<T, R> {
    /** Need to add id field in object. It's the primary task when you want to get entity. **/

    default T findById(T t) {
        return (T) DBHandler.getInstance().getEntity(t);
    }
    default List<T> findAll(T t){
        return DBHandler.getInstance().getByQuery("FROM " + t.getClass().getName());
    }
    default void saveEntity(T entity){
        DBHandler.getInstance().saveEntity(entity);
    }
    default void deleteEntity(T entity){
        DBHandler.getInstance().deleteEntity(entity);
    }
}