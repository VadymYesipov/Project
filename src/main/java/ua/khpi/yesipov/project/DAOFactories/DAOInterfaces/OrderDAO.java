package ua.khpi.yesipov.project.DAOFactories.DAOInterfaces;

import ua.khpi.yesipov.project.entities.Order;

import javax.sql.RowSet;

public interface OrderDAO {

    public int insertOrder(Order order);

    public boolean deleteOrder(Order order);

    public Order findOrder(int id);

    public boolean updateOrder(Order order);

    public RowSet selectOrders();
}
