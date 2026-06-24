package com.SP.week2.SpringBootWeb.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "")
    @Range(min = 3,max = 10, message = "The title character should be in range of 3 and 10")
    private String title;

    @AssertTrue(message = "should be true")
    private boolean active;

    @FutureOrPresent(message = "date can be from future or present")
    private LocalDate createdAt;
}
