package oneseed.backend.controller;

import oneseed.backend.model.Verse;
import oneseed.backend.service.VerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/oneseed/verses")
@CrossOrigin(origins = "*")
public class VerseController {

    @Autowired
    private VerseService verseService;

    /**
     * Create a new verse
     */
    @PostMapping
    public ResponseEntity<Verse> createVerse(@RequestBody Verse verse) {
        Verse createdVerse = verseService.createVerse(verse);
        return new ResponseEntity<>(createdVerse, HttpStatus.CREATED);
    }

    /**
     * Get verse by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Verse> getVerseById(@PathVariable Long id) {
        Optional<Verse> verse = verseService.getVerseById(id);
        return verse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all verses for a user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Verse>> getVersesByUserId(@PathVariable Long userId) {
        List<Verse> verses = verseService.getVersesByUserId(userId);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Get favorite verses for a user
     */
    @GetMapping("/user/{userId}/favorites")
    public ResponseEntity<List<Verse>> getFavoriteVersesByUserId(@PathVariable Long userId) {
        List<Verse> verses = verseService.getFavoriteVersesByUserId(userId);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Get verse by reference and user ID
     */
    @GetMapping("/user/{userId}/reference/{reference}")
    public ResponseEntity<Verse> getVerseByReferenceAndUserId(@PathVariable String reference, @PathVariable Long userId) {
        Optional<Verse> verse = verseService.getVerseByReferenceAndUserId(reference, userId);
        return verse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get verses by book for a user
     */
    @GetMapping("/user/{userId}/book/{book}")
    public ResponseEntity<List<Verse>> getVersesByBookAndUserId(@PathVariable String book, @PathVariable Long userId) {
        List<Verse> verses = verseService.getVersesByBookAndUserId(book, userId);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Get verses by version for a user
     */
    @GetMapping("/user/{userId}/version/{version}")
    public ResponseEntity<List<Verse>> getVersesByVersionAndUserId(@PathVariable String version, @PathVariable Long userId) {
        List<Verse> verses = verseService.getVersesByVersionAndUserId(version, userId);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Get verses by chapter for a user
     */
    @GetMapping("/user/{userId}/book/{book}/chapter/{chapter}")
    public ResponseEntity<List<Verse>> getVersesByChapterAndUserId(@PathVariable String book, @PathVariable int chapter, @PathVariable Long userId) {
        List<Verse> verses = verseService.getVersesByChapterAndUserId(book, chapter, userId);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Get specific verse by book, chapter, and verse number
     */
    @GetMapping("/user/{userId}/book/{book}/chapter/{chapter}/verse/{verseNumber}")
    public ResponseEntity<Verse> getVerseByBookChapterVerseAndUserId(@PathVariable String book, @PathVariable int chapter, @PathVariable int verseNumber, @PathVariable Long userId) {
        Optional<Verse> verse = verseService.getVerseByBookChapterVerseAndUserId(book, chapter, verseNumber, userId);
        return verse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Search verses by text content
     */
    @GetMapping("/user/{userId}/search-text")
    public ResponseEntity<List<Verse>> searchVersesByText(@PathVariable Long userId, @RequestParam String text) {
        List<Verse> verses = verseService.searchVersesByText(userId, text);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Search verses by reference
     */
    @GetMapping("/user/{userId}/search-reference")
    public ResponseEntity<List<Verse>> searchVersesByReference(@PathVariable Long userId, @RequestParam String reference) {
        List<Verse> verses = verseService.searchVersesByReference(userId, reference);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Update verse
     */
    @PutMapping("/{id}")
    public ResponseEntity<Verse> updateVerse(@PathVariable Long id, @RequestBody Verse verse) {
        Optional<Verse> existingVerse = verseService.getVerseById(id);
        if (existingVerse.isPresent()) {
            verse.setId(id);
            Verse updatedVerse = verseService.updateVerse(verse);
            return new ResponseEntity<>(updatedVerse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete verse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerse(@PathVariable Long id) {
        Optional<Verse> verse = verseService.getVerseById(id);
        if (verse.isPresent()) {
            verseService.deleteVerse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Toggle favorite status of a verse
     */
    @PutMapping("/{id}/toggle-favorite")
    public ResponseEntity<Verse> toggleFavorite(@PathVariable Long id) {
        Verse updatedVerse = verseService.toggleFavorite(id);
        if (updatedVerse != null) {
            return new ResponseEntity<>(updatedVerse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get verse count for a user
     */
    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> getVerseCountByUserId(@PathVariable Long userId) {
        long count = verseService.getVerseCountByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get favorite verse count for a user
     */
    @GetMapping("/user/{userId}/favorites/count")
    public ResponseEntity<Long> getFavoriteVerseCountByUserId(@PathVariable Long userId) {
        long count = verseService.getFavoriteVerseCountByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get recent verses for a user
     */
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<Verse>> getRecentVersesByUserId(@PathVariable Long userId) {
        List<Verse> verses = verseService.getRecentVersesByUserId(userId);
        return new ResponseEntity<>(verses, HttpStatus.OK);
    }

    /**
     * Get all unique books for a user
     */
    @GetMapping("/user/{userId}/books")
    public ResponseEntity<List<String>> getDistinctBooksByUserId(@PathVariable Long userId) {
        List<String> books = verseService.getDistinctBooksByUserId(userId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Get all unique versions for a user
     */
    @GetMapping("/user/{userId}/versions")
    public ResponseEntity<List<String>> getDistinctVersionsByUserId(@PathVariable Long userId) {
        List<String> versions = verseService.getDistinctVersionsByUserId(userId);
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }
} 