package sv.org.arrupe.API_BackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permite solicitudes desde el origen del frontend
        config.addAllowedOrigin("http://localhost:8080"); // Ajusta según tu configuración
        
        // Permite los métodos HTTP necesarios
        config.addAllowedMethod("*");
        
        // Permite todos los headers
        config.addAllowedHeader("*");
        
        // Permite cookies en las solicitudes CORS
        config.setAllowCredentials(true);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}