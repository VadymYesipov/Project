package ua.khpi.yesipov.project.DAOFactories.DAOInterfaces;

import ua.khpi.yesipov.project.entities.Brand;

import javax.sql.RowSet;

public interface BrandDAO {

    public int insertBrand(Brand brand);

    public boolean deleteBrand(Brand brand);

    public Brand findBrand(int id);

    public boolean updateBrands(Brand brand);

    public RowSet selectBrand();
}
