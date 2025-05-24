import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

type UserRole = 'STUDENT' | 'INSTRUCTOR' | 'ADMIN' | 'EMPLOYEE';

interface PrivateRouteProps {
  children: React.ReactNode;
  roles?: UserRole[];
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ children, roles = [] }) => {
  const { user, isAuthenticated } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  if (roles.length > 0 && !roles.includes(user?.role as UserRole)) {
    return <Navigate to="/dashboard/student" replace />;
  }

  return <>{children}</>;
};

export default PrivateRoute; 