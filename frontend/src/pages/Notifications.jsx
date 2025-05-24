import React, { useState, useEffect } from 'react';
import Layout from '../components/Layout';
import Table from '../components/Table';
import Button from '../components/Button';
import { api } from '../services/api';
import { websocket } from '../services/websocket';
import { toast } from 'react-toastify';
import LoadingSpinner from '../components/LoadingSpinner';

const Notifications = () => {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchNotifications();

    // Subscribe to new notifications
    websocket.subscribe('notification', handleNewNotification);

    return () => {
      websocket.unsubscribe('notification', handleNewNotification);
    };
  }, []);

  const fetchNotifications = async () => {
    try {
      const response = await api.get('/notifications');
      setNotifications(response.data);
    } catch (error) {
      toast.error('Failed to fetch notifications');
    } finally {
      setLoading(false);
    }
  };

  const handleNewNotification = (data) => {
    setNotifications((prev) => [data, ...prev]);
  };

  const markAsRead = async (id) => {
    try {
      await api.put(`/notifications/${id}/read`);
      setNotifications((prev) =>
        prev.map((notification) =>
          notification.id === id
            ? { ...notification, read: true }
            : notification
        )
      );
      toast.success('Notification marked as read');
    } catch (error) {
      toast.error('Failed to mark notification as read');
    }
  };

  const deleteNotification = async (id) => {
    try {
      await api.delete(`/notifications/${id}`);
      setNotifications((prev) =>
        prev.filter((notification) => notification.id !== id)
      );
      toast.success('Notification deleted');
    } catch (error) {
      toast.error('Failed to delete notification');
    }
  };

  const columns = [
    {
      header: 'Date',
      accessor: 'createdAt',
      cell: (value) => new Date(value).toLocaleString(),
    },
    {
      header: 'Type',
      accessor: 'type',
      cell: (value) => (
        <span className="capitalize">{value.toLowerCase()}</span>
      ),
    },
    {
      header: 'Message',
      accessor: 'message',
    },
    {
      header: 'Status',
      accessor: 'read',
      cell: (value) => (
        <span
          className={`px-2 py-1 text-xs font-semibold rounded-full ${
            value
              ? 'bg-green-100 text-green-800'
              : 'bg-yellow-100 text-yellow-800'
          }`}
        >
          {value ? 'Read' : 'Unread'}
        </span>
      ),
    },
    {
      header: 'Actions',
      accessor: 'id',
      cell: (id, row) => (
        <div className="flex space-x-2">
          {!row.read && (
            <Button
              variant="secondary"
              size="sm"
              onClick={() => markAsRead(id)}
            >
              Mark as Read
            </Button>
          )}
          <Button
            variant="danger"
            size="sm"
            onClick={() => deleteNotification(id)}
          >
            Delete
          </Button>
        </div>
      ),
    },
  ];

  if (loading) {
    return (
      <Layout>
        <div className="flex justify-center items-center h-64">
          <LoadingSpinner size="large" />
        </div>
      </Layout>
    );
  }

  return (
    <Layout>
      <div className="bg-white shadow rounded-lg">
        <div className="px-4 py-5 sm:p-6">
          <h2 className="text-lg font-medium text-gray-900 mb-4">
            Notifications
          </h2>
          <Table
            columns={columns}
            data={notifications}
            isLoading={loading}
          />
        </div>
      </div>
    </Layout>
  );
};

export default Notifications; 