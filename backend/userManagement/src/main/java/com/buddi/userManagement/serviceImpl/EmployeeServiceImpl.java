package com.buddi.userManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddi.userManagement.dto.EmployeeDto;
import com.buddi.userManagement.entity.Employee;
import com.buddi.userManagement.exception.DuplicateResourceException;
import com.buddi.userManagement.exception.ResourceNotFoundException;
import com.buddi.userManagement.repository.EmployeeRepository;
import com.buddi.userManagement.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDto mapToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .dob(employee.getDob())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }

    private Employee mapToEntity(EmployeeDto dto) {
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .dob(dto.getDob())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .build();
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return mapToDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsById(employeeDto.getId())) {
            throw new DuplicateResourceException("Employee with id " + employeeDto.getId() + " already exists");
        }
        Employee employee = mapToEntity(employeeDto);
        return mapToDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setName(employeeDto.getName());
        employee.setDob(employeeDto.getDob());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());

        return mapToDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}