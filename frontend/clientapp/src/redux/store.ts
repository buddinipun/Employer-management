import { configureStore } from '@reduxjs/toolkit';
import employeeReducer from './slices/employeeSlice.ts';
import loginReducer from "./slices/loginSlice.ts";

const store = configureStore({
  reducer: {
    employee: employeeReducer,
    login: loginReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export default store;
