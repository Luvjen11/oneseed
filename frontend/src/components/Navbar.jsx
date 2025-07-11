import React from "react";
import logo from "../assets/oneseed-logo-trans.png";
import "../styles/navbar.css";
import { NavLink, useNavigate } from "react-router-dom";

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
        <NavLink to="/dailyverse" className={({ isActive }) => isActive ? "navbar-link active" : "navbar-link"}>Daily Verse</NavLink>
        <NavLink to="/prayer" className={({ isActive }) => isActive ? "navbar-link active" : "navbar-link"}>Prayer Journal</NavLink>
        <NavLink to="/reflections" className={({ isActive }) => isActive ? "navbar-link active" : "navbar-link"}>Reflections</NavLink>
        <button className="navbar-link navbar-logout" onClick={handleLogout}>Logout</button>
      </div>
    </nav>
  );
} 