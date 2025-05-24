import { toast } from 'react-hot-toast';

class WebSocketService {
  constructor() {
    this.socket = null;
    this.subscribers = new Set();
  }

  connect() {
    const token = localStorage.getItem('token');
    if (!token) return;

    const wsUrl = `${process.env.REACT_APP_WS_URL || 'ws://localhost:8080'}/ws?token=${token}`;
    this.socket = new WebSocket(wsUrl);

    this.socket.onopen = () => {
      console.log('WebSocket connection established');
    };

    this.socket.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data);
        this.notifySubscribers(data);
      } catch (error) {
        console.error('Error parsing WebSocket message:', error);
      }
    };

    this.socket.onclose = () => {
      console.log('WebSocket connection closed');
      // Attempt to reconnect after 5 seconds
      setTimeout(() => this.connect(), 5000);
    };

    this.socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };
  }

  disconnect() {
    if (this.socket) {
      this.socket.close();
      this.socket = null;
    }
  }

  subscribe(callback) {
    this.subscribers.add(callback);
    return () => this.subscribers.delete(callback);
  }

  notifySubscribers(data) {
    this.subscribers.forEach((callback) => {
      try {
        callback(data);
      } catch (error) {
        console.error('Error in WebSocket subscriber:', error);
      }
    });
  }

  sendMessage(message) {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(JSON.stringify(message));
    } else {
      console.error('WebSocket is not connected');
    }
  }
}

const websocketService = new WebSocketService();

export const initializeWebSocket = () => {
  websocketService.connect();
};

export const disconnectWebSocket = () => {
  websocketService.disconnect();
};

export const subscribeToNotifications = (callback) => {
  return websocketService.subscribe((data) => {
    if (data.type === 'NOTIFICATION') {
      toast(data.message, {
        icon: '🔔',
        duration: 5000,
      });
      callback(data);
    }
  });
};

export const sendWebSocketMessage = (message) => {
  websocketService.sendMessage(message);
}; 