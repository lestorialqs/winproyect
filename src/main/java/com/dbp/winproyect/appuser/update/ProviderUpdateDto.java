package com.dbp.winproyect.appuser.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderUpdateDto extends AppUserUpdateDto {

    private Long ruc;
    private Boolean estate;
    private Float rating;
    private double comission;

}
