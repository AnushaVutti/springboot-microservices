package com.microservices.apigateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerJwtAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration{
//
    @Bean
    public SecurityWebFilterChain  securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
////        return httpSecurity.cors(AbstractHttpConfigurer::disable)
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(httpRequests -> httpRequests.requestMatchers("/eureka/**").permitAll().anyRequest().authenticated())
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
////                .build();
        return httpSecurity.csrf(csrf -> csrf.disable()).authorizeExchange(exchage -> exchage
                        .pathMatchers("/eureka/**").permitAll())
                .authorizeExchange(exchange -> exchange.anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                ).build();
    }
//                 .build();
//        return httpSecurity.csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(auth->auth.requestMatchers("/eureka/**").permitAll())
//                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(withDefaults())
//                .build();


}
