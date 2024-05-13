package com.example.common.config;

import com.example.member.service.TokenService;
import com.example.member.util.OauthServerTypeConverter;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    private final TokenService tokenService;

    //WebMVCConfigurer 인터페이스의 메소드 구현
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OauthServerTypeConverter());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                // OPTIONS 메소드에 대한 요청은 인증 절차 없이 허용
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 특정 경로에 대한 접근 허용
                .requestMatchers(
                    "/oauth/kakao",
                    "/oauth/redirected/kakao",
                    "/oauth/login/**",
                    "/members/reissue",
                    "/swagger-ui",
                    "/swagger-ui/**"
                ).permitAll()
                .anyRequest().authenticated()
            ).addFilterBefore(new JwtAuthenticationFilter(tokenService),
                UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(
                e -> e.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        return http.build();
    }

    //CORS 구성을 정의
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
            Arrays.asList("https://whirrlyric.n-e.kr", "http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // 자격증명 허용
        configuration.setExposedHeaders(
            Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
