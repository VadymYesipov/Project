package ua.khpi.yesipov.project.persistence.dao.impl;

import ua.khpi.yesipov.project.persistence.domain.Driver;
import ua.khpi.yesipov.project.persistence.dao.DriverDAO;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLDriverDAO implements DriverDAO{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public MySQLDriverDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertDriver(Driver driver) {
        return 0;
    }

    public boolean deleteDriver(Driver driver) {
        return false;
    }

    public Driver findDriver(int id) {
        return null;
    }

    public int updateDriver(Driver driver) {
        try {
            statement = connection.createStatement();
            int i = statement.executeUpdate("UPDATE orders.driver SET isBusy=" + driver.getIsBusy() + " WHERE id>1 and id=" + driver.getId());

            statement.close();
            connection.close();

            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Driver> selectDrivers() {
        List<Driver> drivers = new ArrayList<Driver>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM orders.driver WHERE id>1 and isBusy=0;");
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getInt(1));
                driver.setName(resultSet.getString(2));
                driver.setSurname(resultSet.getString(3));
                driver.setIsBusy(resultSet.getInt(4));

                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
}
