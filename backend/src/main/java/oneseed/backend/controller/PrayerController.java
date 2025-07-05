package oneseed.backend.controller;

import oneseed.backend.model.Prayer;
import oneseed.backend.service.PrayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/oneseed/prayers")
@CrossOrigin(origins = "*")
public class PrayerController {

    @Autowired
    private PrayerService prayerService;

    /**
     * Create a new prayer
     */
    @PostMapping
    public ResponseEntity<Prayer> createPrayer(@RequestBody Prayer prayer) {
        Prayer createdPrayer = prayerService.createPrayer(prayer);
        return new ResponseEntity<>(createdPrayer, HttpStatus.CREATED);
    }

    /**
     * Get prayer by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prayer> getPrayerById(@PathVariable Long id) {
        Optional<Prayer> prayer = prayerService.getPrayerById(id);
        return prayer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all prayers for a user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Prayer>> getPrayersByUserId(@PathVariable Long userId) {
        List<Prayer> prayers = prayerService.getPrayersByUserId(userId);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }

    /**
     * Get answered prayers for a user
     */
    @GetMapping("/user/{userId}/answered")
    public ResponseEntity<List<Prayer>> getAnsweredPrayersByUserId(@PathVariable Long userId) {
        List<Prayer> prayers = prayerService.getAnsweredPrayersByUserId(userId);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }

    /**
     * Get unanswered prayers for a user
     */
    @GetMapping("/user/{userId}/unanswered")
    public ResponseEntity<List<Prayer>> getUnansweredPrayersByUserId(@PathVariable Long userId) {
        List<Prayer> prayers = prayerService.getUnansweredPrayersByUserId(userId);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }

    /**
     * Search prayers by title
     */
    @GetMapping("/user/{userId}/search-title")
    public ResponseEntity<List<Prayer>> searchPrayersByTitle(@PathVariable Long userId, @RequestParam String title) {
        List<Prayer> prayers = prayerService.searchPrayersByTitle(userId, title);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }

    /**
     * Update prayer
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prayer> updatePrayer(@PathVariable Long id, @RequestBody Prayer prayer) {
        Optional<Prayer> existingPrayer = prayerService.getPrayerById(id);
        if (existingPrayer.isPresent()) {
            prayer.setId(id);
            Prayer updatedPrayer = prayerService.updatePrayer(prayer);
            return new ResponseEntity<>(updatedPrayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete prayer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrayer(@PathVariable Long id) {
        Optional<Prayer> prayer = prayerService.getPrayerById(id);
        if (prayer.isPresent()) {
            prayerService.deletePrayer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Mark prayer as answered
     */
    @PutMapping("/{id}/mark-answered")
    public ResponseEntity<Prayer> markPrayerAsAnswered(@PathVariable Long id) {
        Prayer updatedPrayer = prayerService.markPrayerAsAnswered(id);
        if (updatedPrayer != null) {
            return new ResponseEntity<>(updatedPrayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Mark prayer as unanswered
     */
    @PutMapping("/{id}/mark-unanswered")
    public ResponseEntity<Prayer> markPrayerAsUnanswered(@PathVariable Long id) {
        Prayer updatedPrayer = prayerService.markPrayerAsUnanswered(id);
        if (updatedPrayer != null) {
            return new ResponseEntity<>(updatedPrayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get prayer count for a user
     */
    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> getPrayerCountByUserId(@PathVariable Long userId) {
        long count = prayerService.getPrayerCountByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get answered prayer count for a user
     */
    @GetMapping("/user/{userId}/answered/count")
    public ResponseEntity<Long> getAnsweredPrayerCountByUserId(@PathVariable Long userId) {
        long count = prayerService.getAnsweredPrayerCountByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get unanswered prayer count for a user
     */
    @GetMapping("/user/{userId}/unanswered/count")
    public ResponseEntity<Long> getUnansweredPrayerCountByUserId(@PathVariable Long userId) {
        long count = prayerService.getUnansweredPrayerCountByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get recent prayers for a user
     */
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<Prayer>> getRecentPrayersByUserId(@PathVariable Long userId) {
        List<Prayer> prayers = prayerService.getRecentPrayersByUserId(userId);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }

    /**
     * Get today's prayers for a user
     */
    @GetMapping("/user/{userId}/today")
    public ResponseEntity<List<Prayer>> getTodayPrayersByUserId(@PathVariable Long userId) {
        List<Prayer> prayers = prayerService.getTodayPrayersByUserId(userId);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }

    /**
     * Get today's answered prayers for a user
     */
    @GetMapping("/user/{userId}/today-answered")
    public ResponseEntity<List<Prayer>> getTodayAnsweredPrayersByUserId(@PathVariable Long userId) {
        List<Prayer> prayers = prayerService.getTodayAnsweredPrayersByUserId(userId);
        return new ResponseEntity<>(prayers, HttpStatus.OK);
    }
} 