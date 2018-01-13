package ua.khpi.yesipov.project.factories.entities;

import ua.khpi.yesipov.project.entities.Driver;
import ua.khpi.yesipov.project.factories.interfaces.DriverDAO;

import javax.sql.RowSet;
import java.sql.Connection;

public class MySQLDriverDAO implements DriverDAO{

    private Connection connection;

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

    public boolean updateDriver(Driver driver) {
        return false;
    }

    public RowSet selectDrivers() {
        return null;
    }
}