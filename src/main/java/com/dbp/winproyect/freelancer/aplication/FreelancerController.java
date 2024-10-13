package com.dbp.winproyect.freelancer.aplication;

import com.dbp.winproyect.freelancer.domain.FreelancerService;
import com.dbp.winproyect.freelancer.dto.FreelancerDtoViewPerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/freelancer")
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;

}