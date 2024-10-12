package com.dbp.winproyect.serviceEntity.dto;

import com.dbp.winproyect.review.domain.Review;
import lombok.Data;

import java.util.List;
@Data
public class ServiceDtoResponse {
    private String name;

    private String nameProvider;

    private String description;

    private String address;

    private Float rating;

    private List<Review> reviewList;

}
