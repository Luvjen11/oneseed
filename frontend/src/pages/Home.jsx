import React, { useState } from "react";
import "../styles/home.css";
import logo from "../assets/oneseed-logo-trans.png";
import title from "../assets/oneseed-title.png";
import LoginModal from "./LoginModal";
import SignUpModal from "./SignUpModal";

export default function Home() {
  const [showLogin, setShowLogin] = useState(false);
  const [showSignUp, setShowSignUp] = useState(false);

  const openLogin = () => {
    setShowSignUp(false);
    setShowLogin(true);
  };
  const openSignUp = () => {
    setShowLogin(false);
    setShowSignUp(true);
  };
  const closeModals = () => {
    setShowLogin(false);
    setShowSignUp(false);
  };

  return (
    <div className="home-bg">
      <header className="home-header">
        <div className="plant-icon">
          <img className="plant-icon-img" src={logo} alt="plant" />
        </div>
        <h1 className="home-title-text">
          <img className="title-img" src={title} alt="title" />
        </h1>
        <p className="home-subtitle">
        Nurture your spiritual growth through daily scripture, prayer, and reflection.<br />
        Plant seeds of faith that will grow for eternity.
      </p>
        <button className="home-btn-main" onClick={openLogin}>Begin Your Journey</button>
      </header>
      <section className="home-features-row">
        <FeatureCard
          icon={<span className="feature-icon-bg blue" role="img" aria-label="book">📖</span>}
          title="Daily Scripture"
          color="#15803d"
          desc="Start each day with God's word. Receive carefully selected verses that speak to your heart."
        />
        <FeatureCard
          icon={<span className="feature-icon-bg purple" role="img" aria-label="heart">💜</span>}
          title="Prayer Journal"
          color="#9333ea"
          desc="Record your prayers and witness God's faithfulness as you track answered prayers."
        />
        <FeatureCard
          icon={<span className="feature-icon-bg yellow" role="img" aria-label="leaf">🍃</span>}
          title="Reflections"
          color="#eab308"
          desc="Pause and reflect on God's goodness through guided questions and personal insights."
        />
      </section>
      <blockquote className="home-quote">
        <span>
          "Unless a grain of wheat falls to the ground and dies, it remains only a single seed. But if it dies, it produces many seeds."
        </span>
        <br />
        <span className="home-verse">John 12:24</span>
      </blockquote>
      {showLogin && <LoginModal onClose={closeModals} onShowSignUp={openSignUp} />}
      {showSignUp && <SignUpModal onClose={closeModals} onShowLogin={openLogin} />}
    </div>
  );
}

function FeatureCard({ icon, title, color, desc }) {
  return (
    <div className="feature-card2">
      <div className="feature-icon" style={{ color }}>{icon}</div>
      <h3 className="feature-title2" style={{ color }}>{title}</h3>
      <p className="feature-desc2">{desc}</p>
    </div>
  );
}