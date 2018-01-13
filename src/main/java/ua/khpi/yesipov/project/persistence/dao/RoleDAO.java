package ua.khpi.yesipov.project.persistence.dao;

import ua.khpi.yesipov.project.persistence.domain.Role;

import java.sql.ResultSet;

public interface RoleDAO {

    public int insertRole(Role role);

    public boolean deleteRole(Role role);

    public Role findRole(int id);

    public boolean updateRole(Role role);

    public ResultSet selectRoles();
}
