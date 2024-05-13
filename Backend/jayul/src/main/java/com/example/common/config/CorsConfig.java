package com.example.common.config;

import java.util.Arrays;
import org.springframework.web.cors.*;

//@Configuration
public class CorsConfig {

	//@Bean
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
