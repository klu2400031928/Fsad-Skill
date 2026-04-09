import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, Link, useLocation } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';
import Profile from './components/Profile';

const Navbar = () => {
  const location = useLocation();
  const username = localStorage.getItem('username');

  if (!username || ['/login', '/register'].includes(location.pathname)) return null;

  return (
    <nav className="navbar">
      <div className="logo">AuthApp</div>
      <div className="nav-items">
        <Link to="/home" className={location.pathname === '/home' ? 'active' : ''}>Home</Link>
        <Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}>Profile</Link>
        <a href="#" onClick={(e) => {
          e.preventDefault();
          localStorage.removeItem('username');
          window.location.href = '/login';
        }}>Logout</a>
      </div>
    </nav>
  );
};

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}

export default App;
