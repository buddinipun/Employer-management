package com.buddi.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buddi.userManagement.dto.EmployeeDto;
import com.buddi.userManagement.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Management", description = "APIs for managing employee data")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Retrieve all employees", description = "Fetches a list of all employees")
    @ApiResponse(responseCode = "200", description = "List of employees retrieved successfully")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Operation(summary = "Retrieve employee by ID", description = "Fetches details of an employee by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Operation(summary = "Create a new employee", description = "Adds a new employee to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee created successfully", 
                     content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @Valid @RequestBody(description = "Employee details", required = true, content = @Content(
                schema = @Schema(implementation = EmployeeDto.class))) EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @Operation(summary = "Update an existing employee", description = "Updates details of an existing employee by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee updated successfully", 
                     content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id, 
            @Valid @RequestBody(description = "Updated employee details", required = true, content = @Content(
                schema = @Schema(implementation = EmployeeDto.class))) EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
    }

    @Operation(summary = "Delete an employee", description = "Removes an employee from the system by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}


