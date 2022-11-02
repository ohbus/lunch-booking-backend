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

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.security.Roles;
import xyz.subho.lunchbooking.entities.security.UserLogin;

@Slf4j
@Component
public class JwtHelper {

  public static final String JWT_ISSUER = "lunch-booking";

  private final RSAPrivateKey privateKey;
  private final RSAPublicKey publicKey;

  @Value("${app.security.jwt.token.validity.milliseconds}")
  private String jwtTokenValidity;

  public JwtHelper(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  private Date makeValidity() {
    return new Date(System.currentTimeMillis() + Long.parseLong(jwtTokenValidity));
  }

  public String generateJwtToken(String subject) {
    return createJwtForClaims(subject, new HashMap<>());
  }

  public String createJwt(UserLogin user) {

    Map<String, String> claims = new HashMap<>();
    claims.put("id", user.getId().toString());
    claims.put(
        "roles", user.getRoles().stream().map(Roles::getRole).collect(Collectors.joining(" ")));
    return createJwtForClaims(user.getUsername(), claims);
  }

  public String createJwtForClaims(String subject, Map<String, String> claims) {

    JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

    // Add claims
    claims.forEach(jwtBuilder::withClaim);

    // Add expiredAt and etc
    return jwtBuilder
        .withIssuedAt(new Date())
        .withIssuer(JWT_ISSUER)
        .withExpiresAt(makeValidity())
        .sign(Algorithm.RSA256(publicKey, privateKey));
  }

  public boolean validateToken(String token) {
    return !isTokenExpired(token);
  }

  public DecodedJWT verifyAndDecodeJwt(String token) {
    DecodedJWT decodedJWT;
    try {
      JWTVerifier verifier =
          JWT.require(Algorithm.RSA256(publicKey, privateKey)).withIssuer(JWT_ISSUER).build();
      decodedJWT = verifier.verify(token);
      return decodedJWT;
    } catch (JWTVerificationException exception) {
      log.error("JWT Verification Exception");
    }
    return null;
  }

  public LunchBookingPrincipal getAuthenticatedUserDetails(String token, String username) {

    var claims = extractAllClaims(token);

    var userId = Long.parseLong(claims.get("id").asString());

    var authorities =
        Arrays.stream(claims.get("roles").asString().split(" "))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());

    return LunchBookingPrincipal.builder()
        .id(userId)
        .username(username)
        .authorities(authorities)
        .build();
  }

  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public Date extractExpiration(String token) {
    return verifyAndDecodeJwt(token).getExpiresAt();
  }

  public String extractUsername(String token) {
    return verifyAndDecodeJwt(token).getSubject();
  }

  public Map<String, Claim> extractAllClaims(String token) {
    return verifyAndDecodeJwt(token).getClaims();
  }
}
