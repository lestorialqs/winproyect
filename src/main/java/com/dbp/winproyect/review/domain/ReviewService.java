package com.dbp.winproyect.review.domain;

import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.review.dto.ReviewDtoCreateRequest;
import com.dbp.winproyect.review.dto.ReviewDtoEditRequest;
import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;


@Service
public class ReviewService {

    final private ReviewRepository reviewRepository;
    final private ServiceEntityRepository serviceEntityRepository;
    final private ClientRepository clientRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ServiceEntityRepository serviceEntityRepository,
                         ClientRepository clientRepository) {
        this.reviewRepository = reviewRepository;
        this.serviceEntityRepository = serviceEntityRepository;
        this.clientRepository = clientRepository;
    }

    public Review saveReview(ReviewDtoCreateRequest reviewDtoCreateRequest, Long serviceId) {

        // Obteniendo y Comprobando si existe la review a editar:
        //Review newReview = new Review();

        // Comprobando si existe el Servicio:
//        Optional<ServiceEntity> service = serviceEntityRepository.
//                findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        // Comprobando si existe el Cliente:
//        Optional<Client> client = clientRepository.findById(reviewDtoCreateRequest.getUserId()).
//                orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        // Obteniendo fecha y darle formato de 12-10-2024 12:51:21 PM
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

//        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
//        symbols.setAmPmStrings(new String[] { "AM", "PM" });
//        sdf.setDateFormatSymbols(symbols);
//        String formattedDate = sdf.format(new Date());
//        System.out.println(formattedDate);
//
//        newReview.setDate(formattedDate);

        // Setteando el newReview sin modelMapper (pq puede equivoacrse):



        // CAMBIOS LUIS, VOY A PROBAR SI ES QUE SIRVE SINO AQUI GUARDARE EN LOS COMENTARIOS LA VERSION ESTABLE:
        /*newReview.setRating(reviewDtoCreateRequest.getRating());
        newReview.setComment(reviewDtoCreateRequest.getComment());
        newReview.setDate(ZonedDateTime.now());
        newReview.setServiceEntity(serviceEntityRepository.findById(serviceId).get());
        newReview.setClient(clientRepository.findById(reviewDtoCreateRequest.getUserId()).get());

        return reviewRepository.save(newReview);
         */
        // Obteniendo y Comprobando si existe la review a editar:
        Review newReview = new Review();

        // Comprobando si existe el Servicio:
        ServiceEntity serviceEntity = serviceEntityRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        // Comprobando si existe el Cliente:
        Client client = clientRepository.findById(reviewDtoCreateRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        // Setteando el newReview:
        newReview.setRating(reviewDtoCreateRequest.getRating());
        newReview.setComment(reviewDtoCreateRequest.getComment());
        newReview.setDate(ZonedDateTime.now());
        newReview.setServiceEntity(serviceEntity);
        newReview.setClient(client);

        return reviewRepository.save(newReview);

    }

    // Para obtener Page (ordenable) de Reviews de un ServiceEntity por id de la ServiceEntity:
    public Page<Review> getAllReviewsByServiceId(Long serviceId, Pageable pageable){

        return reviewRepository.findByServiceEntityId(serviceId, pageable);
    }

    public void editReview(ReviewDtoEditRequest reviewDtoEditRequest, Long serviceId, Long reviewId) {

        // Obteniendo y Comprobando si existe la review a editar:
        Review reviewToEdit = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        reviewToEdit.setRating(reviewDtoEditRequest.getRating());
        reviewToEdit.setComment(reviewDtoEditRequest.getComment());
        reviewToEdit.setDate(ZonedDateTime.now());

        reviewRepository.save(reviewToEdit);
    }

    public void deleteReview(Long serviceId, Long reviewId) {

        // Falta revisar que el cliente editando la review es el creador de la review

        // Verificar si existen el Servicio y la Review correspondiente
        if (!serviceEntityRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service not found");

        // Verificar si existe el Cliente que hizo la Review.
        // Aunque ya dijimos que se puede eliminar el Client y quedar la Review
//        if (!clientRepository.existsById(reviewId))
//            throw new ResourceNotFoundException("Review not found");

        if (!reviewRepository.existsById(reviewId))
            throw new ResourceNotFoundException("Review not found");

        reviewRepository.deleteById(reviewId);
    }

}
