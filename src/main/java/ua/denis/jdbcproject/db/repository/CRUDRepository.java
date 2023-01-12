package ua.denis.jdbcproject.db.repository;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.loginapp.model.User;

public interface CRUDRepository<T, R> {
    default T findById(R id, T t) {
        return (T) DBHandler.getInstance().getEntity(id, t.getClass());
    }

    default void saveEntity(T entity){
        DBHandler.getInstance().saveEntity(entity);
    }
    default void deleteEntity(T entity){
        DBHandler.getInstance().deleteEntity(entity);
    }
}