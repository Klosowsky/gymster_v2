import { Navigate } from "react-router-dom";

const PublicRoute = ({ children }) => {
  if (!localStorage.getItem("token")) {
    return children;
  }

  return <Navigate to="/home" />;
};

export default PublicRoute