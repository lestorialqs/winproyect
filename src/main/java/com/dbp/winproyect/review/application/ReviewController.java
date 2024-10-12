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
@RequestMapping("/api/reviews")
public class ReviewController {

    final private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        review.setEdited(false);
        review.setDate(ZonedDateTime.now());
        reviewService.saveReview(review);
        URI location = URI.create("/api/reviews" + review.getId());
        return ResponseEntity.created(location).build();
    }



    // El pageable permite ordenar los resultados al traerlos con un Get
    // Con los path parameters:
    // page: num de pagina a mostrar
    // size: cantidad de elementos por pagina
    // sort: el atributo por el cual se ordena:
    //      se usa por ej "amount,asc" para un sort por monto en ascendente.
    //      y se usa "amount,desc" para un sort por monto descendente
    @GetMapping
    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewService.getAll(pageable);
    }

//    @GetMapping("/api/services/{id}/reviews")
//    public Page
//    GET /api/services/{Id}/reviews
}
