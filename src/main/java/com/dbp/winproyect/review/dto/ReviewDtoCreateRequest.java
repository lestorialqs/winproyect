package com.dbp.winproyect.review.dto;

import com.dbp.winproyect.client.domain.Client;
import lombok.Data;

@Data
public class ReviewDtoCreateRequest {
    private Integer rating;
    private String comment;
    private Long userId;
}
