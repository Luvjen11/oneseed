# 🌱 OneSeed - Spiritual Growth Application

A comprehensive spiritual growth application built with Spring Boot and React, designed to help users deepen their faith through daily Bible study, prayer journaling, and reflection.

## 🚀 Tech Stack

### Backend
- **Spring Boot 3.2+** - Java 17+
- **Spring Security** - Basic Authentication & Authorization
- **Spring Data JPA** - Database operations
- **MySQL 8.0** - Database
- **Maven** - Dependency management
- **Spring Boot DevTools** - Development utilities

### Frontend
- **React 18** - UI framework
- **Axios** - HTTP client
- **React Router** - Navigation
- **Tailwind CSS** - Styling
- **React Hook Form** - Form management

### External APIs
- **Bible API** - Verse retrieval
- **YouVersion API** (optional) - Additional Bible content

## 📋 MVP Features

### 1. Daily Verse
- Automatically displays a different Bible verse each day
- Option to favorite/save verses
- Integration with Bible APIs for verse retrieval

### 2. Bible Study
- Structured reading plans (7-day themes: Faith, Peace, Wisdom, etc.)
- Daily passages with devotionals
- Note-taking functionality
- Progress tracking

### 3. Prayer Journal
- Daily prayer entry creation
- Mark prayers as answered
- View prayer history and answered prayers
- Prayer categories and tags

### 4. Reflections
- Guided daily reflection questions
- Private journal entries
- Tagging system (Gratitude, Confession, Praise, etc.)
- Reflection history

### 5. User Authentication
- Secure registration and login
- Basic authentication with Spring Security
- Private user data isolation

## 🏗️ Project Structure

```
oneseed/
├── backend/                 # Spring Boot application
│   ├── src/main/java/com/walkwithgod/
│   │   ├── controller/      # REST controllers
│   │   ├── model/          # JPA entities
│   │   ├── repository/     # Data access layer
│   │   ├── service/        # Business logic
│   │   ├── config/         # Configuration classes
│   │   ├── dto/            # Data transfer objects
│   │   ├── exception/      # Custom exceptions
│   │   └── security/       # Security configuration
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   └── data.sql        # Initial data
│   └── pom.xml
├── frontend/               # React application
│   ├── src/
│   │   ├── components/     # Reusable components
│   │   ├── pages/         # Page components
│   │   ├── services/      # API services
│   │   ├── hooks/         # Custom hooks
│   │   ├── utils/         # Utility functions
│   │   └── styles/        # CSS files
│   ├── package.json
│   └── tailwind.config.js
└── README.md
```

## 🛠️ Setup Instructions

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.0
- Maven 3.6+
- Git

### Backend Setup

1. **Create Spring Boot Project**
   - Use Spring Initializer (https://start.spring.io/)
   - Project: Maven
   - Language: Java
   - Spring Boot: 3.2.x
   - Group: com.walkwithgod
   - Artifact: oneseed
   - Name: OneSeed
   - Description: Spiritual Growth Application
   - Package name: com.walkwithgod.oneseed
   - Packaging: Jar
   - Java: 17

2. **Dependencies to Add**
   ```
   Spring Web
   Spring Data JPA
   Spring Security
   MySQL Driver
   Spring Boot DevTools
   Validation
   Spring Boot Actuator
   ```

3. **Database Setup**
   ```sql
   CREATE DATABASE oneseed;
   CREATE USER 'oneseed_user'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON oneseed.* TO 'oneseed_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

4. **Configuration**
   - Update `application.yml` with database credentials
   - Configure Spring Security for basic authentication
   - Set up CORS configuration

### Frontend Setup

1. **Create React App**
   ```bash
   npx create-react-app frontend
   cd frontend
   ```

2. **Install Dependencies**
   ```bash
   npm install axios react-router-dom react-hook-form @headlessui/react @heroicons/react tailwindcss
   ```

3. **Configure Tailwind CSS**
   ```bash
   npx tailwindcss init
   ```

## 🚀 Running the Application

### Backend
```bash
cd backend
mvn spring-boot:run
```
Backend will run on: http://localhost:8080

### Frontend
```bash
cd frontend
npm start
```
Frontend will run on: http://localhost:3000

## 📊 Database Schema

### Core Entities
- **User** - User authentication and profile
- **Verse** - Bible verses and favorites
- **Prayer** - Prayer journal entries
- **Reflection** - Daily reflection entries
- **StudyPlan** - Bible study plans
- **StudyEntry** - Individual study entries

## 🔐 Security Features

- Basic authentication with Spring Security
- Password encryption with BCrypt
- Role-based access control
- CORS configuration
- Input validation and sanitization

## 🌱 Future Enhancements

- Light/Dark theme toggle
- Push notifications for daily verses
- Mood-based verse suggestions
- Social sharing features
- Mobile app (React Native)
- Offline support
- Export/import functionality

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License.

## 🙏 Acknowledgments

- Bible APIs for providing scripture content
- Spring Boot and React communities
- All contributors and supporters

---

**Built with ❤️ for spiritual growth and community** 