package com.hotel.review.controller;

import com.hotel.review.model.Review;
import com.hotel.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // 1. POST /reviews - Create a new review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewRepository.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // 2. GET /reviews/hotel/{hotelId} - Get all reviews for a specific hotel
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Review>> getReviewsByHotel(@PathVariable String hotelId) {
        List<Review> reviews = reviewRepository.findByHotelId(hotelId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // 3. PUT /reviews/{id} - Update an existing review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Review reviewDetails) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setRating(reviewDetails.getRating());
            existingReview.setComment(reviewDetails.getComment());
            
            Review updatedReview = reviewRepository.save(existingReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 4. DELETE /reviews/{id} - Delete a review (Moderation)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        
        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
