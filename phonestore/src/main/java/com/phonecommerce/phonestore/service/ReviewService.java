package com.phonecommerce.phonestore.service;

import com.phonecommerce.phonestore.dto.ReviewDTO;
import com.phonecommerce.phonestore.model.Review;
import com.phonecommerce.phonestore.repository.ReviewRepository;
import com.phonecommerce.phonestore.repository.UserRepository;
import com.phonecommerce.phonestore.repository.PhoneRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;


    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, PhoneRepository phoneRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO getReviewById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        return reviewOptional.map(this::convertToDTO).orElse(null);
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            // Update the fields with non-null values from the DTO
            existingReview.setRating(reviewDTO.getRating());
            if (reviewDTO.getComment() != null) {
                existingReview.setComment(reviewDTO.getComment());
            }
            // Assuming other fields can be updated similarly

            // Save the updated review using the repository
            Review updatedReview = reviewRepository.save(existingReview);
            return convertToDTO(updatedReview);
        } else {
            return null;
        }
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);
        return reviewDTO;
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        Review review = new Review(reviewDTO.getComment(),reviewDTO.getRating(), userRepository.findById(reviewDTO.getUserId()).get(), phoneRepository.findById(reviewDTO.getPhoneId()).get());
        BeanUtils.copyProperties(reviewDTO, review);
        return review;
    }
}

