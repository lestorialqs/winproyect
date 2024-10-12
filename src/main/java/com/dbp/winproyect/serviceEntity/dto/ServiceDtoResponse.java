package com.dbp.winproyect.serviceEntity.dto;

import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.tag.domain.Tag;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ServiceDtoResponse {
    private String name;

    private String nameProvider;

    private String description;

    private String address;

    private Double suggestedPrice;

    private Float avg_rating;


    private List<Review> reviewList;

    private Set<Tag> tagsList;

}
