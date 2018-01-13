package ua.khpi.yesipov.project.persistence.domain;

import lombok.Data;

@Data
public class Car {

    private Integer id;

    private Brand brand;

    private String name;

    private Quality quality;

    private Integer hours;

    private Double price;

    private Boolean isOrdered;
}
