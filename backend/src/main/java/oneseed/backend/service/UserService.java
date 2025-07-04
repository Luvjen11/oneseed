package oneseed.backend.service;

import oneseed.backend.model.User;
import oneseed.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user
     */
    public User createUser(User user) {
        user.setDateJoined(LocalDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Get user by ID
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Get user by username
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Get user by email
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Update user
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Delete user by ID
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Check if username exists
     */
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Check if email exists
     */
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Get users by date joined after
     */
    public List<User> getUsersByDateJoinedAfter(LocalDateTime date) {
        return userRepository.findByDateJoinedAfter(date);
    }

    /**
     * Get total user count
     */
    public long getTotalUserCount() {
        return userRepository.count();
    }
} 