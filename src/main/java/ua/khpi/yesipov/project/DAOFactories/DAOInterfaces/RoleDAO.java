package ua.khpi.yesipov.project.DAOFactories.DAOInterfaces;

import ua.khpi.yesipov.project.entities.Role;

import javax.sql.RowSet;

public interface RoleDAO {

    public int insertRole(Role role);

    public boolean deleteRole(Role role);

    public Role findRole(int id);

    public boolean updateRole(Role role);

    public RowSet selectRoles();
}
