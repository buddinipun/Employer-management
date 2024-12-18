package com.buddi.userManagement.dto;


import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;
    private String name;
    private LocalDate dob;
    private String department;
    private Double salary;
}

