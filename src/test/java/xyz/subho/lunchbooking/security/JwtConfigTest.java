/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
  @Autowired private JwtConfig jwtConfig;

  /** Method under test: {@link JwtConfig#keyStore()} */
  @Test
  void testKeyStore() {
    // TODO: Complete this test.

    jwtConfig.keyStore();
  }

  /** Method under test: {@link JwtConfig#jwtSigningKey(KeyStore)} */
  @Test
  void testJwtSigningKey() {
    // TODO: Complete this test.

    jwtConfig.jwtSigningKey(null);
  }

  /** Method under test: {@link JwtConfig#jwtValidationKey(KeyStore)} */
  @Test
  void testJwtValidationKey() {
    // TODO: Complete this test.

    jwtConfig.jwtValidationKey(null);
  }

  /** Method under test: {@link JwtConfig#jwtDecoder(RSAPublicKey)} */
  @Test
  void testJwtDecoder() {
    assertTrue(jwtConfig.jwtDecoder(null) instanceof NimbusJwtDecoder);
  }
}
