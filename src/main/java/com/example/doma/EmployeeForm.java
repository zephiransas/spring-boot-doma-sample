package com.example.doma;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class EmployeeForm {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

}
