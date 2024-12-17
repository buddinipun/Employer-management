import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login.tsx";
import EmployeeDashboard from "./pages/EmployeeDashboard.tsx";
import { Provider } from 'react-redux'; // Import Provider from react-redux
import store from './redux/store.ts';
function App() {
  return (
    <Provider store={store}>
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<EmployeeDashboard />} />
      </Routes>
    </Router>
    </Provider>
  );
}

export default App;

