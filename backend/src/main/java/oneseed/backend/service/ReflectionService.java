package oneseed.backend.service;

import oneseed.backend.model.Reflection;
import oneseed.backend.repository.ReflectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReflectionService {

    @Autowired
    private ReflectionRepository reflectionRepository;

    /**
     * Create a new reflection
     */
    public Reflection createReflection(Reflection reflection) {
        reflection.setDateCreated(LocalDateTime.now());
        return reflectionRepository.save(reflection);
    }

    /**
     * Get reflection by ID
     */
    public Optional<Reflection> getReflectionById(Long id) {
        return reflectionRepository.findById(id);
    }

    /**
     * Get all reflections for a user
     */
    public List<Reflection> getReflectionsByUserId(Long userId) {
        return reflectionRepository.findByUserId(userId);
    }

    /**
     * Get reflections by prompt for a user
     */
    public List<Reflection> getReflectionsByPromptAndUserId(String prompt, Long userId) {
        return reflectionRepository.findByPromptAndUserId(prompt, userId);
    }

    /**
     * Update reflection
     */
    public Reflection updateReflection(Reflection reflection) {
        return reflectionRepository.save(reflection);
    }

    /**
     * Delete reflection by ID
     */
    public void deleteReflection(Long id) {
        reflectionRepository.deleteById(id);
    }

    /**
     * Get reflection count for a user
     */
    public long getReflectionCountByUserId(Long userId) {
        return reflectionRepository.countByUserId(userId);
    }

    /**
     * Get recent reflections for a user
     */
    public List<Reflection> getRecentReflectionsByUserId(Long userId) {
        return reflectionRepository.findRecentReflections(userId);
    }

    /**
     * Get today's reflections for a user
     */
    public List<Reflection> getTodayReflectionsByUserId(Long userId) {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return reflectionRepository.findByUserIdAndDateCreatedBetween(userId, startOfDay, endOfDay);
    }

    /**
     * Get reflections by date range for a user
     */
    public List<Reflection> getReflectionsByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return reflectionRepository.findByUserIdAndDateCreatedBetween(userId, startDate, endDate);
    }

    /**
     * Get this week's reflections for a user
     */
    public List<Reflection> getThisWeekReflectionsByUserId(Long userId) {
        LocalDateTime startOfWeek = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        startOfWeek = startOfWeek.minusDays(startOfWeek.getDayOfWeek().getValue() - 1);
        LocalDateTime endOfWeek = startOfWeek.plusDays(7).minusNanos(1);
        return reflectionRepository.findByUserIdAndDateCreatedBetween(userId, startOfWeek, endOfWeek);
    }

    /**
     * Get this month's reflections for a user
     */
    public List<Reflection> getThisMonthReflectionsByUserId(Long userId) {
        LocalDateTime startOfMonth = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).withDayOfMonth(1);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);
        return reflectionRepository.findByUserIdAndDateCreatedBetween(userId, startOfMonth, endOfMonth);
    }
} 