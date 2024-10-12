package com.dbp.winproyect.auth.domain;

import com.dbp.winproyect.appuser.domain.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AppUserDetails implements UserDetails {
    private final AppUser appUser;
    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;

    }
    @Override
    public String getUsername() {
        return appUser.getEmail();  // Usamos el email como nombre de usuario
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();  // Retornamos la contraseña cifrada
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes devolver los roles o permisos del usuario si los tienes
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Puedes agregar lógica para expirar la cuenta si es necesario
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Puedes agregar lógica para bloquear la cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Puedes agregar lógica para expirar las credenciales
    }

    @Override
    public boolean isEnabled() {
        return true;  // Puedes deshabilitar cuentas si es necesario
    }
}

