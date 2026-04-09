import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/api/login', formData);
      localStorage.setItem('username', res.data);
      navigate('/home');
    } catch (err) {
      setError(err.response?.data || 'Invalid credentials');
    }
  };

  return (
    <div className="auth-container">
      <div className="glass-card">
        <h2>Login</h2>
        <p className="subtitle">Welcome back! Please enter your details.</p>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Username</label>
            <input name="username" type="text" onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input name="password" type="password" onChange={handleChange} required />
          </div>
          {error && <p style={{ color: 'var(--error)', textAlign: 'center', marginBottom: '1rem' }}>{error}</p>}
          <button type="submit" className="btn">Sign In</button>
        </form>
        <div className="nav-links">
          Don't have an account? <Link to="/register">Register</Link>
        </div>
      </div>
    </div>
  );
};

export default Login;
