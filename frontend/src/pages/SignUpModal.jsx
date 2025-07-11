// Uses login-modal.css for styling shared with LoginModal
import React, { useRef, useState } from "react";
import "../styles/login-modal.css";
import plantIcon from "../assets/oneseed-logo-trans.png";
import { signup } from "../services/authservice";

export default function SignUpModal({ onClose, onShowLogin }) {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState("");
  const [isError, setIsError] = useState(false);
  const modalRef = useRef();

  const handleOverlayClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setIsError(false);
    if (password !== confirmPassword) {
      setMessage("Passwords do not match.");
      setIsError(true);
      return;
    }
    try {
      await signup({ username, email, password });
      setMessage("Sign up successful! You can now log in.");
      setTimeout(() => {
        onShowLogin();
      }, 1200);
    } catch (error) {
      setMessage(error.response?.data?.message || "Sign up failed. Please try again.");
      setIsError(true);
    }
  };

  return (
    <div className="modal-overlay" onClick={handleOverlayClick}>
      <div className="login-modal" ref={modalRef}>
        <button className="modal-close" onClick={onClose}>&times;</button>
        <img src={plantIcon} alt="plant" className="modal-plant-icon" />
        <h2 className="modal-title">Create Account</h2>
        <p className="modal-subtitle">Start your spiritual journey with OneSeed</p>
        {message && (
          <div className={`modal-message ${isError ? "error" : "success"}`}>{message}</div>
        )}
        <form className="modal-form center-form" onSubmit={handleSubmit}>
          <label>Username
            <input type="text" placeholder="Enter your username" className="modal-input" value={username} onChange={e => setUsername(e.target.value)} required />
          </label>
          <label>Email
            <input type="email" placeholder="Enter your email" className="modal-input" value={email} onChange={e => setEmail(e.target.value)} required />
          </label>
          <label>Password
            <input type="password" placeholder="Create a password" className="modal-input" value={password} onChange={e => setPassword(e.target.value)} required />
          </label>
          <label>Confirm Password
            <input type="password" placeholder="Confirm your password" className="modal-input" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} required />
          </label>
          <button type="submit" className="modal-btn-main">Sign Up</button>
        </form>
        <div className="modal-footer">
          <span>Already have an account? <a href="#" className="modal-link" onClick={e => {e.preventDefault(); onShowLogin();}}>Sign in</a></span>
        </div>
        <div className="modal-terms">
          By signing up, you agree to grow in faith and fellowship with God through OneSeed.
        </div>
      </div>
    </div>
  );
} 