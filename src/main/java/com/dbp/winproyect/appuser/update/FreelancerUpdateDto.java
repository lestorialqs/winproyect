package com.dbp.winproyect.appuser.update;


import com.dbp.winproyect.freelancer.domain.LevelEducation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreelancerUpdateDto extends ProviderUpdateDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer dni;
    private String experience;
    private LevelEducation levelEducation;
}