import React from "react";
import Navbar from "../components/Navbar";
import "../styles/dailyverse.css";

export default function DailyVerse() {
  const today = new Date();
  const dateString = today.toLocaleDateString("en-US", { weekday: "long", year: "numeric", month: "long", day: "numeric" });

  return (
    <div className="dailyverse-bg">
      <Navbar />
      <div className="dailyverse-content">
        <h2 className="dailyverse-greeting">Good Morning, Child of God</h2>
        <div className="dailyverse-date">{dateString}</div>
        <div className="verse-card">
          <div className="verse-card-header">
            <span className="verse-status-dot" />
            <span className="verse-status-label">TODAY'S VERSE</span>
            <span className="verse-status-dot" />
          </div>
          <div className="verse-card-title">Hope &amp; Future</div>
          <blockquote className="verse-card-quote">
            <span className="verse-card-verse">
              "For I know the plans I have for you, declares the Lord, plans to prosper you and not to harm you, to give you hope and a future."
            </span>
            <br />
            <span className="verse-card-ref">— Jeremiah 29:11</span>
          </blockquote>
          <div className="verse-card-actions">
            <button className="verse-btn"><span role="img" aria-label="favorite">♡</span> Favorite</button>
            <button className="verse-btn"><span role="img" aria-label="share">🔗</span> Share</button>
            <button className="verse-btn verse-btn-green">New Verse</button>
          </div>
        </div>
        <div className="reflection-card">
          <div className="reflection-title">Reflection Question</div>
          <div className="reflection-question">
            How can you apply this verse to your life today? What is God speaking to your heart through these words?
          </div>
        </div>
      </div>
    </div>
  );
} 