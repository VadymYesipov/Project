package ua.khpi.yesipov.project.DAOFactories.DAOInterfaces;

import ua.khpi.yesipov.project.entities.User;

import javax.sql.RowSet;

public interface UserDAO {

    public int insertUser(User user);

    public boolean deleteUser(User user);

    public User findUser(int id);

    public boolean updateUser(User user);

    public RowSet selectUsers();
}
