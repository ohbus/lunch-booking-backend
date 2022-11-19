package xyz.subho.lunchbooking.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.KeyStore;
import java.security.interfaces.RSAPublicKey;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtConfig.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class JwtConfigTest {
    @Autowired
    private JwtConfig jwtConfig;

    /**
     * Method under test: {@link JwtConfig#keyStore()}
     */
    @Test
    void testKeyStore() {
        // TODO: Complete this test.

        jwtConfig.keyStore();
    }

    /**
     * Method under test: {@link JwtConfig#jwtSigningKey(KeyStore)}
     */
    @Test
    void testJwtSigningKey() {
        // TODO: Complete this test.

        jwtConfig.jwtSigningKey(null);
    }

    /**
     * Method under test: {@link JwtConfig#jwtValidationKey(KeyStore)}
     */
    @Test
    void testJwtValidationKey() {
        // TODO: Complete this test.

        jwtConfig.jwtValidationKey(null);
    }

    /**
     * Method under test: {@link JwtConfig#jwtDecoder(RSAPublicKey)}
     */
    @Test
    void testJwtDecoder() {
        assertTrue(jwtConfig.jwtDecoder(null) instanceof NimbusJwtDecoder);
    }
}

