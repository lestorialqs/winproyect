package com.dbp.winproyect.appuser.update;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterpriseUpdateDto extends ProviderUpdateDto {

    private String name;
    private String description;
    private BusinessSector businessSector;
    private Size size;
    private String address;

}

