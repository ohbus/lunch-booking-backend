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

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Slf4j
@Configuration
public class JwtConfig {

  @Value("${app.security.jwt.keystore-location}")
  private String keyStorePath;

  @Value("${app.security.jwt.keystore-password}")
  private String keyStorePassword;

  @Value("${app.security.jwt.key-alias}")
  private String keyAlias;

  @Value("${app.security.jwt.private-key-passphrase}")
  private String privateKeyPassphrase;

  @Bean
  public KeyStore keyStore() {
    try {
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      InputStream resourceAsStream =
          Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStorePath);
      keyStore.load(resourceAsStream, keyStorePassword.toCharArray());
      return keyStore;
    } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
      log.error("Unable to load keystore: {}", keyStorePath, e);
    }
    throw new IllegalArgumentException("Unable to load keystore");
  }

  @Bean
  public RSAPrivateKey jwtSigningKey(KeyStore keyStore) {
    try {
      Key key = keyStore.getKey(keyAlias, privateKeyPassphrase.toCharArray());
      if (key instanceof RSAPrivateKey privateKey) {
        return privateKey;
      }
    } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
      log.error("Unable to load private key from keystore: {}", keyStorePath, e);
    }

    throw new IllegalArgumentException("Unable to load private key");
  }

  @Bean
  public RSAPublicKey jwtValidationKey(KeyStore keyStore) {
    try {
      Certificate certificate = keyStore.getCertificate(keyAlias);
      PublicKey publicKey = certificate.getPublicKey();

      if (publicKey instanceof RSAPublicKey pubKey) {
        return pubKey;
      }
    } catch (KeyStoreException e) {
      log.error("Unable to load private key from keystore: {}", keyStorePath, e);
    }

    throw new IllegalArgumentException("Unable to load RSA public key");
  }

  @Bean
  public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
    return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
  }
}
