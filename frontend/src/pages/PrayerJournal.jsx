import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import '../styles/prayer-journal.css';

const verseText = "Do not be anxious about anything, but in every situation, by prayer and petition, with thanksgiving, present your requests to God. - Philippians 4:6";

const PrayerJournal = () => {
  const today = new Date();
  const dateString = today.toLocaleDateString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
  const [showModal, setShowModal] = useState(false);
  const [prayers, setPrayers] = useState([
    {
      id: 1,
      title: "Health and Healing",
      category: "Health",
      tags: ["Health"],
      date: "2024-01-15",
      text: "Lord, please watch over my family's health and grant healing where it's needed. Give us strength during this time.",
      answered: false
    }
  ]);
  const [newPrayer, setNewPrayer] = useState({
    title: '',
    category: '',
    tags: '',
    text: ''
  });
  const categories = ['Health', 'Career', 'Relationships', 'Spiritual Growth', 'Financial', 'Other'];
  const tagOptions = ['family', 'healing', 'guidance', 'career', 'love', 'faith', 'strength', 'peace'];
  const handleSubmit = (e) => {
    e.preventDefault();
    const prayer = {
      id: Date.now(),
      ...newPrayer,
      tags: newPrayer.tags.split(',').map(tag => tag.trim()),
      date: new Date().toISOString().split('T')[0],
      answered: false
    };
    setPrayers([...prayers, prayer]);
    setNewPrayer({ title: '', category: '', tags: '', text: '' });
    setShowModal(false);
  };
  const markAsAnswered = (id) => {
    setPrayers(prayers.map(prayer => 
      prayer.id === id ? { ...prayer, answered: true } : prayer
    ));
  };
  const activePrayers = prayers.filter(prayer => !prayer.answered);
  const answeredPrayers = prayers.filter(prayer => prayer.answered);
  return (
    <div className="prayer-bg">
      <Navbar />
      <div className="prayerjournal-content">
        <h2 className="prayer-greeting">Good Morning, Child of God</h2>
        <div className="prayer-date">{dateString}</div>
        <div className="prayerjournal-header">
          <h1>Prayer Journal</h1>
          <div className="prayerjournal-verse">{verseText}</div>
          <button className="new-prayer-btn" onClick={() => setShowModal(true)}>
            <span className="plus-icon">+</span> New Prayer
          </button>
        </div>
        <div className="prayerjournal-columns">
          <div className="prayerjournal-col">
            <div className="prayerjournal-tab active">
              <span className="tab-icon" role="img" aria-label="clock">⏰</span> Active Prayers ({activePrayers.length})
            </div>
            <div className="prayerjournal-list">
              {activePrayers.length === 0 ? (
                <div className="empty-state">
                  <h3>No active prayers</h3>
                  <p>Start your prayer journey by adding your first prayer request.</p>
                  <button onClick={() => setShowModal(true)}>Add Your First Prayer</button>
                </div>
              ) : (
                activePrayers.map(prayer => (
                  <PrayerCard 
                    key={prayer.id} 
                    prayer={prayer} 
                    onMarkAnswered={markAsAnswered}
                  />
                ))
              )}
            </div>
          </div>
          <div className="prayerjournal-col">
            <div className="prayerjournal-tab answered">
              <span className="tab-icon" role="img" aria-label="check">✔️</span> Answered Prayers ({answeredPrayers.length})
            </div>
            <div className="prayerjournal-list">
              {answeredPrayers.length === 0 ? (
                <div className="empty-state answered">
                  <span className="empty-check">✓</span>
                  <div>No answered prayers yet<br/>Keep praying and watch God work!</div>
                </div>
              ) : (
                answeredPrayers.map(prayer => (
                  <PrayerCard 
                    key={prayer.id} 
                    prayer={prayer} 
                    onMarkAnswered={markAsAnswered}
                    isAnswered={true}
                  />
                ))
              )}
            </div>
          </div>
        </div>
        {showModal && (
          <div className="modal-overlay" onClick={() => setShowModal(false)}>
            <div className="modal" onClick={e => e.stopPropagation()}>
              <div className="modal-header">
                <h2>New Prayer Request</h2>
                <button 
                  className="close-btn"
                  onClick={() => setShowModal(false)}
                >
                  ×
                </button>
              </div>
              <form onSubmit={handleSubmit} className="prayer-form">
                <div className="form-group">
                  <label htmlFor="title">Prayer Title *</label>
                  <input
                    type="text"
                    id="title"
                    value={newPrayer.title}
                    onChange={(e) => setNewPrayer({...newPrayer, title: e.target.value})}
                    required
                    placeholder="Brief title for your prayer"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="category">Category</label>
                  <select
                    id="category"
                    value={newPrayer.category}
                    onChange={(e) => setNewPrayer({...newPrayer, category: e.target.value})}
                  >
                    <option value="">Select a category</option>
                    {categories.map(cat => (
                      <option key={cat} value={cat}>{cat}</option>
                    ))}
                  </select>
                </div>
                <div className="form-group">
                  <label htmlFor="tags">Tags (comma-separated)</label>
                  <input
                    type="text"
                    id="tags"
                    value={newPrayer.tags}
                    onChange={(e) => setNewPrayer({...newPrayer, tags: e.target.value})}
                    placeholder="e.g., family, healing, guidance"
                  />
                  <div className="tag-suggestions">
                    {tagOptions.map(tag => (
                      <span 
                        key={tag} 
                        className="tag-suggestion"
                        onClick={() => {
                          const currentTags = newPrayer.tags ? newPrayer.tags.split(',').map(t => t.trim()) : [];
                          if (!currentTags.includes(tag)) {
                            const newTags = [...currentTags, tag].join(', ');
                            setNewPrayer({...newPrayer, tags: newTags});
                          }
                        }}
                      >
                        {tag}
                      </span>
                    ))}
                  </div>
                </div>
                <div className="form-group">
                  <label htmlFor="text">Prayer Details *</label>
                  <textarea
                    id="text"
                    value={newPrayer.text}
                    onChange={(e) => setNewPrayer({...newPrayer, text: e.target.value})}
                    required
                    placeholder="Share your prayer request..."
                    rows={4}
                  />
                </div>
                <div className="form-actions">
                  <button type="button" onClick={() => setShowModal(false)}>
                    Cancel
                  </button>
                  <button type="submit" className="submit-btn">
                    Save Prayer
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

const PrayerCard = ({ prayer, onMarkAnswered, isAnswered = false }) => {
  // Format date as DD/MM/YYYY
  const formatDate = (dateStr) => {
    const d = new Date(dateStr);
    return d.toLocaleDateString('en-GB');
  };
  return (
    <div className={`prayer-card ${isAnswered ? 'answered' : ''}`}>
      <div className="prayer-card-header">
        <div className="prayer-card-title">{prayer.title}</div>
        <div className="prayer-card-date">{formatDate(prayer.date)}</div>
      </div>
      {prayer.category && (
        <span className="prayer-card-category">{prayer.category}</span>
      )}
      <div className="prayer-card-text">{prayer.text}</div>
      {prayer.tags && prayer.tags.length > 0 && (
        <div className="prayer-card-tags">
          {prayer.tags.map(tag => (
            <span key={tag} className="tag">{tag}</span>
          ))}
        </div>
      )}
      {!isAnswered && (
        <button className="mark-answered-btn" onClick={() => onMarkAnswered(prayer.id)}>
          Mark as Answered
        </button>
      )}
      {isAnswered && (
        <div className="answered-indicator">✓ Answered Prayer</div>
      )}
    </div>
  );
};

export default PrayerJournal; 