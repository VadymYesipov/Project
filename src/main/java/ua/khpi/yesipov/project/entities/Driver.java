package ua.khpi.yesipov.project.entities;

import lombok.Data;

@Data
public class Driver {

    private Integer id;

    private String name;
    private String surname;

    private Boolean isBusy;
}
