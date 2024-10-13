package com.dbp.winproyect.review.infrastructure;

import com.dbp.winproyect.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByServiceEntityId(Long serviceId, Pageable pageable);
}
