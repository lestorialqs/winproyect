package com.dbp.winproyect.review.domain;


import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public List<Review> findAllByServiceId(Long serviceId) {
        return reviewRepository.findAllByServiceEntityId(serviceId);
    }
}
