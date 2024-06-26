package it.epicode.DiVino.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    //PERSONALIZZIAMO IL CORSFILTER SECONDO LE NOSTRE ESIGENZE
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        //UNA VOLTA DEPLOYATO IL FRONTEND BISOGNA AGGIUNGERE L'ORIGIN DOVE E STATO FATTO IL DEPLOY
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedMethod("");
        configuration.addAllowedHeader("");
        configuration.setAllowCredentials(true);

        // Setta i permessi per le richieste preflight (OPTIONS)
        configuration.addExposedHeader("Authorization");
        configuration.setMaxAge(3600L); // Cache delle preflight per 1 ora

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}
