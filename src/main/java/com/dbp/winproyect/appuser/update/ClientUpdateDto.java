package com.dbp.winproyect.appuser.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUpdateDto extends AppUserUpdateDto {

    private String firstName;
    private String lastName;
    private Long dni;
    private Boolean showAds;

}
