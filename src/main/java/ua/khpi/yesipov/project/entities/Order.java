package ua.khpi.yesipov.project.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class Order {

    private Integer id;

    private Car car;

    private User user;

    private Date since;
    private Date till;

    private Driver driver;
}