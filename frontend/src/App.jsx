import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import DailyVerse from "./pages/DailyVerse";
// import PrayerJournal from "./pages/PrayerJournal"; // placeholder
// import Reflections from "./pages/Reflections"; // placeholder

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
        {/* <Route path="/prayer" element={<PrayerJournal />} /> */}
        {/* <Route path="/reflections" element={<Reflections />} /> */}
      </Routes>
    </BrowserRouter>
  );
}