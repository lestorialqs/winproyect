package com.dbp.winproyect.review.domain;

import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void saveReview(Review newReview, Long serviceId) {
        ServiceEntity service = serviceEntityRepository.
                findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
        Client client = clientRepository.
                findById(newReview.getClient().getId()).get();

        // Obteniendo fecha y darle formato de 12-10-2024 12:51:21 PM
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setAmPmStrings(new String[] { "AM", "PM" });
        sdf.setDateFormatSymbols(symbols);
        String formattedDate = sdf.format(new Date());
        System.out.println(formattedDate);

        newReview.setEdited(false);
        newReview.setDate(formattedDate);
        newReview.setServiceEntity(service);
        newReview.setClient(client);
        reviewRepository.save(newReview);

    }

    public Page<Review> getAll(Pageable pageable){
        return reviewRepository.findAll(pageable);
    }

}
