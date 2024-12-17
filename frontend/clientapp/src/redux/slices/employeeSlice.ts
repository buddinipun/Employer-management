import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

interface Employee {
  id: number;
  name: string;
  dob: string;
  department: string;
  salary: number;
}

interface EmployeeState {
  employees: Employee[];
  loading: boolean;
  error: string | null;
}

const initialState: EmployeeState = {
  employees: [],
  loading: false,
  error: null,
};

// Async actions for CRUD operations
export const fetchEmployees = createAsyncThunk('employee/fetchEmployees', async (_, thunkAPI) => {
  try {
    const response = await axios.get('/api/employees');
    return response.data;
  } catch (error) {
    return thunkAPI.rejectWithValue('Failed to fetch employees');
  }
});

// Create Slice
const employeeSlice = createSlice({
  name: 'employee',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchEmployees.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchEmployees.fulfilled, (state, action) => {
        state.loading = false;
        state.employees = action.payload;
      })
      .addCase(fetchEmployees.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      });
  },
});

export default employeeSlice.reducer;
