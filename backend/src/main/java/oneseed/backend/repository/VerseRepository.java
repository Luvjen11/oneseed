package oneseed.backend.repository;

import oneseed.backend.model.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {
    
    /**
     * Find verses by user ID
     */
    List<Verse> findByUserId(Long userId);
    
    /**
     * Find favorite verses by user ID
     */
    List<Verse> findByUserIdAndIsFavoriteTrue(Long userId);
    
    /**
     * Find verse by reference and user ID
     */
    Optional<Verse> findByReferenceAndUserId(String reference, Long userId);
    
    /**
     * Find verses by book name
     */
    List<Verse> findByBookAndUserId(String book, Long userId);
    
    /**
     * Find verses by version/translation
     */
    List<Verse> findByVersionAndUserId(String version, Long userId);
    
    /**
     * Find verses by chapter
     */
    List<Verse> findByBookAndChapterAndUserId(String book, int chapter, Long userId);
    
    /**
     * Find specific verse by book, chapter, and verse number
     */
    Optional<Verse> findByBookAndChapterAndVerseNumberAndUserId(String book, int chapter, int verseNumber, Long userId);
    
    /**
     * Find verses containing specific text
     */
    @Query("SELECT v FROM Verse v WHERE v.userId = :userId AND LOWER(v.text) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Verse> findByTextContaining(@Param("userId") Long userId, @Param("searchText") String searchText);
    
    /**
     * Find verses by reference containing text
     */
    @Query("SELECT v FROM Verse v WHERE v.userId = :userId AND LOWER(v.reference) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Verse> findByReferenceContaining(@Param("userId") Long userId, @Param("searchText") String searchText);
    
    /**
     * Count verses by user ID
     */
    long countByUserId(Long userId);
    
    /**
     * Count favorite verses by user ID
     */
    long countByUserIdAndIsFavoriteTrue(Long userId);
    
    /**
     * Find most recent verses by user ID
     */
    @Query("SELECT v FROM Verse v WHERE v.userId = :userId ORDER BY v.id DESC")
    List<Verse> findRecentVerses(@Param("userId") Long userId);
    
    /**
     * Find verses by multiple book names
     */
    @Query("SELECT v FROM Verse v WHERE v.userId = :userId AND v.book IN :books")
    List<Verse> findByBooks(@Param("userId") Long userId, @Param("books") List<String> books);
    
    /**
     * Find verses by multiple versions
     */
    @Query("SELECT v FROM Verse v WHERE v.userId = :userId AND v.version IN :versions")
    List<Verse> findByVersions(@Param("userId") Long userId, @Param("versions") List<String> versions);
    
    /**
     * Find all unique books for a user
     */
    @Query("SELECT DISTINCT v.book FROM Verse v WHERE v.userId = :userId")
    List<String> findDistinctBooksByUserId(@Param("userId") Long userId);
    
    /**
     * Find all unique versions for a user
     */
    @Query("SELECT DISTINCT v.version FROM Verse v WHERE v.userId = :userId")
    List<String> findDistinctVersionsByUserId(@Param("userId") Long userId);
} 