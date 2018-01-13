package ua.khpi.yesipov.project.persistence.dao;

import ua.khpi.yesipov.project.persistence.domain.Driver;

import javax.sql.RowSet;

public interface DriverDAO {

    public int insertDriver(Driver driver);

    public boolean deleteDriver(Driver driver);

    public Driver findDriver(int id);

    public boolean updateDriver(Driver driver);

    public RowSet selectDrivers();
}
