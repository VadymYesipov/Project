package ua.khpi.yesipov.project.DAOFactories;

import ua.khpi.yesipov.project.DAOFactories.DAOInterfaces.*;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int POSTGRESQL = 2;
    public static final int ORACLE = 3;
    public static final int H2 = 4;

    // Здесь будет метод для каждого DAO, который может быть
    // создан. Реализовывать эти методы
    // должны конкретные генераторы.
    public abstract BrandDAO getBrandDAO();

    public abstract CarDAO getCarDAO();

    public abstract DriverDAO getDriverDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract QualityDAO getQualityDAO();

    public abstract RoleDAO getRoleDAO();

    public abstract UserDAO getUserDAO();


    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySqlDAOFactory();
            case POSTGRESQL:
            case ORACLE:
            case H2:
            default:
                return null;
        }
    }
}
