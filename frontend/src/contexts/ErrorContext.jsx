import React, { createContext, useContext, useState } from 'react';
import { toast } from 'react-toastify';

const ErrorContext = createContext(null);

export const useError = () => {
  const context = useContext(ErrorContext);
  if (!context) {
    throw new Error('useError must be used within an ErrorProvider');
  }
  return context;
};

export const ErrorProvider = ({ children }) => {
  const [errors, setErrors] = useState({});

  const handleError = (error, formikHelpers = null) => {
    console.error('Error:', error);

    // Handle API errors
    if (error.response?.data) {
      const { message, errors: apiErrors } = error.response.data;

      // Show toast notification
      if (message) {
        toast.error(message);
      }

      // Set form errors if formikHelpers is provided
      if (formikHelpers && apiErrors) {
        formikHelpers.setErrors(apiErrors);
      }

      // Set global errors
      setErrors(apiErrors || {});
    } else {
      // Handle network or other errors
      toast.error('An unexpected error occurred. Please try again later.');
      setErrors({ general: 'An unexpected error occurred' });
    }
  };

  const clearErrors = () => {
    setErrors({});
  };

  return (
    <ErrorContext.Provider
      value={{
        errors,
        handleError,
        clearErrors,
      }}
    >
      {children}
    </ErrorContext.Provider>
  );
}; 