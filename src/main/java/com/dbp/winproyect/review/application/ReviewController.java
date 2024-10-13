package com.dbp.winproyect.review.application;

import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.review.domain.ReviewService;
import com.dbp.winproyect.review.dto.ReviewDtoCreateRequest;
import com.dbp.winproyect.review.dto.ReviewDtoEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/service")
public class ReviewController {

    final private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{serviceId}/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDtoCreateRequest reviewDtoCreateRequest,
                                               @PathVariable Long serviceId) {
        Review newReview = reviewService.saveReview(reviewDtoCreateRequest, serviceId);
        URI location = URI.create("/{id}/review" + newReview.getId());
        return ResponseEntity.created(location).body(newReview);
    }

    // El pageable permite ordenar los resultados al traerlos con un Get
    // Con los path parameters:
    // page: num de pagina a mostrar
    // size: cantidad de elementos por pagina
    // sort: el atributo por el cual se ordena:
    //      se usa por ej "amount,asc" para un sort por monto en ascendente.
    //      y se usa "amount,desc" para un sort por monto descendente
    @GetMapping("/{serviceId}/review")
    public ResponseEntity<Page<Review>> getAllReviews(@PathVariable Long serviceId, Pageable pageable) {
        return ResponseEntity.ok(reviewService.getAllReviewsByServiceId(serviceId, pageable));
    }

    @PatchMapping("/{serviceId}/review/{reviewId}")
    public ResponseEntity<Void> editReview(@RequestBody ReviewDtoEditRequest reviewDtoEditRequest,
                                           @PathVariable Long serviceId, @PathVariable Long reviewId) {
        reviewService.editReview(reviewDtoEditRequest, serviceId, reviewId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{serviceId}/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long serviceId, @PathVariable Long reviewId) {
        reviewService.deleteReview(serviceId, reviewId);
        return ResponseEntity.noContent().build();
    }

}
