// src/services/employeeService.ts
import axios from 'axios';

const BASE_URL = "http://localhost:8080/api/v1";
// Define Employee Type
export interface Employee {
  id: number;
  name: string;
  dob: string;
  department: string;
  salary: number;
}

// Function to fetch all employees
export const fetchEmployees = async (): Promise<Employee[]> => {
  const token = localStorage.getItem('accessToken');
  const response = await axios.get<Employee[]>(`${BASE_URL}/api/employees`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// Function to delete an employee
export const deleteEmployee = async (id: number): Promise<void> => {
  const token = localStorage.getItem('accessToken');
  await axios.delete(`${BASE_URL}/api/employees/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
};

// Additional functions: createEmployee, updateEmployee (can be added here)
