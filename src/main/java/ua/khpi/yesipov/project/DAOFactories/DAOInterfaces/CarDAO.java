package ua.khpi.yesipov.project.DAOFactories.DAOInterfaces;

import ua.khpi.yesipov.project.entities.Car;

import javax.sql.RowSet;

public interface CarDAO {

    public int insertCar(Car car);

    public boolean deleteCar(Car car);

    public Car findCar(int id);

    public boolean updateCar(Car car);

    public RowSet selectCars();
}
