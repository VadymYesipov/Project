package ua.khpi.yesipov.project.DAOFactories;

import ua.khpi.yesipov.project.DAOFactories.DAOInterfaces.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDAOFactory extends DAOFactory {

    public static final String DRIVER =
            "com.mysql.jdbc.Driver";
    public static final String DB_URL =
            "jdbc:mysql://localhost:3306/orders?useSSL=false";

    // метод для создания соединений к Cloudscape
    public static Connection createConnection() {
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/orders");
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BrandDAO getBrandDAO() {
        return null;
    }

    public CarDAO getCarDAO() {
        return null;
    }

    public DriverDAO getDriverDAO() {
        return null;
    }

    public OrderDAO getOrderDAO() {
        return null;
    }

    public QualityDAO getQualityDAO() {
        return null;
    }

    public RoleDAO getRoleDAO() {
        return null;
    }

    public UserDAO getUserDAO() {
        return null;
    }
}