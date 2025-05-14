package com.dentists.microservices.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(EndpointRequest.to(HealthEndpoint.class)).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:9000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//    {
//        return http
//                .cors(e -> Customizer.withDefaults())
//                .csrf(e -> Customizer.withDefaults())
//                .authorizeHttpRequests(req -> req.requestMatchers(
//                        "/api/user/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .oauth2ResourceServer
//                        (oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//                .build();}
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
//    package com.example.petservice7.config;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//    import org.springframework.security.config.http.SessionCreationPolicy;
//    import org.springframework.security.core.authority.SimpleGrantedAuthority;
//    import org.springframework.security.oauth2.jwt.JwtDecoder;
//    import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//    import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//    import org.springframework.security.web.SecurityFilterChain;
//    import java.util.Collections;
//    import java.util.List;
//    import java.util.stream.Collectors;
//    @EnableWebSecurity
//    @Configuration
//    @EnableMethodSecurity
//    public class SecurityConfig
//    {
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .authorizeHttpRequests(authz -> authz
//                            .requestMatchers("/pets/test").permitAll()
//                            .requestMatchers("/pets/**").authenticated()
//                            .anyRequest().authenticated()
//                    )                .oauth2ResourceServer(oauth2 -> oauth2
//                            .jwt(jwt -> jwt
//                                    .decoder(jwtDecoder())
//                                    .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                    )                .sessionManagement(session -> session
//                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    );        return
//                    http.build();
//        }
//        @Bean
//        public JwtDecoder jwtDecoder() {
//            String jwksUri = "https://firm-liger-84.clerk.accounts.dev/.well-known/jwks.json";
//            return NimbusJwtDecoder.withJwkSetUri(jwksUri).build();    }
//        @Bean
//        public JwtAuthenticationConverter jwtAuthenticationConverter() {
//            JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//            converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//                List<String> roles = jwt.getClaimAsStringList("roles");
//                return roles != null ?
//                        roles.stream()
//                                .map(SimpleGrantedAuthority::new)
//                                .collect(Collectors.toList()) :
//                        Collections.emptyList();        });
//            return converter;
//        }
//    }
}
