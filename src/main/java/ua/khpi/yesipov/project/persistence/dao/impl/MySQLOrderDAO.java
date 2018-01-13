package ua.khpi.yesipov.project.persistence.dao.impl;

import ua.khpi.yesipov.project.persistence.domain.Order;
import ua.khpi.yesipov.project.persistence.dao.OrderDAO;

import java.sql.*;

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
        return false;
    }

    public ResultSet selectOrders() {
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT order_list.id, car.model, person.first_name, person.middle_name, person.last_name, person.birthday, person.password, person.login, since, till, order_list.price \n" +
                    "FROM orders.order_list order_list\n" +
                    "LEFT JOIN orders.car car on order_list.car_id=car.id\n" +
                    "LEFT JOIN orders.person person on order_list.person_id=person.id");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
