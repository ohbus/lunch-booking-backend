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

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@ContextConfiguration(
    classes = {
      WebSecurityConfig.class,
      JwtRequestFilter.class,
      JwtHelper.class,
      JwtAuthenticationEntryPoint.class
    })
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class WebSecurityConfigTest {
  @Autowired private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired private JwtRequestFilter jwtRequestFilter;

  @MockBean private RSAPrivateKey rSAPrivateKey;

  @MockBean private RSAPublicKey rSAPublicKey;

  @Autowired private WebSecurityConfig webSecurityConfig;

  /**
   * Method under test: {@link WebSecurityConfig#securityFilterChain(HttpSecurity, JwtRequestFilter,
   * JwtAuthenticationEntryPoint)}
   */
  @Test
  void testSecurityFilterChain() throws Exception {
    assertTrue(
        webSecurityConfig.securityFilterChain(null, jwtRequestFilter, jwtAuthenticationEntryPoint)
            instanceof DefaultSecurityFilterChain);
  }

  /** Method under test: {@link WebSecurityConfig#corsConfigurationSource()} */
  @Test
  void testCorsConfigurationSource() {
    assertTrue(
        webSecurityConfig.corsConfigurationSource() instanceof UrlBasedCorsConfigurationSource);
  }
}
