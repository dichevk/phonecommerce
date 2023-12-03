package com.phonecommerce.phonestore.controller;

import com.phonecommerce.phonestore.dto.ReviewDTO;
import com.phonecommerce.phonestore.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Api(tags = "Review Management")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @ApiOperation(value = "Get all reviews", notes = "Retrieve a list of all reviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get review by ID", notes = "Retrieve a review by its ID")
    public ResponseEntity<ReviewDTO> getReviewById(
            @ApiParam(value = "Review ID", required = true)
            @PathVariable Long id) {
        ReviewDTO review = reviewService.getReviewById(id);
        return review != null ?
                new ResponseEntity<>(review, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ApiOperation(value = "Create a new review", notes = "Add a new review to the system")
    public ResponseEntity<ReviewDTO> createReview(
            @ApiParam(value = "Review data", required = true)
            @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a review", notes = "Modify an existing review's information")
    public ResponseEntity<ReviewDTO> updateReview(
            @ApiParam(value = "Review ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated review data", required = true)
            @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        return updatedReview != null ?
                new ResponseEntity<>(updatedReview, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a review", notes = "Remove a review from the system")
    public ResponseEntity<Void> deleteReview(
            @ApiParam(value = "Review ID", required = true)
            @PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
