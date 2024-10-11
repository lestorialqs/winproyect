package com.dbp.winproyect.provider.providerdto;

import com.dbp.winproyect.appuser.appuserdto.DtoLoginGoogle;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProviderDto extends DtoLoginGoogle {
    private Boolean active;
    private Integer rating;
    private Double commission;
    private String ruc;
    private String category;
}
