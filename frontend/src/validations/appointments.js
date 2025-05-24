import * as Yup from 'yup';

export const appointmentSchema = Yup.object().shape({
  date: Yup.date()
    .min(new Date(), 'Appointment date must be in the future')
    .required('Date is required'),
  instructorId: Yup.string()
    .required('Instructor is required'),
  notes: Yup.string()
    .max(500, 'Notes must be at most 500 characters'),
});

export const sessionSchema = Yup.object().shape({
  date: Yup.date()
    .min(new Date(), 'Session date must be in the future')
    .required('Date is required'),
  studentId: Yup.string()
    .required('Student is required'),
  duration: Yup.number()
    .min(30, 'Duration must be at least 30 minutes')
    .max(180, 'Duration must be at most 180 minutes')
    .required('Duration is required'),
  notes: Yup.string()
    .max(500, 'Notes must be at most 500 characters'),
}); 