package com.dbp.winproyect.auth.config;

import com.dbp.winproyect.auth.filter.JwtAuthenticationFilter;
import com.dbp.winproyect.auth.service.AppUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF, ya que estamos usando JWT
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/login", "/auth/register/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/service", "/service/**", "/service/by-tag").permitAll()  // Cualquier usuario puede obtener servicios
                        // Endpoints para crear servicios
                        .requestMatchers(HttpMethod.POST, "/service").hasAnyRole("FREELANCER", "ENTERPRISE")
                        // Endpoints para perfil propio
                        .requestMatchers(HttpMethod.GET, "/profile").authenticated()  // Cualquier usuario autenticado puede ver su perfil
                        .requestMatchers(HttpMethod.PATCH, "/profile").authenticated()  // Cualquier usuario autenticado puede modificar su perfil
                        .anyRequest().authenticated()  // Proteger todo lo demás
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Sin estado, usando JWT
                );

        // Añadir el filtro JWT antes del filtro de autenticación predeterminado
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
