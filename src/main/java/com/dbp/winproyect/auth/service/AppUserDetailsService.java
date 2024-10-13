package com.dbp.winproyect.auth.service;

import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.appuser.infrastructure.BaseAppUserRepository;
import com.dbp.winproyect.auth.domain.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private BaseAppUserRepository baseAppUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        AppUser appUser = baseAppUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convertir la clase AppUser a UserDetails
        return new org.springframework.security.core.userdetails.User(
                appUser.getEmail(),
                appUser.getPassword(),
                getAuthorities(appUser)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(AppUser appUser) {
        // Convertir el rol a una lista de GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole().name()));
    }
}
