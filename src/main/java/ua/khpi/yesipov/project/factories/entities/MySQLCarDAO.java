package ua.khpi.yesipov.project.factories.entities;

import ua.khpi.yesipov.project.entities.Car;
import ua.khpi.yesipov.project.factories.interfaces.CarDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLCarDAO implements CarDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public MySQLCarDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertCar(Car car) {
        return 0;
    }

    public boolean deleteCar(Car car) {
        return false;
    }

    public Car findCar(int id) {
        return null;
    }

    public boolean updateCar(Car car) {
        return false;
    }

    public ResultSet selectCars() {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("SELECT car.id, brand.brand, car.model, quality.quality, car.hours, car.price, car.isOrdered \n" +
                    "FROM orders.car car\n" +
                    "LEFT JOIN orders.brand brand on car.brand_id=brand.id\n" +
                    "LEFT JOIN orders.quality quality on car.quality_id=quality.id\n" +
                    "WHERE car.isOrdered=0;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet selectAllCars() {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("SELECT car.id, brand.brand, car.model, quality.quality, car.hours, car.price, car.isOrdered \n" +
                    "FROM orders.car car\n" +
                    "LEFT JOIN orders.brand brand on car.brand_id=brand.id\n" +
                    "LEFT JOIN orders.quality quality on car.quality_id=quality.id\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
