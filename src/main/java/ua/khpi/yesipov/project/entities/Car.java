package ua.khpi.yesipov.project.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class Car {

    private Integer id;

    private Brand brand;

    private String name;

    private Quality quality;

    private Date hours;

    private Double price;
}
