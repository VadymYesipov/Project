package ua.khpi.yesipov.project.persistence.dao;

import ua.khpi.yesipov.project.persistence.domain.Person;

import javax.sql.RowSet;

public interface PersonDAO {

    public int insertPerson(Person person);

    public boolean deletePerson(Person person);

    public Person findPerson(int id);

    public boolean updatePerson(Person person);

    public RowSet selectPerson();
}
