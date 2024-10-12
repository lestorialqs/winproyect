package com.dbp.winproyect.review.application;

import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.review.domain.ReviewService;
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


    @PostMapping("/{id}/review")
    public ResponseEntity<Review> createReview(@RequestBody Review review, @PathVariable Long serviceId) {
        reviewService.saveReview(review, serviceId);
        URI location = URI.create("/{id}/review" + review.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/review")
    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewService.getAll(pageable);
    }

    // El pageable permite ordenar los resultados al traerlos con un Get
    // Con los path parameters:
    // page: num de pagina a mostrar
    // size: cantidad de elementos por pagina
    // sort: el atributo por el cual se ordena:
    //      se usa por ej "amount,asc" para un sort por monto en ascendente.
    //      y se usa "amount,desc" para un sort por monto descendente


//    @GetMapping("/{id}/review")
//    public Page
//    GET /api/services/{Id}/reviews
}
