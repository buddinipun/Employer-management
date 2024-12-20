import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import {login as loginService} from "../../services/authService.ts";

interface LoginState {
    token: string | null;
    status: "idle" | "loading" | "succeeded" | "failed";
    error: string | null;
}

const initialState: LoginState = {
    token: null,
    status: "idle",
    error: null,
}

// Asunchrnous Thunk for login

export const login = createAsyncThunk(
    "login/authenticate",
    async (credentials: { username: string; password: string }, { rejectWithValue }) => {
      try {
        const response = await loginService(credentials);
        return response.token;
      } catch (error: any) {
        return rejectWithValue(error.response?.data?.message || "Login failed");
      }
    }
  );


    const loginSlice = createSlice({
        name: "login",
        initialState,
        reducers: {
          logout: (state) => {
            state.token = null;
            localStorage.removeItem("accessToken");
          },
        },
        extraReducers: (builder) => {
          builder
            .addCase(login.pending, (state) => {
              state.status = "loading";
              state.error = null;
            })
            .addCase(login.fulfilled, (state, action) => {
              state.status = "succeeded";
              state.token = action.payload;
              localStorage.setItem("accessToken", action.payload);
            })
            .addCase(login.rejected, (state, action) => {
              state.status = "failed";
              state.error = action.payload as string;
            });
        },
      });

export const { logout } = loginSlice.actions;

export default loginSlice.reducer;