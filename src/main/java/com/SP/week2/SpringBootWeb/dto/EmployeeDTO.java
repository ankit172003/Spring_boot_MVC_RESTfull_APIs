package com.SP.week2.SpringBootWeb.dto;


import com.SP.week2.SpringBootWeb.annotations.AgeIsPrimeNumber;
import com.SP.week2.SpringBootWeb.annotations.EmployeeRoleValidation;
import com.SP.week2.SpringBootWeb.annotations.PasswordCheck;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee can't be empty ")
    @Size(min = 3,max = 10, message = "Number of character in name should be in range of 3 to 10")
    private String name;

    @NotNull(message = "email can't be null")
    @Email(message = "email should be according to valid email rules")
    private String email;

    @NotNull(message = "age can't be null")
    @Max(value = 80, message = "age can't be greater than 80")
    @Min(value = 18, message = "age should be greater than 18")
    @AgeIsPrimeNumber
    private Integer age;

    // Learn about regular expression - regexp
    @NotNull(message = "role of employee can't be null")
    //@Pattern(regexp ="^(ADMIN|USER)$", message ="role of user can be admin or user only")
    // custom annotation that does the regexp work only
    @EmployeeRoleValidation
    private String role;// it should be either ADMIN, USER

    @PastOrPresent(message = "field in DOJ should be from past or present not future date")
    private LocalDate dateOfJoining;

    @NotNull(message = "Salary shouldn't be null")
    @Positive(message = "salary should be a positive number")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form of xxxxxx.xx")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double Salary;

    @AssertTrue(message = "this should be true")
    private Boolean active;

    @PasswordCheck
    private String password;


}
