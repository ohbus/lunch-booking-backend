package xyz.subho.lunchbooking.services;

public interface EncryptionService {
	
	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int KEY_LENGTH = 512;
    public static final String DEFAULT_SALT = "WZeBXmCI9cAz3LyY9Sdllj9l5iPsXC";
    
	public String generateSalt(int length);
	
	public byte[] hash(char[] password, byte[] salt);
	
	public String encrypt(String password, String salt);
	
	public boolean isPasswordValid(String providedPassword, String securedPassword, String salt);

}
