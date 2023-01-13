package ua.denis.jdbcproject.server.db.repository.impl;

import ua.denis.jdbcproject.server.db.DBHandler;
import ua.denis.jdbcproject.server.db.model.Car;
import ua.denis.jdbcproject.server.db.repository.CRUDRepository;

import java.util.List;

public class CarRepository implements CRUDRepository<Car, Long> {
    private static CarRepository INSTANCE = null;

    private CarRepository(){}

    public static CarRepository getInstance(){
        if (INSTANCE == null) INSTANCE = new CarRepository();
        return INSTANCE;
    }
    public List findByAuthorId(long id){
        return DBHandler.getInstance().getByQueryWithOneParameter("FROM Car WHERE author_id = :first", Car.class, id);
    }
}
