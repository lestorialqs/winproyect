package com.dbp.winproyect.appuser.application;

import com.dbp.winproyect.appuser.dto.AppUserLoginDto;
import com.dbp.winproyect.config.JwtService;
import com.dbp.winproyect.config.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appuser/auth")
public class AppUserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody AppUserLoginDto userLoginDto) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Error en el login", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginDto.getEmail());
        return jwtService.generateToken(userDetails);
    }
    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto) throws Exception
    }
}
