package ua.khpi.yesipov.project.DAOFactories.DAOInterfaces;

import ua.khpi.yesipov.project.entities.Quality;

import javax.sql.RowSet;

public interface QualityDAO {

    public int insertQuality(Quality quality);

    public boolean deleteQuality(Quality quality);

    public Quality findQuality(int id);

    public boolean updateQuality(Quality quality);

    public RowSet selectQualities();
}
