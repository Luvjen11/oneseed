package oneseed.backend.controller;

import oneseed.backend.model.Reflection;
import oneseed.backend.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/oneseed/reflections")
@CrossOrigin(origins = "*")
public class ReflectionController {

    @Autowired
    private ReflectionService reflectionService;

    /**
     * Create a new reflection
     */
    @PostMapping
    public ResponseEntity<Reflection> createReflection(@RequestBody Reflection reflection) {
        Reflection createdReflection = reflectionService.createReflection(reflection);
        return new ResponseEntity<>(createdReflection, HttpStatus.CREATED);
    }

    /**
     * Get reflection by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reflection> getReflectionById(@PathVariable Long id) {
        Optional<Reflection> reflection = reflectionService.getReflectionById(id);
        return reflection.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all reflections for a user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reflection>> getReflectionsByUserId(@PathVariable Long userId) {
        List<Reflection> reflections = reflectionService.getReflectionsByUserId(userId);
        return new ResponseEntity<>(reflections, HttpStatus.OK);
    }

    /**
     * Get reflections by prompt for a user
     */
    @GetMapping("/user/{userId}/prompt/{prompt}")
    public ResponseEntity<List<Reflection>> getReflectionsByPromptAndUserId(@PathVariable String prompt, @PathVariable Long userId) {
        List<Reflection> reflections = reflectionService.getReflectionsByPromptAndUserId(prompt, userId);
        return new ResponseEntity<>(reflections, HttpStatus.OK);
    }

    /**
     * Update reflection
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reflection> updateReflection(@PathVariable Long id, @RequestBody Reflection reflection) {
        Optional<Reflection> existingReflection = reflectionService.getReflectionById(id);
        if (existingReflection.isPresent()) {
            reflection.setId(id);
            Reflection updatedReflection = reflectionService.updateReflection(reflection);
            return new ResponseEntity<>(updatedReflection, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete reflection
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReflection(@PathVariable Long id) {
        Optional<Reflection> reflection = reflectionService.getReflectionById(id);
        if (reflection.isPresent()) {
            reflectionService.deleteReflection(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get reflection count for a user
     */
    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> getReflectionCountByUserId(@PathVariable Long userId) {
        long count = reflectionService.getReflectionCountByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get recent reflections for a user
     */
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<Reflection>> getRecentReflectionsByUserId(@PathVariable Long userId) {
        List<Reflection> reflections = reflectionService.getRecentReflectionsByUserId(userId);
        return new ResponseEntity<>(reflections, HttpStatus.OK);
    }

    /**
     * Get today's reflections for a user
     */
    @GetMapping("/user/{userId}/today")
    public ResponseEntity<List<Reflection>> getTodayReflectionsByUserId(@PathVariable Long userId) {
        List<Reflection> reflections = reflectionService.getTodayReflectionsByUserId(userId);
        return new ResponseEntity<>(reflections, HttpStatus.OK);
    }

    /**
     * Get reflections by date range for a user
     */
    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<Reflection>> getReflectionsByDateRange(@PathVariable Long userId, @RequestParam String start, @RequestParam String end) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);
            List<Reflection> reflections = reflectionService.getReflectionsByDateRange(userId, startDate, endDate);
            return new ResponseEntity<>(reflections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get this week's reflections for a user
     */
    @GetMapping("/user/{userId}/this-week")
    public ResponseEntity<List<Reflection>> getThisWeekReflectionsByUserId(@PathVariable Long userId) {
        List<Reflection> reflections = reflectionService.getThisWeekReflectionsByUserId(userId);
        return new ResponseEntity<>(reflections, HttpStatus.OK);
    }

    /**
     * Get this month's reflections for a user
     */
    @GetMapping("/user/{userId}/this-month")
    public ResponseEntity<List<Reflection>> getThisMonthReflectionsByUserId(@PathVariable Long userId) {
        List<Reflection> reflections = reflectionService.getThisMonthReflectionsByUserId(userId);
        return new ResponseEntity<>(reflections, HttpStatus.OK);
    }
} 