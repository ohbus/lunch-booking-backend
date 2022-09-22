package xyz.subho.lunchbooking.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.UserLogin;

@Component
public class JwtHelper {

  private final RSAPrivateKey privateKey;
  private final RSAPublicKey publicKey;

  @Value("app.security.jwt.token.validity.milliseconds")
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

  public String createJwt(String subject, UserLogin user) {

    Map<String, String> claims = new HashMap<>();

    return createJwtForClaims(subject, claims);
  }

  public String createJwtForClaims(String subject, Map<String, String> claims) {

    JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

    // Add claims
    claims.forEach(jwtBuilder::withClaim);

    // Add expiredAt and etc
    return jwtBuilder
        .withIssuedAt(new Date())
        .withNotBefore(new Date())
        .withExpiresAt(makeValidity())
        .sign(Algorithm.RSA512(publicKey, privateKey));
  }

  public boolean validateToken(String token, UserLogin user) {
    return (extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token));
  }

  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public Date extractExpiration(String token) {
    return JWT.decode(token).getExpiresAt();
  }

  public String extractUsername(String token) {
    return JWT.decode(token).getSubject();
  }

  public Map<String, Claim> extractAllClaims(String token) {
    return JWT.decode(token).getClaims();
  }
}
