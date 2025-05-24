import React, { useState, useEffect } from 'react';
import Layout from '../components/Layout';
import Table from '../components/Table';
import Modal from '../components/Modal';
import Button from '../components/Button';
import DatePicker from '../components/DatePicker';
import Select from '../components/Select';
import { api } from '../services/api';
import { toast } from 'react-toastify';

const Appointments = () => {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedAppointment, setSelectedAppointment] = useState(null);
  const [formData, setFormData] = useState({
    date: '',
    instructorId: '',
  });
  const [instructors, setInstructors] = useState([]);

  useEffect(() => {
    fetchAppointments();
    fetchInstructors();
  }, []);

  const fetchAppointments = async () => {
    try {
      const response = await api.get('/appointments');
      setAppointments(response.data);
    } catch (error) {
      toast.error('Failed to fetch appointments');
    } finally {
      setLoading(false);
    }
  };

  const fetchInstructors = async () => {
    try {
      const response = await api.get('/instructors');
      setInstructors(response.data);
    } catch (error) {
      toast.error('Failed to fetch instructors');
    }
  };

  const handleCreateAppointment = async (e) => {
    e.preventDefault();
    try {
      await api.post('/appointments', formData);
      toast.success('Appointment created successfully');
      setIsModalOpen(false);
      resetForm();
      fetchAppointments();
    } catch (error) {
      toast.error('Failed to create appointment');
    }
  };

  const handleCancelAppointment = async (id) => {
    try {
      await api.delete(`/appointments/${id}`);
      toast.success('Appointment cancelled successfully');
      fetchAppointments();
    } catch (error) {
      toast.error('Failed to cancel appointment');
    }
  };

  const resetForm = () => {
    setFormData({
      date: '',
      instructorId: '',
    });
    setSelectedAppointment(null);
  };

  const columns = [
    {
      header: 'Date',
      accessor: 'date',
      cell: (value) => new Date(value).toLocaleString(),
    },
    {
      header: 'Instructor',
      accessor: 'instructor.name',
    },
    {
      header: 'Status',
      accessor: 'status',
      cell: (value) => (
        <span
          className={`px-2 py-1 text-xs font-medium rounded-full ${
            value === 'approved'
              ? 'bg-green-100 text-green-800'
              : value === 'pending'
              ? 'bg-yellow-100 text-yellow-800'
              : 'bg-red-100 text-red-800'
          }`}
        >
          {value}
        </span>
      ),
    },
    {
      header: 'Actions',
      accessor: 'id',
      cell: (value, row) => (
        <Button
          variant="danger"
          size="small"
          onClick={() => handleCancelAppointment(value)}
          disabled={row.status !== 'approved'}
        >
          Cancel
        </Button>
      ),
    },
  ];

  return (
    <Layout>
      <div className="sm:flex sm:items-center">
        <div className="sm:flex-auto">
          <h1 className="text-xl font-semibold text-gray-900">Appointments</h1>
          <p className="mt-2 text-sm text-gray-700">
            A list of all your driving lesson appointments.
          </p>
        </div>
        <div className="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
          <Button onClick={() => setIsModalOpen(true)}>New Appointment</Button>
        </div>
      </div>

      <div className="mt-8">
        <Table
          columns={columns}
          data={appointments}
          isLoading={loading}
        />
      </div>

      <Modal
        isOpen={isModalOpen}
        onClose={() => {
          setIsModalOpen(false);
          resetForm();
        }}
        title="New Appointment"
      >
        <form onSubmit={handleCreateAppointment} className="space-y-4">
          <DatePicker
            label="Date and Time"
            name="date"
            value={formData.date}
            onChange={(e) =>
              setFormData({ ...formData, date: e.target.value })
            }
            required
          />
          <Select
            label="Instructor"
            name="instructorId"
            value={formData.instructorId}
            onChange={(e) =>
              setFormData({ ...formData, instructorId: e.target.value })
            }
            options={instructors.map((instructor) => ({
              value: instructor.id,
              label: instructor.name,
            }))}
            required
          />
          <div className="flex justify-end space-x-3">
            <Button
              type="button"
              variant="secondary"
              onClick={() => {
                setIsModalOpen(false);
                resetForm();
              }}
            >
              Cancel
            </Button>
            <Button type="submit">Create</Button>
          </div>
        </form>
      </Modal>
    </Layout>
  );
};

export default Appointments; 