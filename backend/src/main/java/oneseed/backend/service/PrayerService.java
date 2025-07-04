package oneseed.backend.service;

import oneseed.backend.model.Prayer;
import oneseed.backend.repository.PrayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrayerService {

    @Autowired
    private PrayerRepository prayerRepository;

    /**
     * Create a new prayer
     */
    public Prayer createPrayer(Prayer prayer) {
        prayer.setDateCreated(LocalDateTime.now());
        return prayerRepository.save(prayer);
    }

    /**
     * Get prayer by ID
     */
    public Optional<Prayer> getPrayerById(Long id) {
        return prayerRepository.findById(id);
    }

    /**
     * Get all prayers for a user
     */
    public List<Prayer> getPrayersByUserId(Long userId) {
        return prayerRepository.findByUserId(userId);
    }

    /**
     * Get answered prayers for a user
     */
    public List<Prayer> getAnsweredPrayersByUserId(Long userId) {
        return prayerRepository.findByUserIdAndIsAnsweredTrue(userId);
    }

    /**
     * Get unanswered prayers for a user
     */
    public List<Prayer> getUnansweredPrayersByUserId(Long userId) {
        return prayerRepository.findByUserIdAndIsAnsweredFalse(userId);
    }

    /**
     * Search prayers by title
     */
    public List<Prayer> searchPrayersByTitle(Long userId, String searchText) {
        return prayerRepository.findByTitleContaining(userId, searchText);
    }

    /**
     * Update prayer
     */
    public Prayer updatePrayer(Prayer prayer) {
        return prayerRepository.save(prayer);
    }

    /**
     * Delete prayer by ID
     */
    public void deletePrayer(Long id) {
        prayerRepository.deleteById(id);
    }

    /**
     * Mark prayer as answered
     */
    public Prayer markPrayerAsAnswered(Long prayerId) {
        Optional<Prayer> prayerOpt = prayerRepository.findById(prayerId);
        if (prayerOpt.isPresent()) {
            Prayer prayer = prayerOpt.get();
            prayer.setAnswered(true);
            prayer.setDateAnswered(LocalDateTime.now());
            return prayerRepository.save(prayer);
        }
        return null;
    }

    /**
     * Mark prayer as unanswered
     */
    public Prayer markPrayerAsUnanswered(Long prayerId) {
        Optional<Prayer> prayerOpt = prayerRepository.findById(prayerId);
        if (prayerOpt.isPresent()) {
            Prayer prayer = prayerOpt.get();
            prayer.setAnswered(false);
            prayer.setDateAnswered(null);
            return prayerRepository.save(prayer);
        }
        return null;
    }

    /**
     * Get prayer count for a user
     */
    public long getPrayerCountByUserId(Long userId) {
        return prayerRepository.countByUserId(userId);
    }

    /**
     * Get answered prayer count for a user
     */
    public long getAnsweredPrayerCountByUserId(Long userId) {
        return prayerRepository.countByUserIdAndIsAnsweredTrue(userId);
    }

    /**
     * Get unanswered prayer count for a user
     */
    public long getUnansweredPrayerCountByUserId(Long userId) {
        return prayerRepository.countByUserIdAndIsAnsweredFalse(userId);
    }

    /**
     * Get recent prayers for a user
     */
    public List<Prayer> getRecentPrayersByUserId(Long userId) {
        return prayerRepository.findRecentPrayers(userId);
    }

    /**
     * Get today's prayers for a user
     */
    public List<Prayer> getTodayPrayersByUserId(Long userId) {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return prayerRepository.findByUserIdAndDateCreatedBetween(userId, startOfDay, endOfDay);
    }

    /**
     * Get today's answered prayers for a user
     */
    public List<Prayer> getTodayAnsweredPrayersByUserId(Long userId) {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return prayerRepository.findByUserIdAndDateAnsweredBetween(userId, startOfDay, endOfDay);
    }
} 