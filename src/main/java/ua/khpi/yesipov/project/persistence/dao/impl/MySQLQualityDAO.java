package ua.khpi.yesipov.project.persistence.dao.impl;

import ua.khpi.yesipov.project.persistence.domain.Quality;
import ua.khpi.yesipov.project.persistence.dao.QualityDAO;

import javax.sql.RowSet;
import java.sql.Connection;
import java.util.List;

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

    public List<Quality> selectQualities() {
        return null;
    }
}
