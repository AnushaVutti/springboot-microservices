package com.microservices.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Value("${eureka.username}")
    private String username;
    @Value("${eureka.password}")
    private String password;
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        System.out.println("In configure of eureka");
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("eureka").password("password")
//                .authorities("USER");
//    }
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        System.out.println("In configure of eureka -> 2");
//        httpSecurity.csrf(csrf -> csrf.disable()).authorizeRequests().anyRequest()
//                .authenticated().and().httpBasic(Customizer.withDefaults());
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((authorize) -> {
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }
@Bean
public UserDetailsService userDetailsService() {

    UserDetails admin = User.builder().username("eureka").password(passwordEncoder().encode("password")).roles("ADMIN")
            .build();
    return new InMemoryUserDetailsManager(admin);
}

}
