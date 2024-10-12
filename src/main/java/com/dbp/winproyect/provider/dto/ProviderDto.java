package com.dbp.winproyect.provider.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProviderDto extends UserDto {
    private Boolean active;
    private Integer rating;
    private Double commission;
    private String ruc;
    private String category;
}
