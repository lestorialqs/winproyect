package com.dbp.winproyect.review.domain;

import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.ZonedDateTime;


@Service
public class ReviewService {

    final private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);

    }

    public Page<Review> getAll(Pageable pageable){
        return reviewRepository.findAll(pageable);
    }

}
