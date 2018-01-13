package ua.khpi.yesipov.project.factories.interfaces;

import ua.khpi.yesipov.project.entities.Order;

import javax.sql.RowSet;
import java.sql.ResultSet;

public interface OrderDAO {

    public int insertOrder(Order order);

    public boolean deleteOrder(Order order);

    public Order findOrder(int id);

    public boolean updateOrder(Order order);

    public ResultSet selectOrders();
}
