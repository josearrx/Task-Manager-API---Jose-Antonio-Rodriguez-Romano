package com.hitss.springboot.taskmanager.security;

import com.hitss.springboot.taskmanager.security.filter.JwtAuthenticationFilter;
import com.hitss.springboot.taskmanager.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler; 

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // NUEVO: Bean para el manejador de Acceso Denegado (403)
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
            // Endpoints públicos
            auth.requestMatchers("/api/users/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/api/users").permitAll(); 
            // Todas las demás solicitudes requieren autenticación (la autorización de roles se hace con @PreAuthorize)
            auth.anyRequest().authenticated();
        })
        .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
        .addFilter(new JwtValidationFilter(this.authenticationManager()))
        .csrf(conf -> conf.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(conf -> conf.accessDeniedHandler(accessDeniedHandler()))
        .build();
    }
}