package ua.khpi.yesipov.project.persistence.dao.impl;

import ua.khpi.yesipov.project.persistence.domain.*;
import ua.khpi.yesipov.project.persistence.dao.OrderDAO;
import ua.khpi.yesipov.project.persistence.domain.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MySQLOrderDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertOrder(Order order) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO order_list (id, car_id, person_id, since, till, driver_id, price) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getCar().getId());
            preparedStatement.setInt(3, order.getPerson().getId());
            preparedStatement.setDate(4, order.getSince());
            preparedStatement.setDate(5, order.getTill());
            preparedStatement.setInt(6, order.getDriver().getId());
            preparedStatement.setDouble(7, order.getPrice());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public boolean deleteOrder(Order order) {
        return false;
    }

    public Order findOrder(int id) {
        return null;
    }

    public boolean updateOrder(Order order) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE orders.car SET price=" + order.getPrice()
                    + " WHERE id=" + order.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //manager or admin
    public List<Order> selectAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT order_list.id, brand.brand, car.model, quality.quality, person.first_name, person.middle_name, person.last_name, " +
                            "person.birthday, person.login, person.password, driver.name, driver.surname, since, till, order_list.price\n" +
                            "FROM orders.order_list order_list\n" +
                            "LEFT JOIN orders.car car on order_list.car_id=car.id\n" +
                            "LEFT JOIN orders.person person on order_list.person_id=person.id\n" +
                            "LEFT JOIN orders.brand brand on car.brand_id=brand.id\n" +
                            "LEFT JOIN orders.quality quality on car.quality_id=quality.id\n" +
                            "LEFT JOIN orders.driver driver on order_list.driver_id=driver.id;");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));

                Car car = new Car();
                Brand brand = new Brand();
                brand.setBrand(resultSet.getString(2));
                car.setBrand(brand);
                car.setModel(resultSet.getString(3));
                Quality quality = new Quality();
                quality.setQuality(resultSet.getString(4));
                car.setQuality(quality);
                order.setCar(car);

                Person person = new Person();
                person.setFirstName(resultSet.getString(5));
                person.setMiddleName(resultSet.getString(6));
                person.setLastName(resultSet.getString(7));
                person.setBirthday(resultSet.getDate(8));
                person.setLogin(resultSet.getString(9));
                person.setPassword(resultSet.getString(10));
                order.setPerson(person);

                Driver driver = new Driver();
                driver.setIsBusy(1);
                driver.setName(resultSet.getString(11));
                driver.setSurname(resultSet.getString(12));

                order.setSince(resultSet.getDate(13));
                order.setTill(resultSet.getDate(14));

                order.setPrice(resultSet.getDouble(15));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    //customer
    public List<Order> selectOrders(int id) {
        List<Order> orders = new ArrayList<Order>();
        try {
            String sql = "WHERE order_list.person_id=" + id + ";";
            orders = select(sql, orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    //manager
    public List<Order> selectOrders(Date date) {
        List<Order> orders = new ArrayList<Order>();
        try {
            String sql = "WHERE order_list.till<=" + date + ";";
            orders = select(sql, orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private List<Order> select(String sql, List<Order> orders) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT order_list.id, brand.brand, car.model, quality.quality, person.first_name, person.middle_name, person.last_name, " +
                        "person.birthday, driver.name, driver.surname, since, till, order_list.price\n" +
                        "FROM orders.order_list order_list\n" +
                        "LEFT JOIN orders.car car on order_list.car_id=car.id\n" +
                        "LEFT JOIN orders.person person on order_list.person_id=person.id\n" +
                        "LEFT JOIN orders.brand brand on car.brand_id=brand.id\n" +
                        "LEFT JOIN orders.quality quality on car.quality_id=quality.id\n" +
                        "LEFT JOIN orders.driver driver on order_list.driver_id=driver.id " + sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt(1));

            Car car = new Car();
            Brand brand = new Brand();
            brand.setBrand(resultSet.getString(2));
            car.setBrand(brand);
            car.setModel(resultSet.getString(3));
            Quality quality = new Quality();
            quality.setQuality(resultSet.getString(4));
            car.setQuality(quality);
            order.setCar(car);

            Person person = new Person();
            person.setFirstName(resultSet.getString(5));
            person.setMiddleName(resultSet.getString(6));
            person.setLastName(resultSet.getString(7));
            person.setBirthday(resultSet.getDate(8));
            order.setPerson(person);

            Driver driver = new Driver();
            driver.setIsBusy(1);
            driver.setName(resultSet.getString(9));
            driver.setSurname(resultSet.getString(10));
            order.setDriver(driver);

            order.setSince(resultSet.getDate(11));
            order.setTill(resultSet.getDate(12));

            order.setPrice(resultSet.getDouble(13));

            orders.add(order);
        }
        return orders;
    }

    public int selectCount() {
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) as total FROM orders.order_list;");
            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }

            resultSet.close();
            preparedStatement.close();
            //connection.close();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
