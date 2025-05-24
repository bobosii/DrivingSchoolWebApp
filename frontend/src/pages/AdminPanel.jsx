import React, { useState, useEffect } from 'react';
import Layout from '../components/Layout';
import Table from '../components/Table';
import Modal from '../components/Modal';
import Button from '../components/Button';
import Select from '../components/Select';
import Input from '../components/Input';
import { api } from '../services/api';
import { toast } from 'react-toastify';

const AdminPanel = () => {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedAppointment, setSelectedAppointment] = useState(null);
  const [instructors, setInstructors] = useState([]);
  const [formData, setFormData] = useState({
    instructorId: '',
    notes: '',
  });

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

  const handleApprove = async (id) => {
    setSelectedAppointment(id);
    setIsModalOpen(true);
  };

  const handleReject = async (id) => {
    try {
      await api.patch(`/appointments/${id}`, { status: 'rejected' });
      toast.success('Appointment rejected successfully');
      fetchAppointments();
    } catch (error) {
      toast.error('Failed to reject appointment');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.patch(`/appointments/${selectedAppointment}`, {
        status: 'approved',
        instructorId: formData.instructorId,
        notes: formData.notes,
      });
      toast.success('Appointment approved successfully');
      setIsModalOpen(false);
      resetForm();
      fetchAppointments();
    } catch (error) {
      toast.error('Failed to approve appointment');
    }
  };

  const resetForm = () => {
    setFormData({
      instructorId: '',
      notes: '',
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
      header: 'Student',
      accessor: 'student.name',
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
        <div className="flex space-x-2">
          {row.status === 'pending' && (
            <>
              <Button
                variant="success"
                size="small"
                onClick={() => handleApprove(value)}
              >
                Approve
              </Button>
              <Button
                variant="danger"
                size="small"
                onClick={() => handleReject(value)}
              >
                Reject
              </Button>
            </>
          )}
        </div>
      ),
    },
  ];

  return (
    <Layout>
      <div className="sm:flex sm:items-center">
        <div className="sm:flex-auto">
          <h1 className="text-xl font-semibold text-gray-900">Admin Panel</h1>
          <p className="mt-2 text-sm text-gray-700">
            Manage and approve driving lesson appointments.
          </p>
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
        title="Approve Appointment"
      >
        <form onSubmit={handleSubmit} className="space-y-4">
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
          <Input
            label="Notes"
            name="notes"
            type="textarea"
            value={formData.notes}
            onChange={(e) =>
              setFormData({ ...formData, notes: e.target.value })
            }
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
            <Button type="submit">Approve</Button>
          </div>
        </form>
      </Modal>
    </Layout>
  );
};

export default AdminPanel; 