package ua.khpi.yesipov.project.factories.entities;

import ua.khpi.yesipov.project.entities.Role;
import ua.khpi.yesipov.project.factories.interfaces.RoleDAO;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLRoleDAO implements RoleDAO{

    private Connection connection;
    private Statement statement;

    public MySQLRoleDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertRole(Role role) {
        return 0;
    }

    public boolean deleteRole(Role role) {
        return false;
    }

    public Role findRole(int id) {
        return null;
    }

    public boolean updateRole(Role role) {
        return false;
    }

    public ResultSet selectRoles() {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM orders.role;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
