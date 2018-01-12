package ua.khpi.yesipov.project;

import ua.khpi.yesipov.project.DAOFactories.DAOFactory;
import ua.khpi.yesipov.project.DAOFactories.MySqlDAOFactory;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        MySqlDAOFactory mySqlDAOFactory = (MySqlDAOFactory) DAOFactory.getDAOFactory(1);
        try {
            Statement statement = MySqlDAOFactory.createConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
