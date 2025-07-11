import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

// Store token in localStorage
export const storeToken = (token) => localStorage.setItem('token', token);
export const getToken = () => localStorage.getItem('token');

// Login API Call (Basic Auth)
export const login = (username, password) => {
  const token = 'Basic ' + window.btoa(username + ':' + password);
  storeToken(token);
  // Use POST for login
  return axios.post(`${API_BASE_URL}/oneseed/login`, {}, {
    headers: { 'Authorization': token }
  });
};

// Signup API Call
export const signup = ({ username, email, password }) => {
  return axios.post(`${API_BASE_URL}/oneseed/register`, { username, email, password });
}; 