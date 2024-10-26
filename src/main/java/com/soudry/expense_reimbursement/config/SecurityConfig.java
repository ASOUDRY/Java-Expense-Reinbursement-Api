package com.soudry.expense_reimbursement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.soudry.expense_reimbursement.services.UserToSpringUserService;
import com.soudry.expense_reimbursement.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {   
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserToSpringUserService userToSpringUserService;

    @Autowired
    private JwtUtil jwtUtil;
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration, JwtAuthenticationEntryPoint authenticationEntryPoint) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationEntryPoint authenticationEntryPoint, AuthorizationDenied authorizationDenied) throws Exception {
            http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                    authorizeHttpRequests
                        .requestMatchers("/login/**", "register/**").permitAll()
                        .requestMatchers("/ticket/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("MANAGER")
                        .anyRequest().permitAll()
                )
                .exceptionHandling(exceptionHandling -> 
                    exceptionHandling.authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(authorizationDenied)
                )
                .sessionManagement((sessionManagment) -> sessionManagment.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }

        @Bean
        public JwtRequestFilter jwtRequestFilter() {
            return new JwtRequestFilter(jwtUtil, userToSpringUserService);
        }
    }