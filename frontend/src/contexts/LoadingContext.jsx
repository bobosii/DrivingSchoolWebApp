import React, { createContext, useContext, useState } from 'react';
import LoadingSpinner from '../components/LoadingSpinner';

const LoadingContext = createContext(null);

export const useLoading = () => {
  const context = useContext(LoadingContext);
  if (!context) {
    throw new Error('useLoading must be used within a LoadingProvider');
  }
  return context;
};

export const LoadingProvider = ({ children }) => {
  const [loading, setLoading] = useState(false);
  const [loadingMessage, setLoadingMessage] = useState('');

  const startLoading = (message = 'Loading...') => {
    setLoadingMessage(message);
    setLoading(true);
  };

  const stopLoading = () => {
    setLoading(false);
    setLoadingMessage('');
  };

  return (
    <LoadingContext.Provider
      value={{
        loading,
        loadingMessage,
        startLoading,
        stopLoading,
      }}
    >
      {children}
      {loading && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-4 rounded-lg shadow-lg">
            <LoadingSpinner size="large" />
            {loadingMessage && (
              <p className="mt-2 text-sm text-gray-600">{loadingMessage}</p>
            )}
          </div>
        </div>
      )}
    </LoadingContext.Provider>
  );
}; 