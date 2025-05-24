import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from '../../contexts/AuthContext';
import { ErrorProvider } from '../../contexts/ErrorContext';
import { LoadingProvider } from '../../contexts/LoadingContext';
import Login from '../Login';

const renderLogin = () => {
  return render(
    <BrowserRouter>
      <ErrorProvider>
        <LoadingProvider>
          <AuthProvider>
            <Login />
          </AuthProvider>
        </LoadingProvider>
      </ErrorProvider>
    </BrowserRouter>
  );
};

describe('Login Component', () => {
  beforeEach(() => {
    // Clear all mocks before each test
    jest.clearAllMocks();
  });

  it('renders login form correctly', () => {
    renderLogin();

    // Check if form elements are rendered
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /sign in/i })).toBeInTheDocument();
    // Linkin varlığını kontrol et
    const link = screen.getByRole('link', { name: /create a new account/i });
    expect(link).toBeInTheDocument();
    expect(link).toHaveAttribute('href', '/register');
  });

  it('shows validation errors for empty fields', async () => {
    renderLogin();

    // Click sign in button without filling the form
    fireEvent.click(screen.getByRole('button', { name: /sign in/i }));

    // Check if validation errors are shown
    await waitFor(() => {
      expect(screen.getByText(/email is required/i)).toBeInTheDocument();
      expect(screen.getByText(/password is required/i)).toBeInTheDocument();
    });
  });

  it('shows validation error for invalid email', async () => {
    renderLogin();
    
    // Email input'una geçersiz email yaz
    const emailInput = screen.getByLabelText(/email/i);
    await userEvent.type(emailInput, 'invalid-email');
    
    // Input değerinin değişmesi için bekle
    await waitFor(() => {
      expect(emailInput).toHaveValue('invalid-email');
    });
    
    // Formu gönder
    const submitButton = screen.getByRole('button', { name: /sign in/i });
    await userEvent.click(submitButton);
    
    // Validation hatasının görünmesi için bekle
    await waitFor(() => {
      expect(screen.getByText(/invalid email address/i)).toBeInTheDocument();
    }, { timeout: 3000 });
  });

  it('shows validation error for short password', async () => {
    renderLogin();

    // Type short password
    await userEvent.type(screen.getByLabelText(/password/i), '123');

    // Click sign in button
    fireEvent.click(screen.getByRole('button', { name: /sign in/i }));

    // Check if password validation error is shown
    await waitFor(() => {
      expect(screen.queryByText(/password.*6 characters/i)).toBeInTheDocument();
    });
  });

  it('navigates to register page when clicking register link', () => {
    renderLogin();

    // Click register link
    const link = screen.getByRole('link', { name: /create a new account/i });
    fireEvent.click(link);

    // Check if URL is updated
    expect(window.location.pathname).toBe('/register');
  });
}); 