import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

const Profile = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const username = localStorage.getItem('username');

  useEffect(() => {
    if (!username) {
      navigate('/login');
      return;
    }

    const fetchProfile = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/api/profile/${username}`);
        setUser(res.data);
      } catch (err) {
        console.error('Error fetching profile', err);
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [username, navigate]);

  if (loading) return <div className="auth-container"><p>Loading profile...</p></div>;
  if (!user) return <div className="auth-container"><p>User not found</p></div>;

  return (
    <div className="auth-container">
      <div className="glass-card">
        <h2>User Profile</h2>
        <p className="subtitle">Detailed information about your account</p>
        
        <div className="profile-info">
          <div className="profile-item">
            <span className="profile-label">Username</span>
            <span className="profile-value">{user.username}</span>
          </div>
          <div className="profile-item">
            <span className="profile-label">Full Name</span>
            <span className="profile-value">{user.fullName}</span>
          </div>
          <div className="profile-item">
            <span className="profile-label">Email</span>
            <span className="profile-value">{user.email}</span>
          </div>
          <div className="profile-item">
            <span className="profile-label">User ID</span>
            <span className="profile-value"># {user.id}</span>
          </div>
        </div>

        <div style={{ display: 'grid', gap: '1rem', marginTop: '2rem' }}>
          <Link to="/home" className="btn" style={{ textDecoration: 'none', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            Back to Home
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

export default Profile;
