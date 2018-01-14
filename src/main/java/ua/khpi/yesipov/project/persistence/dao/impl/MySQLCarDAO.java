package ua.khpi.yesipov.project.persistence.dao.impl;

import ua.khpi.yesipov.project.persistence.domain.Brand;
import ua.khpi.yesipov.project.persistence.domain.Car;
import ua.khpi.yesipov.project.persistence.dao.CarDAO;
import ua.khpi.yesipov.project.persistence.domain.Quality;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCarDAO implements CarDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public MySQLCarDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertCar(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO orders_list.car (id, brand_id, model, quality_id, price, isOrdered) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setInt(2, car.getBrand().getId());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getQuality().getId());
            preparedStatement.setDouble(5, car.getPrice());
            preparedStatement.setInt(6, car.getIsOrdered());

            int result = preparedStatement.executeUpdate();
            preparedStatement.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean deleteCar(Car car) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM order_list.car car WHERE car.id=" + car.getId() + ";");
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Car findCar(int id) {
        return null;
    }

    public boolean updateCar(Car car) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE orders.car SET isOrdered=" + car.getIsOrdered()
                    + " WHERE id=" + car.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Car> selectCars() {
        List<Car> cars = new ArrayList<Car>();
        try {
            String sql = "WHERE car.isOrdered=0;";
            cars = select(sql, cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> selectAllCars() {
        return null;
    }

    public List<Car> selectCarsByBrand(String brand) {
        List<Car> cars = new ArrayList<Car>();
        try {
            String sql = "WHERE brand.brand=\"" + brand + "\";";
            cars = select(sql, cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> selectCarsByQuality(String quality) {
        List<Car> cars = new ArrayList<Car>();
        try {
            String sql = "WHERE quality.quality=\"" + quality + "\";";
            cars = select(sql, cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> selectSortedByModel() {
        List<Car> cars = new ArrayList<Car>();
        try {
            String sql = "ORDER BY model;";
            cars = select(sql, cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> selectSortedByPrice() {
        List<Car> cars = new ArrayList<Car>();
        try {
            String sql = "ORDER BY price DESC;";
            cars = select(sql, cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private List<Car> select(String sql, List<Car> cars) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT car.id, brand.id, brand.brand, car.model, quality.id, quality.quality, car.price, car.isOrdered FROM orders.car car\n" +
                "LEFT JOIN orders.brand brand on car.brand_id=brand.id\n" +
                "LEFT JOIN orders.quality quality on car.quality_id=quality.id\n" + sql);
        while (resultSet.next()) {
            Car car = new Car();
            car.setId(resultSet.getInt(1));

            Brand brand = new Brand();
            brand.setId(resultSet.getInt(2));
            brand.setBrand(resultSet.getString(3));
            car.setBrand(brand);

            car.setModel(resultSet.getString(4));

            Quality quality = new Quality();
            quality.setId(resultSet.getInt(5));
            quality.setQuality(resultSet.getString(6));
            car.setQuality(quality);

            car.setPrice(resultSet.getDouble(7));

            car.setIsOrdered(resultSet.getInt(8));

            cars.add(car);
        }
        return cars;
    }
}
