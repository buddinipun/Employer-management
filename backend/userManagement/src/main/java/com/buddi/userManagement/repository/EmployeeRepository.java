package com.buddi.userManagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.buddi.userManagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom queries can be added here if needed
}

