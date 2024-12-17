import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Container, Form, Button, Alert } from "react-bootstrap";
import { login } from "../services/authService";
const Login: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  // Handle form submission
  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await login({ username, password });
      localStorage.setItem("accessToken", response.token);
      navigate("/dashboard"); // Redirect to Employee Dashboard
    } catch (err) {
      setError("Invalid username or password");
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="text-center mb-4">Login</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleLogin} className="mx-auto" style={{ maxWidth: "400px" }}>
        <Form.Group controlId="username">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="password" className="mt-3">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="mt-4 w-100">
          Login
        </Button>
      </Form>
    </Container>
  );
};

export default Login;
