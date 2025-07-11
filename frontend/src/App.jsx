import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import DailyVerse from "./pages/DailyVerse";
import PrayerJournal from "./pages/PrayerJournal";
import Reflections from "./pages/Reflections";

function PrivateRoute({ children }) {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/" replace />;
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dailyverse" element={
          <PrivateRoute>
            <DailyVerse />
          </PrivateRoute>
        } />
        <Route path="/prayer" element={
          <PrivateRoute>
            <PrayerJournal />
          </PrivateRoute>
        } />
        <Route path="/reflections" element={
          <PrivateRoute>
            <Reflections />
          </PrivateRoute>
        } />
      </Routes>
    </BrowserRouter>
  );
}