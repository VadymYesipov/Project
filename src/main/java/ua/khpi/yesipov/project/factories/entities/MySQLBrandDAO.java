package ua.khpi.yesipov.project.factories.entities;

import ua.khpi.yesipov.project.entities.Brand;
import ua.khpi.yesipov.project.factories.interfaces.BrandDAO;

import javax.sql.RowSet;
import java.sql.Connection;

public class MySQLBrandDAO implements BrandDAO{

    private Connection connection;

    public MySQLBrandDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertBrand(Brand brand) {
        return 0;
    }

    public boolean deleteBrand(Brand brand) {
        return false;
    }

    public Brand findBrand(int id) {
        return null;
    }

    public boolean updateBrands(Brand brand) {
        return false;
    }

    public RowSet selectBrand() {
        return null;
    }
}
