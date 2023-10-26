import { Routes, Route } from "react-router-dom";
import  Login  from "./components/Login";
import  Home  from "./components/Home";
import  ProtectedRoute  from "./components/ProtectedRoute";
import  PublicRoute  from "./components/PublicRoute";
import Register from "./components/Register";


export default function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<PublicRoute><Login /></PublicRoute>} />
        <Route path="/login" element={<PublicRoute><Login /></PublicRoute>} />
        <Route path="/register" element={<PublicRoute><Register /></PublicRoute>} />

        <Route path="/home" element={ <ProtectedRoute><Home /></ProtectedRoute> }/>
      </Routes>
    </div>
  );
}
