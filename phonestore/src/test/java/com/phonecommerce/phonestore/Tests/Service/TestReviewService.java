package com.phonecommerce.phonestore.Tests.Service;

import com.phonecommerce.phonestore.dto.ReviewDTO;
import com.phonecommerce.phonestore.model.Phone;
import com.phonecommerce.phonestore.model.Review;
import com.phonecommerce.phonestore.model.User;
import com.phonecommerce.phonestore.repository.ReviewRepository;
import com.phonecommerce.phonestore.service.ReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Good phone", 5, new User("john_doe", "password123", "john@example.com"),  new Phone("Samsung_S23","Samsung",800)));
        reviews.add(new Review("Average phone", 3, new User("john_doe", "password123", "john@example.com"),  new Phone("Samsung_S23","Samsung",800)));

        when(reviewRepository.findAll()).thenReturn(reviews);

        List<ReviewDTO> reviewDTOs = reviewService.getAllReviews();

        assertEquals(2, reviewDTOs.size());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetReviewById() {
        Long reviewId = 1L;
        Review review = new Review("Good phone", 5, new User("john_doe", "password123", "john@example.com"),  new Phone("Samsung_S23","Samsung",800));
        review.setId(reviewId);

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewDTO reviewDTO = reviewService.getReviewById(reviewId);

        assertEquals(reviewId, reviewDTO.getId());
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void testCreateReview() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setComment("Good phone");
        reviewDTO.setRating(5);

        Review review = new Review("Good phone", 5, new User("john_doe", "password123", "john@example.com"),  new Phone("Samsung_S23","Samsung",800));

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewDTO savedReviewDTO = reviewService.createReview(reviewDTO);

        assertNotNull(savedReviewDTO);
        assertEquals("Good phone", savedReviewDTO.getComment());
        assertEquals(5, savedReviewDTO.getRating());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testUpdateReview() {
        Long reviewId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setComment("Updated comment");
        reviewDTO.setRating(8.7);

        Review existingReview = new Review("Original comment", 8 ,new User("john_doe", "password123", "john@example.com"),  new Phone("Samsung_S23","Samsung",800));;
        existingReview.setId(reviewId);

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(existingReview);

        ReviewDTO updatedReviewDTO = reviewService.updateReview(reviewId, reviewDTO);

        assertNotNull(updatedReviewDTO);
        assertEquals("Updated comment", updatedReviewDTO.getComment());
        assertEquals(4, updatedReviewDTO.getRating());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testDeleteReview() {
        Long reviewId = 1L;

        doNothing().when(reviewRepository).deleteById(reviewId);

        reviewService.deleteReview(reviewId);

        verify(reviewRepository, times(1)).deleteById(reviewId);
    }
}
