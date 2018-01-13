package ua.khpi.yesipov.project.persistence.dao;

import ua.khpi.yesipov.project.persistence.domain.Car;

import java.sql.ResultSet;

public interface CarDAO {

    public int insertCar(Car car);

    public boolean deleteCar(Car car);

    public Car findCar(int id);

    public boolean updateCar(Car car);

    public ResultSet selectCars();

    public ResultSet selectAllCars();
}
