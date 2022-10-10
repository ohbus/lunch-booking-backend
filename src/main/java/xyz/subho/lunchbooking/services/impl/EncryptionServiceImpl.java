package xyz.subho.lunchbooking.services.impl;

import io.micrometer.core.instrument.util.StringUtils;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.exceptions.InvalidConfigurationException;
import xyz.subho.lunchbooking.services.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String ALPHABET =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final int ITERATIONS = 32768;

  @Override
  public String generateSalt(int length) {
    StringBuilder saltStr = new StringBuilder(length);
    while (length-- > 0) saltStr.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    return new String(saltStr);
  }

  @Override
  public byte[] hash(char[] password, byte[] salt) {
    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
    Arrays.fill(password, Character.MIN_VALUE);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
    } finally {
      spec.clearPassword();
    }
  }

  @Override
  public String encrypt(String password, String salt) {
    if (StringUtils.isBlank(salt)) {
      throw new InvalidConfigurationException(
          "Invalid salt: Wrong configuration. Salt cannot be empty or null");
    }
    byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
    return Base64.getEncoder().encodeToString(securePassword);
  }

  @Override
  public boolean isPasswordValid(String providedPassword, String securedPassword, String salt) {
    // Generate new secure password with the same salt
    String newSecurePassword = encrypt(providedPassword, salt);
    // Check if the passwords are equal
    return newSecurePassword.equalsIgnoreCase(securedPassword);
  }
}
