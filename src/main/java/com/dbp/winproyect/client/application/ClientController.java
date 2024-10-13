package com.dbp.winproyect.client.application;

import com.dbp.winproyect.client.domain.ClientService;
import com.dbp.winproyect.client.dto.ClientDtoViewPerfilResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

}
