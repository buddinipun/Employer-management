// src/services/authService.ts
import axios from 'axios';

const BASE_URL = "http://localhost:8080/api/v1";
// Define the request payload type
interface LoginPayload {
  username: string;
  password: string;
}

// Define the login response type
interface LoginResponse {
  token: string;
}

// Function to handle the login API call
export const login = async (payload: LoginPayload): Promise<LoginResponse> => {
  const response = await axios.post<LoginResponse>(`${BASE_URL}/auth/login`, payload);
  return response.data;
};
