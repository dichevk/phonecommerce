package com.phonecommerce.phonestore.Tests.Controller;

import com.phonecommerce.phonestore.controller.ReviewController;
import com.phonecommerce.phonestore.dto.ReviewDTO;
import com.phonecommerce.phonestore.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllReviews_ReturnsListOfReviews() {
        // Arrange
        ReviewDTO review = new ReviewDTO();
        List<ReviewDTO> reviewList = Collections.singletonList(review);

        when(reviewService.getAllReviews()).thenReturn(reviewList);

        // Act
        ResponseEntity<List<ReviewDTO>> response = reviewController.getAllReviews();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewList, response.getBody());
    }

    @Test
    void getReviewById_ExistingId_ReturnsReview() {
        // Arrange
        long reviewId = 1L;
        ReviewDTO review = new ReviewDTO();
        when(reviewService.getReviewById(reviewId)).thenReturn(review);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.getReviewById(reviewId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    void getReviewById_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        when(reviewService.getReviewById(nonExistingId)).thenReturn(null);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.getReviewById(nonExistingId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
        @Test
    void createReview_ValidReview_ReturnsCreated() {
        // Arrange
        ReviewDTO reviewDTO = new ReviewDTO(); // Initialize with valid review data
        when(reviewService.createReview(any(ReviewDTO.class))).thenReturn(reviewDTO);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.createReview(reviewDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reviewDTO, response.getBody());
    }

    @Test
    void updateReview_ExistingIdAndValidReview_ReturnsUpdatedReview() {
        // Arrange
        long reviewId = 1L;
        ReviewDTO updatedReviewDTO = new ReviewDTO(); // Initialize with updated review data
        when(reviewService.updateReview(anyLong(), any(ReviewDTO.class))).thenReturn(updatedReviewDTO);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.updateReview(reviewId, updatedReviewDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReviewDTO, response.getBody());
    }

    @Test
    void updateReview_NonExistingId_ReturnsNotFound() {
        // Arrange
        long nonExistingId = 100L;
        ReviewDTO updatedReviewDTO = new ReviewDTO(); // Initialize with updated review data
        when(reviewService.updateReview(anyLong(), any(ReviewDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.updateReview(nonExistingId, updatedReviewDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}