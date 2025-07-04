package oneseed.backend.repository;

import oneseed.backend.model.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
    
    /**
     * Find reflections by user ID
     */
    List<Reflection> findByUserId(Long userId);
    
    /**
     * Find reflections by prompt and user ID
     */
    List<Reflection> findByPromptAndUserId(String prompt, Long userId);
    
    /**
     * Find reflections by date created range
     */
    List<Reflection> findByUserIdAndDateCreatedBetween(Long userId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Count reflections by user ID
     */
    long countByUserId(Long userId);
    
    /**
     * Find most recent reflections by user ID
     */
    @Query("SELECT r FROM Reflection r WHERE r.userId = :userId ORDER BY r.dateCreated DESC")
    List<Reflection> findRecentReflections(@Param("userId") Long userId);
} 