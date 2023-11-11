import { Routes, Route } from "react-router-dom";
import  Login  from "./components/Login";
import  Home  from "./components/Home";
import  ProtectedRoute  from "./components/ProtectedRoute";
import  PublicRoute  from "./components/PublicRoute";
import Register from "./components/Register";
import  AddTraining  from "./components/AddTraining";
import  Training  from "./components/Training";
import  AdminPage  from "./components/AdminPage";
import  UserPanel  from "./components/UserPanel";


export default function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<PublicRoute><Login /></PublicRoute>} />
        <Route path="/login" element={<PublicRoute><Login /></PublicRoute>} />
        <Route path="/register" element={<PublicRoute><Register /></PublicRoute>} />

        <Route path="/home" element={ <ProtectedRoute><Home /></ProtectedRoute> }/>
        <Route path="/addtraining" element={ <ProtectedRoute><AddTraining /></ProtectedRoute> } />
        <Route path="/training/:trainingId" element={ <ProtectedRoute><Training /></ProtectedRoute> } />
        <Route path="/adminpanel" element={ <ProtectedRoute><AdminPage /></ProtectedRoute> } />
        <Route path="/userpanel" element={ <ProtectedRoute><UserPanel /></ProtectedRoute> } />
      </Routes>
    </div>
  );
}
