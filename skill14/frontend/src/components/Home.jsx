import React, { useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();
  const username = localStorage.getItem('username');

  useEffect(() => {
    if (!username) {
      navigate('/login');
    }
  }, [username, navigate]);

  if (!username) return null;

  return (
    <div className="auth-container">
      <div className="glass-card" style={{ textAlign: 'center' }}>
        <h2>Welcome, {username}!</h2>
        <p className="subtitle">You have successfully logged into the system.</p>
        <div style={{ display: 'grid', gap: '1rem', marginTop: '2rem' }}>
          <Link to="/profile" className="btn" style={{ textDecoration: 'none', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            View Profile
          </Link>
          <button className="btn logout-btn" onClick={() => {
            localStorage.removeItem('username');
            navigate('/login');
          }}>
            Logout
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;
