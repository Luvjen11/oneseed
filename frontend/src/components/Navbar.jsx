import React from "react";
import logo from "../assets/oneseed-logo-trans.png";
import "../styles/navbar.css";
import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };
  return (
    <nav className="navbar">
      <div className="navbar-left">
        <img src={logo} alt="OneSeed logo" className="navbar-logo" />
        <span className="navbar-title">OneSeed</span>
      </div>
      <div className="navbar-links">
        <Link to="/dailyverse" className="navbar-link">Daily Verse</Link>
        <Link to="/prayer" className="navbar-link">Prayer Journal</Link>
        <Link to="/reflections" className="navbar-link">Reflections</Link>
        <button className="navbar-link navbar-logout" onClick={handleLogout}>Logout</button>
      </div>
    </nav>
  );
} 