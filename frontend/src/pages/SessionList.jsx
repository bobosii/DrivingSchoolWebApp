import React, { useState, useEffect } from 'react';
import Layout from '../components/Layout';
import Table from '../components/Table';
import Modal from '../components/Modal';
import Button from '../components/Button';
import DatePicker from '../components/DatePicker';
import Input from '../components/Input';
import { api } from '../services/api';
import { toast } from 'react-toastify';

const SessionList = () => {
  const [sessions, setSessions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedSession, setSelectedSession] = useState(null);
  const [formData, setFormData] = useState({
    date: '',
    duration: '',
    notes: '',
  });

  useEffect(() => {
    fetchSessions();
  }, []);

  const fetchSessions = async () => {
    try {
      const response = await api.get('/sessions');
      setSessions(response.data);
    } catch (error) {
      toast.error('Failed to fetch sessions');
    } finally {
      setLoading(false);
    }
  };

  const handleCreateSession = async (e) => {
    e.preventDefault();
    try {
      await api.post('/sessions', formData);
      toast.success('Session created successfully');
      setIsModalOpen(false);
      resetForm();
      fetchSessions();
    } catch (error) {
      toast.error('Failed to create session');
    }
  };

  const handleUpdateStatus = async (id, status) => {
    try {
      await api.patch(`/sessions/${id}`, { status });
      toast.success(`Session ${status} successfully`);
      fetchSessions();
    } catch (error) {
      toast.error(`Failed to ${status} session`);
    }
  };

  const resetForm = () => {
    setFormData({
      date: '',
      duration: '',
      notes: '',
    });
    setSelectedSession(null);
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
      header: 'Duration',
      accessor: 'duration',
      cell: (value) => `${value} minutes`,
    },
    {
      header: 'Status',
      accessor: 'status',
      cell: (value) => (
        <span
          className={`px-2 py-1 text-xs font-medium rounded-full ${
            value === 'completed'
              ? 'bg-green-100 text-green-800'
              : value === 'scheduled'
              ? 'bg-blue-100 text-blue-800'
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
          {row.status === 'scheduled' && (
            <>
              <Button
                variant="success"
                size="small"
                onClick={() => handleUpdateStatus(value, 'completed')}
              >
                Complete
              </Button>
              <Button
                variant="danger"
                size="small"
                onClick={() => handleUpdateStatus(value, 'cancelled')}
              >
                Cancel
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
          <h1 className="text-xl font-semibold text-gray-900">Sessions</h1>
          <p className="mt-2 text-sm text-gray-700">
            A list of all your driving lesson sessions.
          </p>
        </div>
        <div className="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
          <Button onClick={() => setIsModalOpen(true)}>New Session</Button>
        </div>
      </div>

      <div className="mt-8">
        <Table
          columns={columns}
          data={sessions}
          isLoading={loading}
        />
      </div>

      <Modal
        isOpen={isModalOpen}
        onClose={() => {
          setIsModalOpen(false);
          resetForm();
        }}
        title="New Session"
      >
        <form onSubmit={handleCreateSession} className="space-y-4">
          <DatePicker
            label="Date and Time"
            name="date"
            value={formData.date}
            onChange={(e) =>
              setFormData({ ...formData, date: e.target.value })
            }
            required
          />
          <Input
            label="Duration (minutes)"
            name="duration"
            type="number"
            value={formData.duration}
            onChange={(e) =>
              setFormData({ ...formData, duration: e.target.value })
            }
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
            <Button type="submit">Create</Button>
          </div>
        </form>
      </Modal>
    </Layout>
  );
};

export default SessionList; 