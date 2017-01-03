package com.y4d3.services.encryption;

/**
 * Created by semo on 23.12.16.
 */

public interface EncryptionService {

    String encryptString(String input);

    boolean validatePassword(String input, String encryptedPassword);

}
