package com.example.library.management.system.Config;

import com.example.library.management.system.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .cors(cors -> cors.configurationSource(configurationSource()))
                        .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/api/auth/**","/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                                        .anyRequest().authenticated()
                        )
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .exceptionHandling(ex -> ex
                                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                        )
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
            return authentication -> {
                String username=authentication.getName();
                String password=authentication.getCredentials().toString();

                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                if(!encoder().matches(password,userDetails.getPassword())){
                    throw new BadCredentialsException("Invalid Email Or Password");
                }
                return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
            };
    }


    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("POST","PUT","GET","PATCH","DELETE","OPTIONS"));
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
