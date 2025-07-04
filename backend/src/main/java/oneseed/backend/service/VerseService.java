package oneseed.backend.service;

import oneseed.backend.model.Verse;
import oneseed.backend.repository.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerseService {

    @Autowired
    private VerseRepository verseRepository;

    /**
     * Create a new verse
     */
    public Verse createVerse(Verse verse) {
        return verseRepository.save(verse);
    }

    /**
     * Get verse by ID
     */
    public Optional<Verse> getVerseById(Long id) {
        return verseRepository.findById(id);
    }

    /**
     * Get all verses for a user
     */
    public List<Verse> getVersesByUserId(Long userId) {
        return verseRepository.findByUserId(userId);
    }

    /**
     * Get favorite verses for a user
     */
    public List<Verse> getFavoriteVersesByUserId(Long userId) {
        return verseRepository.findByUserIdAndIsFavoriteTrue(userId);
    }

    /**
     * Get verse by reference and user ID
     */
    public Optional<Verse> getVerseByReferenceAndUserId(String reference, Long userId) {
        return verseRepository.findByReferenceAndUserId(reference, userId);
    }

    /**
     * Get verses by book for a user
     */
    public List<Verse> getVersesByBookAndUserId(String book, Long userId) {
        return verseRepository.findByBookAndUserId(book, userId);
    }

    /**
     * Get verses by version for a user
     */
    public List<Verse> getVersesByVersionAndUserId(String version, Long userId) {
        return verseRepository.findByVersionAndUserId(version, userId);
    }

    /**
     * Get verses by chapter for a user
     */
    public List<Verse> getVersesByChapterAndUserId(String book, int chapter, Long userId) {
        return verseRepository.findByBookAndChapterAndUserId(book, chapter, userId);
    }

    /**
     * Get specific verse by book, chapter, and verse number
     */
    public Optional<Verse> getVerseByBookChapterVerseAndUserId(String book, int chapter, int verseNumber, Long userId) {
        return verseRepository.findByBookAndChapterAndVerseNumberAndUserId(book, chapter, verseNumber, userId);
    }

    /**
     * Search verses by text content
     */
    public List<Verse> searchVersesByText(Long userId, String searchText) {
        return verseRepository.findByTextContaining(userId, searchText);
    }

    /**
     * Search verses by reference
     */
    public List<Verse> searchVersesByReference(Long userId, String searchText) {
        return verseRepository.findByReferenceContaining(userId, searchText);
    }

    /**
     * Update verse
     */
    public Verse updateVerse(Verse verse) {
        return verseRepository.save(verse);
    }

    /**
     * Delete verse by ID
     */
    public void deleteVerse(Long id) {
        verseRepository.deleteById(id);
    }

    /**
     * Toggle favorite status of a verse
     */
    public Verse toggleFavorite(Long verseId) {
        Optional<Verse> verseOpt = verseRepository.findById(verseId);
        if (verseOpt.isPresent()) {
            Verse verse = verseOpt.get();
            verse.setFavorite(!verse.isFavorite());
            return verseRepository.save(verse);
        }
        return null;
    }

    /**
     * Get verse count for a user
     */
    public long getVerseCountByUserId(Long userId) {
        return verseRepository.countByUserId(userId);
    }

    /**
     * Get favorite verse count for a user
     */
    public long getFavoriteVerseCountByUserId(Long userId) {
        return verseRepository.countByUserIdAndIsFavoriteTrue(userId);
    }

    /**
     * Get recent verses for a user
     */
    public List<Verse> getRecentVersesByUserId(Long userId) {
        return verseRepository.findRecentVerses(userId);
    }

    /**
     * Get verses by multiple books
     */
    public List<Verse> getVersesByBooks(Long userId, List<String> books) {
        return verseRepository.findByBooks(userId, books);
    }

    /**
     * Get verses by multiple versions
     */
    public List<Verse> getVersesByVersions(Long userId, List<String> versions) {
        return verseRepository.findByVersions(userId, versions);
    }

    /**
     * Get all unique books for a user
     */
    public List<String> getDistinctBooksByUserId(Long userId) {
        return verseRepository.findDistinctBooksByUserId(userId);
    }

    /**
     * Get all unique versions for a user
     */
    public List<String> getDistinctVersionsByUserId(Long userId) {
        return verseRepository.findDistinctVersionsByUserId(userId);
    }
} 