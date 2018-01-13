package ua.khpi.yesipov.project.persistence.dao.impl;

import ua.khpi.yesipov.project.persistence.domain.Person;
import ua.khpi.yesipov.project.persistence.dao.PersonDAO;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLPersonDAO implements PersonDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public MySQLPersonDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertPerson(Person person) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO person (id, role_id, first_name, middle_name, last_name, birthday, login, password) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setInt(2, person.getRole().getId());
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(4, person.getMiddleName());
            preparedStatement.setString(5, person.getLastName());
            preparedStatement.setDate(6, person.getBirthday());
            preparedStatement.setString(7, person.getLogin());
            preparedStatement.setString(8, person.getPassword());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean deletePerson(Person person) {
        return false;
    }

    public Person findPerson(int id) {
        return null;
    }

    public boolean updatePerson(Person person) {
        return false;
    }

    public RowSet selectPerson() {
        return null;
    }
}
