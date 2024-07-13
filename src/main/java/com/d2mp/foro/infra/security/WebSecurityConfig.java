package com.d2mp.foro.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SecurityFilter securityFilter;

    public WebSecurityConfig(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF (STATEFULL -> STATELESS)
                .sessionManagement(sessm -> sessm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Disable Sessions
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST,"/login","/usuarios").permitAll() // Acceso a login y Registro
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/**").permitAll() // Acceso a recursos estáticos
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll() // Acceso a Documentación
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Añadir filtro de seguridad personalizado
                .formLogin(AbstractHttpConfigurer::disable); // Deshabilitar el manejo de formularios de login
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}