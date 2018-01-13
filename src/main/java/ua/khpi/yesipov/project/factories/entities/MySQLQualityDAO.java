package ua.khpi.yesipov.project.factories.entities;

import ua.khpi.yesipov.project.entities.Quality;
import ua.khpi.yesipov.project.factories.interfaces.QualityDAO;

import javax.sql.RowSet;
import java.sql.Connection;

public class MySQLQualityDAO implements QualityDAO{

    private Connection connection;

    public MySQLQualityDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertQuality(Quality quality) {
        return 0;
    }

    public boolean deleteQuality(Quality quality) {
        return false;
    }

    public Quality findQuality(int id) {
        return null;
    }

    public boolean updateQuality(Quality quality) {
        return false;
    }

    public RowSet selectQualities() {
        return null;
    }
}
