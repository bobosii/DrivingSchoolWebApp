import * as Yup from 'yup';

export const loginSchema = Yup.object().shape({
  username: Yup.string()
    .required('Username is required'),
  password: Yup.string()
    .required('Password is required')
    .min(6, 'Password must be at least 6 characters'),
});

export const registerSchema = Yup.object().shape({
  username: Yup.string()
    .required('Username is required')
    .min(3, 'Username must be at least 3 characters'),
  email: Yup.string()
    .email('Invalid email address')
    .required('Email is required'),
  password: Yup.string()
    .required('Password is required')
    .min(6, 'Password must be at least 6 characters'),
  confirmPassword: Yup.string()
    .oneOf([Yup.ref('password'), null], 'Passwords must match')
    .required('Confirm password is required'),
  firstName: Yup.string()
    .required('First name is required'),
  lastName: Yup.string()
    .required('Last name is required'),
  phoneNumber: Yup.string()
    .required('Phone number is required')
    .matches(/^[0-9]+$/, 'Phone number must contain only digits')
    .min(10, 'Phone number must be at least 10 digits'),
});

export const appointmentSchema = Yup.object().shape({
  date: Yup.date()
    .required('Date is required')
    .min(new Date(), 'Date must be in the future'),
  instructorId: Yup.string()
    .required('Instructor is required'),
  notes: Yup.string()
    .max(500, 'Notes must be less than 500 characters'),
});

export const sessionSchema = Yup.object().shape({
  date: Yup.date()
    .required('Date is required')
    .min(new Date(), 'Date must be in the future'),
  studentId: Yup.string()
    .required('Student is required'),
  duration: Yup.number()
    .required('Duration is required')
    .min(0.5, 'Duration must be at least 0.5 hours')
    .max(8, 'Duration cannot exceed 8 hours'),
  notes: Yup.string()
    .max(500, 'Notes must be less than 500 characters'),
});

export const profileSchema = Yup.object().shape({
  firstName: Yup.string()
    .required('First name is required'),
  lastName: Yup.string()
    .required('Last name is required'),
  email: Yup.string()
    .email('Invalid email address')
    .required('Email is required'),
  phoneNumber: Yup.string()
    .required('Phone number is required')
    .matches(/^[0-9]+$/, 'Phone number must contain only digits')
    .min(10, 'Phone number must be at least 10 digits'),
  currentPassword: Yup.string()
    .when('newPassword', {
      is: (val) => val && val.length > 0,
      then: Yup.string().required('Current password is required'),
    }),
  newPassword: Yup.string()
    .min(6, 'New password must be at least 6 characters'),
  confirmPassword: Yup.string()
    .oneOf([Yup.ref('newPassword'), null], 'Passwords must match'),
}); 