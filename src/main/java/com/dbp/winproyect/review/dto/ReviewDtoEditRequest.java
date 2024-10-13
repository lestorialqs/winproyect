package com.dbp.winproyect.review.dto;

import lombok.Data;

@Data
public class ReviewDtoEditRequest {
    private Integer rating;
    private String comment;
}
