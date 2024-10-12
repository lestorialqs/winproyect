package com.dbp.winproyect.review.infrastructure;


import com.dbp.winproyect.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByServiceEntityId(Long serviceEntityId);
}
