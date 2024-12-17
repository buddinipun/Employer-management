import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { Table, Button, Container, Spinner } from "react-bootstrap";
import { fetchEmployees } from "../services/employeeService";
const EmployeeDashboard: React.FC = () => {
  const dispatch = useDispatch();
  const { employees, loading, error } = useSelector((state: RootState) => state.employee);

  useEffect(() => {
  //  dispatch(fetchEmployees());
  }, [dispatch]);

  const handleCreate = () => {
    console.log("Create new employee");
  };

  const handleUpdate = (id: number) => {
    console.log("Update employee with ID:", id);
  };

  const handleDelete = (id: number) => {
    console.log("Delete employee with ID:", id);
  };

  return (
    <Container className="mt-5">
      <h2 className="mb-4 text-center">Employee Dashboard</h2>

      {loading ? (
        <Spinner animation="border" role="status" className="d-block mx-auto">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      ) : error ? (
        <div className="alert alert-danger text-center">{error}</div>
      ) : (
        <>
          <Button variant="success" className="mb-3" onClick={handleCreate}>
            + Create New Employee
          </Button>
          <Table striped bordered hover responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>Department</th>
                <th>Salary</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {employees.map((employee) => (
                <tr key={employee.id}>
                  <td>{employee.id}</td>
                  <td>{employee.name}</td>
                  <td>{employee.dob}</td>
                  <td>{employee.department}</td>
                  <td>${employee.salary}</td>
                  <td>
                    <Button
                      variant="primary"
                      size="sm"
                      onClick={() => handleUpdate(employee.id)}
                      className="me-2"
                    >
                      Update
                    </Button>
                    <Button
                      variant="danger"
                      size="sm"
                      onClick={() => handleDelete(employee.id)}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </>
      )}
    </Container>
  );
};

export default EmployeeDashboard;
