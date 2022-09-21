package xyz.subho.lunchbooking.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtHelper {
	
	private final RSAPrivateKey privateKey;
	private final RSAPublicKey publicKey;
	
	@Value("jwt.token.validity.milliseconds")
	private String jwtTokenValidity;
	
	private long jwtTokenValidityInMilliseconds;
	
	public JwtHelper(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		jwtTokenValidityInMilliseconds = Long.parseLong(jwtTokenValidity);
	}
	
	public String createJwtForClaims(String subject, Map<String, String> claims) {
		
		JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
		
		// Add claims
		claims.forEach(jwtBuilder::withClaim);
		
		// Add expiredAt and etc
		return jwtBuilder
				.withIssuedAt(new Date())
				.withNotBefore(new Date())
				.withExpiresAt(new Date(
						System.currentTimeMillis() + jwtTokenValidityInMilliseconds))
				.sign(Algorithm.RSA256(publicKey, privateKey));
	}

}
