package com.pavlov.onlinestore.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        /*
        http
                .authorizeHttpRequests((requests) -> requests
                        //.requestMatchers("/").permitAll()
                        .requestMatchers("**", "*", "/test").permitAll()  //TODO set pages that require authorization
                        .anyRequest().authenticated()
                )

                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable);
         */
        // since requests are for local resources only, cors() should not be necessary, but it gives 403 without it
        http.cors().and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user_1 =
                User.withDefaultPasswordEncoder().username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        UserDetails user_2 =
                User.withDefaultPasswordEncoder().username("user2")
                        .password("password2")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user_1, user_2);
    }

}
