import React from 'react';
import { Formik, Form, Field } from 'formik';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { registerSchema } from '../validations/auth';
import Input from '../components/Input';
import Select from '../components/Select';
import Button from '../components/Button';

const Register = () => {
  const { register } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (values, { setSubmitting, setErrors }) => {
    try {
      await register(values);
      navigate('/dashboard/student');
    } catch (error) {
      if (error.response?.data?.errors) {
        setErrors(error.response.data.errors);
      }
    } finally {
      setSubmitting(false);
    }
  };

  const roleOptions = [
    { value: 'STUDENT', label: 'Student' },
    { value: 'INSTRUCTOR', label: 'Instructor' },
  ];

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8">
        <div>
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Create your account
          </h2>
          <p className="mt-2 text-center text-sm text-gray-600">
            Or{' '}
            <Link
              to="/login"
              className="font-medium text-blue-600 hover:text-blue-500"
            >
              sign in to your account
            </Link>
          </p>
        </div>

        <Formik
          initialValues={{
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            confirmPassword: '',
            role: '',
          }}
          validationSchema={registerSchema}
          onSubmit={handleSubmit}
        >
          {({ isSubmitting, errors, touched }) => (
            <Form className="mt-8 space-y-6">
              <div className="rounded-md shadow-sm space-y-4">
                <Field
                  as={Input}
                  name="firstName"
                  type="text"
                  label="First name"
                  error={touched.firstName && errors.firstName}
                />
                <Field
                  as={Input}
                  name="lastName"
                  type="text"
                  label="Last name"
                  error={touched.lastName && errors.lastName}
                />
                <Field
                  as={Input}
                  name="email"
                  type="email"
                  label="Email address"
                  error={touched.email && errors.email}
                />
                <Field
                  as={Input}
                  name="password"
                  type="password"
                  label="Password"
                  error={touched.password && errors.password}
                />
                <Field
                  as={Input}
                  name="confirmPassword"
                  type="password"
                  label="Confirm password"
                  error={touched.confirmPassword && errors.confirmPassword}
                />
                <Field
                  as={Select}
                  name="role"
                  label="Role"
                  options={roleOptions}
                  error={touched.role && errors.role}
                />
              </div>

              <div>
                <Button
                  type="submit"
                  variant="primary"
                  className="w-full"
                  disabled={isSubmitting}
                >
                  {isSubmitting ? 'Creating account...' : 'Create account'}
                </Button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
};

export default Register; 