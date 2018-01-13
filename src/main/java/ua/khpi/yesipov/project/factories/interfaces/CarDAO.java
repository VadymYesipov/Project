package ua.khpi.yesipov.project.factories.interfaces;

import ua.khpi.yesipov.project.entities.Car;

import java.sql.ResultSet;

public interface CarDAO {

    public int insertCar(Car car);

    public boolean deleteCar(Car car);

    public Car findCar(int id);

    public boolean updateCar(Car car);

    public ResultSet selectCars();

    public ResultSet selectAllCars();
}
