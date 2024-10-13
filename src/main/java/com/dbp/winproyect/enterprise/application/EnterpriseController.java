package com.dbp.winproyect.enterprise.application;

import com.dbp.winproyect.enterprise.domain.EnterpriseService;
import com.dbp.winproyect.enterprise.dto.EnterpriseDtoViewPerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {
    private final EnterpriseService enterpriseService;

}