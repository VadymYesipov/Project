package ua.khpi.yesipov.project.persistence.dao;

import ua.khpi.yesipov.project.persistence.domain.Order;

import java.sql.ResultSet;

public interface OrderDAO {

    public int insertOrder(Order order);

    public boolean deleteOrder(Order order);

    public Order findOrder(int id);

    public boolean updateOrder(Order order);

    public ResultSet selectOrders();
}
