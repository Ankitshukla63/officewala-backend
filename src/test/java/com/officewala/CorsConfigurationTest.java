package com.officewala;

import com.officewala.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigurationTest {

    @Test
    @DisplayName("Should correctly configure CORS for production Cloudflare Workers origin and methods")
    void testCorsConfigurationAllowsProductionOriginAndMethods() {
        SecurityConfig config = new SecurityConfig(null);
        ReflectionTestUtils.setField(
                config,
                "allowedOrigins",
                "http://localhost:5173,https://localhost:5173,http://localhost:3000,http://localhost:4173,https://officewala-frontend.shuklaankit45470.workers.dev,https://*.workers.dev,https://*.vercel.app,https://*.railway.app,https://*.up.railway.app,https://*.pages.dev,https://*.cloudflarepages.com"
        );

        CorsConfigurationSource source = config.corsConfigurationSource();
        MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/api/auth/signin");
        request.addHeader("Origin", "https://officewala-frontend.shuklaankit45470.workers.dev");
        request.addHeader("Access-Control-Request-Method", "POST");
        request.addHeader("Access-Control-Request-Headers", "Content-Type, Authorization");

        CorsConfiguration corsConfig = source.getCorsConfiguration(request);
        assertNotNull(corsConfig, "CorsConfiguration must not be null");

        // Verify origin check
        String resolvedOrigin = corsConfig.checkOrigin("https://officewala-frontend.shuklaankit45470.workers.dev");
        assertEquals("https://officewala-frontend.shuklaankit45470.workers.dev", resolvedOrigin);

        // Verify required HTTP methods
        assertTrue(corsConfig.getAllowedMethods().containsAll(java.util.List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")));

        // Verify headers & credentials
        assertTrue(corsConfig.getAllowCredentials());
        assertTrue(corsConfig.getAllowedHeaders().contains("*"));
        assertTrue(corsConfig.getExposedHeaders().contains("Authorization"));
    }

    @Test
    @DisplayName("Should reject unauthorized unknown origins")
    void testCorsConfigurationRejectsUnknownOrigin() {
        SecurityConfig config = new SecurityConfig(null);
        ReflectionTestUtils.setField(
                config,
                "allowedOrigins",
                "http://localhost:5173,https://localhost:5173,http://localhost:3000,http://localhost:4173,https://officewala-frontend.shuklaankit45470.workers.dev,https://*.workers.dev,https://*.vercel.app,https://*.railway.app,https://*.up.railway.app,https://*.pages.dev,https://*.cloudflarepages.com"
        );

        CorsConfigurationSource source = config.corsConfigurationSource();
        CorsConfiguration corsConfig = source.getCorsConfiguration(new MockHttpServletRequest("OPTIONS", "/api/videos"));
        assertNotNull(corsConfig);

        String resolvedOrigin = corsConfig.checkOrigin("https://malicious-website.com");
        assertNull(resolvedOrigin, "Unknown origin must be rejected");
    }
}
