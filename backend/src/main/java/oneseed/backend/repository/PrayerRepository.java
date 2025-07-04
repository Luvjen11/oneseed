package oneseed.backend.repository;

import oneseed.backend.model.Prayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrayerRepository extends JpaRepository<Prayer, Long> {
    
    /**
     * Find prayers by user ID
     */
    List<Prayer> findByUserId(Long userId);
    
    /**
     * Find answered prayers by user ID
     */
    List<Prayer> findByUserIdAndIsAnsweredTrue(Long userId);
    
    /**
     * Find unanswered prayers by user ID
     */
    List<Prayer> findByUserIdAndIsAnsweredFalse(Long userId);
    
    /**
     * Find prayers by title containing text
     */
    @Query("SELECT p FROM Prayer p WHERE p.userId = :userId AND LOWER(p.title) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Prayer> findByTitleContaining(@Param("userId") Long userId, @Param("searchText") String searchText);
    
    /**
     * Find prayers by date created range
     */
    List<Prayer> findByUserIdAndDateCreatedBetween(Long userId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Find prayers by date answered range
     */
    List<Prayer> findByUserIdAndDateAnsweredBetween(Long userId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Count prayers by user ID
     */
    long countByUserId(Long userId);
    
    /**
     * Count answered prayers by user ID
     */
    long countByUserIdAndIsAnsweredTrue(Long userId);
    
    /**
     * Count unanswered prayers by user ID
     */
    long countByUserIdAndIsAnsweredFalse(Long userId);
    
    /**
     * Find most recent prayers by user ID
     */
    @Query("SELECT p FROM Prayer p WHERE p.userId = :userId ORDER BY p.dateCreated DESC")
    List<Prayer> findRecentPrayers(@Param("userId") Long userId);
} 