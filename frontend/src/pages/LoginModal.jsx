import React, { useRef, useState } from "react";
import "../styles/login-modal.css";
import plantIcon from "../assets/oneseed-logo-trans.png";
import { login } from "../services/authservice";
import { useNavigate } from "react-router-dom";

export default function LoginModal({ onClose, onShowSignUp }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [isError, setIsError] = useState(false);
  const modalRef = useRef();
  const navigate = useNavigate();

  const handleOverlayClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setIsError(false);
    try {
      await login(username, password);
      setMessage("Login successful!");
      onClose();
      navigate("/dailyverse");
    } catch (error) {
      setMessage(error.response?.data?.message || "Login failed. Please check your credentials.");
      setIsError(true);
    }
  };

  return (
    <div className="modal-overlay" onClick={handleOverlayClick}>
      <div className="login-modal" ref={modalRef}>
        <button className="modal-close" onClick={onClose}>&times;</button>
        <img src={plantIcon} alt="plant" className="modal-plant-icon" />
        <h2 className="modal-title">Welcome Back</h2>
        <p className="modal-subtitle">Continue your spiritual journey</p>
        {message && (
          <div className={`modal-message ${isError ? "error" : "success"}`}>{message}</div>
        )}
        <form className="modal-form center-form" onSubmit={handleSubmit}>
          <label>Username
            <input type="text" placeholder="Enter your username" className="modal-input" value={username} onChange={e => setUsername(e.target.value)} required />
          </label>
          <label>Password
            <input type="password" placeholder="Enter your password" className="modal-input" value={password} onChange={e => setPassword(e.target.value)} required />
          </label>
          <button type="submit" className="modal-btn-main">Sign In</button>
        </form>
        <div className="modal-footer">
          <span>Don't have an account? <a href="#" className="modal-link" onClick={e => {e.preventDefault(); onShowSignUp();}}>Sign up</a></span>
        </div>
        <div className="modal-terms">
          By continuing, you agree to grow in faith and fellowship with God through OneSeed.
        </div>
      </div>
    </div>
  );
} 