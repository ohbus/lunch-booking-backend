package xyz.subho.lunchbooking.services;

public interface EncryptionService {

  public static final String ALGORITHM = "PBKDF2WithHmacSHA512";
  public static final int KEY_LENGTH = 256;

  public String generateSalt(int length);

  public byte[] hash(char[] password, byte[] salt);

  public String encrypt(String password, String salt);

  public boolean isPasswordValid(String providedPassword, String securedPassword, String salt);
}
