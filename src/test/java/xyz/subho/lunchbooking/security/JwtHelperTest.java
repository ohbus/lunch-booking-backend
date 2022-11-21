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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.JWTVerificationException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.security.UserLogin;

@ContextConfiguration(classes = {JwtHelper.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class JwtHelperTest {
  @Autowired private JwtHelper jwtHelper;

  @MockBean private RSAPrivateKey rSAPrivateKey;

  @MockBean private RSAPublicKey rSAPublicKey;

  /** Method under test: {@link JwtHelper#generateJwtToken(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGenerateJwtToken() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.auth0.jwt.exceptions.SignatureGenerationException: The Token's Signature couldn't be
    // generated when signing using the Algorithm: SHA256withRSA
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:69)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.generateJwtToken(JwtHelper.java:61)
    //   java.security.InvalidKeyException: java.security.ProviderException: Unsupported algorithm
    // Algorithm
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:132)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.generateJwtToken(JwtHelper.java:61)
    //   java.security.ProviderException: Unsupported algorithm Algorithm
    //       at sun.security.rsa.RSAUtil$KeyType.lookup(RSAUtil.java:69)
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.generateJwtToken(JwtHelper.java:61)
    //   See https://diff.blue/R013 to resolve this issue.

    when(rSAPrivateKey.getAlgorithm()).thenReturn("Algorithm");
    jwtHelper.generateJwtToken("Hello from the Dreaming Spires");
  }

  /** Method under test: {@link JwtHelper#generateJwtToken(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGenerateJwtToken2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   com.auth0.jwt.exceptions.JWTVerificationException: An error occurred
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.generateJwtToken(JwtHelper.java:61)
    //   See https://diff.blue/R013 to resolve this issue.

    when(rSAPrivateKey.getAlgorithm()).thenThrow(new JWTVerificationException("An error occurred"));
    jwtHelper.generateJwtToken("Hello from the Dreaming Spires");
  }

  /** Method under test: {@link JwtHelper#createJwt(UserLogin)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateJwt() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   com.auth0.jwt.exceptions.SignatureGenerationException: The Token's Signature couldn't be
    // generated when signing using the Algorithm: SHA256withRSA
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:69)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwt(JwtHelper.java:70)
    //   java.security.InvalidKeyException: java.security.ProviderException: Unsupported algorithm
    // Algorithm
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:132)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwt(JwtHelper.java:70)
    //   java.security.ProviderException: Unsupported algorithm Algorithm
    //       at sun.security.rsa.RSAUtil$KeyType.lookup(RSAUtil.java:69)
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwt(JwtHelper.java:70)

    when(rSAPrivateKey.getAlgorithm()).thenReturn("Algorithm");

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    jwtHelper.createJwt(userLogin);
  }

  /** Method under test: {@link JwtHelper#createJwt(UserLogin)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateJwt2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   com.auth0.jwt.exceptions.JWTVerificationException: An error occurred
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwt(JwtHelper.java:70)

    when(rSAPrivateKey.getAlgorithm()).thenThrow(new JWTVerificationException("An error occurred"));

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    jwtHelper.createJwt(userLogin);
  }

  /** Method under test: {@link JwtHelper#createJwtForClaims(String, Map)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateJwtForClaims() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   com.auth0.jwt.exceptions.SignatureGenerationException: The Token's Signature couldn't be
    // generated when signing using the Algorithm: SHA256withRSA
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:69)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //   java.security.InvalidKeyException: java.security.ProviderException: Unsupported algorithm
    // Algorithm
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:132)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //   java.security.ProviderException: Unsupported algorithm Algorithm
    //       at sun.security.rsa.RSAUtil$KeyType.lookup(RSAUtil.java:69)
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)

    when(rSAPrivateKey.getAlgorithm()).thenReturn("Algorithm");
    jwtHelper.createJwtForClaims("Hello from the Dreaming Spires", new HashMap<>());
  }

  /** Method under test: {@link JwtHelper#createJwtForClaims(String, Map)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateJwtForClaims2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   com.auth0.jwt.exceptions.SignatureGenerationException: The Token's Signature couldn't be
    // generated when signing using the Algorithm: SHA256withRSA
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:69)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //   java.security.InvalidKeyException: java.security.ProviderException: Unsupported algorithm
    // Algorithm
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:132)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //   java.security.ProviderException: Unsupported algorithm Algorithm
    //       at sun.security.rsa.RSAUtil$KeyType.lookup(RSAUtil.java:69)
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)

    when(rSAPrivateKey.getAlgorithm()).thenReturn("Algorithm");

    HashMap<String, String> stringStringMap = new HashMap<>();
    stringStringMap.put(JwtHelper.JWT_ISSUER, "42");
    jwtHelper.createJwtForClaims("Hello from the Dreaming Spires", stringStringMap);
  }

  /** Method under test: {@link JwtHelper#createJwtForClaims(String, Map)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateJwtForClaims3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   com.auth0.jwt.exceptions.JWTVerificationException: An error occurred
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)

    when(rSAPrivateKey.getAlgorithm()).thenThrow(new JWTVerificationException("An error occurred"));
    jwtHelper.createJwtForClaims("Hello from the Dreaming Spires", new HashMap<>());
  }

  /** Method under test: {@link JwtHelper#createJwtForClaims(String, Map)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateJwtForClaims4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   com.auth0.jwt.exceptions.SignatureGenerationException: The Token's Signature couldn't be
    // generated when signing using the Algorithm: SHA256withRSA
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:69)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //   java.security.InvalidKeyException: java.security.ProviderException: Unsupported algorithm
    // Algorithm
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:132)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)
    //   java.security.ProviderException: Unsupported algorithm Algorithm
    //       at sun.security.rsa.RSAUtil$KeyType.lookup(RSAUtil.java:69)
    //       at sun.security.rsa.RSAKeyFactory.toRSAKey(RSAKeyFactory.java:128)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:112)
    //       at sun.security.rsa.RSASignature.engineInitSign(RSASignature.java:104)
    //       at java.security.Signature$Delegate.tryOperation(Signature.java:1327)
    //       at java.security.Signature$Delegate.chooseProvider(Signature.java:1276)
    //       at java.security.Signature$Delegate.engineInitSign(Signature.java:1373)
    //       at java.security.Signature.initSign(Signature.java:635)
    //       at com.auth0.jwt.algorithms.CryptoHelper.createSignatureFor(CryptoHelper.java:135)
    //       at com.auth0.jwt.algorithms.RSAAlgorithm.sign(RSAAlgorithm.java:67)
    //       at com.auth0.jwt.JWTCreator.sign(JWTCreator.java:574)
    //       at com.auth0.jwt.JWTCreator.access$100(JWTCreator.java:24)
    //       at com.auth0.jwt.JWTCreator$Builder.sign(JWTCreator.java:554)
    //       at xyz.subho.lunchbooking.security.JwtHelper.createJwtForClaims(JwtHelper.java:85)

    when(rSAPrivateKey.getAlgorithm()).thenReturn("Algorithm");

    HashMap<String, String> stringStringMap = new HashMap<>();
    stringStringMap.put("Key", "42");
    stringStringMap.putIfAbsent(JwtHelper.JWT_ISSUER, "42");
    jwtHelper.createJwtForClaims("Hello from the Dreaming Spires", stringStringMap);
  }

  /** Method under test: {@link JwtHelper#validateToken(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testValidateToken() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.auth0.jwt.interfaces.DecodedJWT.getExpiresAt()" because the return value of
    // "xyz.subho.lunchbooking.security.JwtHelper.verifyAndDecodeJwt(String)" is null
    //       at xyz.subho.lunchbooking.security.JwtHelper.extractExpiration(JwtHelper.java:128)
    //       at xyz.subho.lunchbooking.security.JwtHelper.isTokenExpired(JwtHelper.java:124)
    //       at xyz.subho.lunchbooking.security.JwtHelper.validateToken(JwtHelper.java:89)

    jwtHelper.validateToken("ABC123");
  }

  /** Method under test: {@link JwtHelper#verifyAndDecodeJwt(String)} */
  @Test
  void testVerifyAndDecodeJwt() {
    assertNull(jwtHelper.verifyAndDecodeJwt("ABC123"));
  }

  /** Method under test: {@link JwtHelper#getAuthenticatedUserDetails(String, String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetAuthenticatedUserDetails() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.auth0.jwt.interfaces.DecodedJWT.getClaims()" because the return value of
    // "xyz.subho.lunchbooking.security.JwtHelper.verifyAndDecodeJwt(String)" is null
    //       at xyz.subho.lunchbooking.security.JwtHelper.extractAllClaims(JwtHelper.java:136)
    //       at
    // xyz.subho.lunchbooking.security.JwtHelper.getAuthenticatedUserDetails(JwtHelper.java:107)

    jwtHelper.getAuthenticatedUserDetails("ABC123", "janedoe");
  }

  /** Method under test: {@link JwtHelper#isTokenExpired(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testIsTokenExpired() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.auth0.jwt.interfaces.DecodedJWT.getExpiresAt()" because the return value of
    // "xyz.subho.lunchbooking.security.JwtHelper.verifyAndDecodeJwt(String)" is null
    //       at xyz.subho.lunchbooking.security.JwtHelper.extractExpiration(JwtHelper.java:128)
    //       at xyz.subho.lunchbooking.security.JwtHelper.isTokenExpired(JwtHelper.java:124)

    jwtHelper.isTokenExpired("ABC123");
  }

  /** Method under test: {@link JwtHelper#extractExpiration(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testExtractExpiration() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.auth0.jwt.interfaces.DecodedJWT.getExpiresAt()" because the return value of
    // "xyz.subho.lunchbooking.security.JwtHelper.verifyAndDecodeJwt(String)" is null
    //       at xyz.subho.lunchbooking.security.JwtHelper.extractExpiration(JwtHelper.java:128)

    jwtHelper.extractExpiration("ABC123");
  }

  /** Method under test: {@link JwtHelper#extractUsername(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testExtractUsername() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.auth0.jwt.interfaces.DecodedJWT.getSubject()" because the return value of
    // "xyz.subho.lunchbooking.security.JwtHelper.verifyAndDecodeJwt(String)" is null
    //       at xyz.subho.lunchbooking.security.JwtHelper.extractUsername(JwtHelper.java:132)

    jwtHelper.extractUsername("ABC123");
  }

  /** Method under test: {@link JwtHelper#extractAllClaims(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testExtractAllClaims() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "com.auth0.jwt.interfaces.DecodedJWT.getClaims()" because the return value of
    // "xyz.subho.lunchbooking.security.JwtHelper.verifyAndDecodeJwt(String)" is null
    //       at xyz.subho.lunchbooking.security.JwtHelper.extractAllClaims(JwtHelper.java:136)

    jwtHelper.extractAllClaims("ABC123");
  }
}
