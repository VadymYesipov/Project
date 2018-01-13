package ua.khpi.yesipov.project.factories.interfaces;

import ua.khpi.yesipov.project.entities.Role;

import java.sql.ResultSet;

public interface RoleDAO {

    public int insertRole(Role role);

    public boolean deleteRole(Role role);

    public Role findRole(int id);

    public boolean updateRole(Role role);

    public ResultSet selectRoles();
}
