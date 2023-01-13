package ua.denis.jdbcproject.server.db.repository;

import ua.denis.jdbcproject.server.db.DBHandler;

import java.util.List;

public interface CRUDRepository<T, R> {
    default T findById(R id, T t) {
        return (T) DBHandler.getInstance().getEntity(id, t.getClass());
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