import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import '../styles/reflections.css';

const verseText = "Search me, God, and know my heart; test me and know my anxious thoughts. - Psalm 139:23";

const todaysQuestion = "How can you draw closer to God in your daily routine?";
const tagOptions = [
  'Gratitude', 'Growth', 'Family', 'Challenges', 'Praise', 'Confession', 'Hope', 'Love', 'Peace'
];

const Reflections = () => {
  const today = new Date();
  const dateString = today.toLocaleDateString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
  const [showModal, setShowModal] = useState(false);
  const [reflectionText, setReflectionText] = useState("");
  const [selectedTags, setSelectedTags] = useState([]);
  const [reflections, setReflections] = useState([
    {
      id: 1,
      question: "What are three things you're grateful for today?",
      entry: "I'm grateful for my family's health, the beautiful sunrise this morning, and the opportunity to grow closer to God through His word.",
      tags: ["Gratitude", "Family"],
      date: "2024-01-20",
      type: "guided"
    }
  ]);
  // Format date as DD/MM/YYYY
  const formatDate = (dateStr) => {
    const d = new Date(dateStr);
    return d.toLocaleDateString('en-GB');
  };
  const handleTagClick = (tag) => {
    setSelectedTags((prev) => prev.includes(tag) ? prev.filter(t => t !== tag) : [...prev, tag]);
  };
  const handleCancel = () => {
    setReflectionText("");
    setSelectedTags([]);
    setShowModal(false);
  };
  const handleSave = (e) => {
    e.preventDefault();
    if (!reflectionText.trim()) return;
    setReflections([
      {
        id: Date.now(),
        question: todaysQuestion,
        entry: reflectionText,
        tags: selectedTags,
        date: new Date().toISOString().split('T')[0],
        type: 'guided'
      },
      ...reflections
    ]);
    setReflectionText("");
    setSelectedTags([]);
    setShowModal(false);
  };
  return (
    <div className="reflections-bg">
      <Navbar />
      <div className="reflections-content">
        <h2 className="reflections-greeting">Good Morning, Child of God!</h2>
        <div className="reflections-date">{dateString}</div>
        <div className="reflections-header-row">
          <div>
            <h1 className="reflections-title">Spiritual Reflections</h1>
            <div className="reflections-verse">{verseText}</div>
          </div>
          <button className="new-reflection-btn" onClick={() => setShowModal(true)}>
            <span className="plus-icon">+</span> New Reflection
          </button>
        </div>
        {/* Modal for New Reflection */}
        {showModal && (
          <div className="modal-overlay" onClick={handleCancel}>
            <div className="todays-reflection-card modal-card" onClick={e => e.stopPropagation()}>
              <div className="todays-reflection-header">
                <span className="star-icon" role="img" aria-label="star">✨</span>
                <span className="todays-reflection-title">Today's Reflection</span>
              </div>
              <form className="todays-reflection-form" onSubmit={handleSave}>
                <div className="todays-reflection-question-label">Reflection Question:</div>
                <div className="todays-reflection-question-box">
                  <em>{todaysQuestion}</em>
                </div>
                <label className="todays-reflection-label" htmlFor="reflection-text">Your Reflection</label>
                <textarea
                  id="reflection-text"
                  className="todays-reflection-textarea"
                  value={reflectionText}
                  onChange={e => setReflectionText(e.target.value)}
                  placeholder="Take a moment to reflect and share your thoughts..."
                  rows={4}
                />
                <div className="todays-reflection-tags-label">Tags (optional)</div>
                <div className="todays-reflection-tags">
                  {tagOptions.map(tag => (
                    <button
                      type="button"
                      key={tag}
                      className={`tag-pill${selectedTags.includes(tag) ? ' selected' : ''}`}
                      onClick={() => handleTagClick(tag)}
                    >
                      {tag}
                    </button>
                  ))}
                </div>
                <div className="todays-reflection-actions">
                  <button type="button" className="cancel-btn" onClick={handleCancel}>Cancel</button>
                  <button type="submit" className="save-btn">Save Reflection</button>
                </div>
              </form>
            </div>
          </div>
        )}
        {/* Journey Section */}
        <div className="reflections-journey-label">
          <span className="leaf-icon" role="img" aria-label="leaf">🪴</span> Your Reflection Journey ({reflections.length})
        </div>
        <div className="reflection-journey-list">
          {reflections.length === 0 ? (
            <div className="empty-state">
              <h3>No reflections yet</h3>
              <p>Begin your reflection journey by adding your first entry.</p>
            </div>
          ) : (
            reflections.map(reflection => (
              <ReflectionCard key={reflection.id} reflection={reflection} formatDate={formatDate} />
            ))
          )}
        </div>
      </div>
    </div>
  );
};

const ReflectionCard = ({ reflection, formatDate }) => {
  return (
    <div className="reflection-card-green">
      <div className="reflection-card-header">
        <div className="reflection-card-question">{reflection.question}</div>
        <div className="reflection-card-date">{formatDate(reflection.date)}</div>
      </div>
      {reflection.tags && reflection.tags.length > 0 && (
        <div className="reflection-card-tags">
          {reflection.tags.map(tag => (
            <span key={tag} className="tag-green">{tag}</span>
          ))}
        </div>
      )}
      <div className="reflection-card-entry">{reflection.entry}</div>
    </div>
  );
};

export default Reflections; 