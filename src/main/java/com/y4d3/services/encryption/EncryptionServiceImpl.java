package com.y4d3.services.encryption;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by semo on 23.12.16.
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor encryptor;

    @Autowired
    public void setEncryptor(StrongPasswordEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String encryptString(String input) {
        return encryptor.encryptPassword(input);
    }

    @Override
    public boolean validatePassword(String input, String encryptedPassword) {
        return encryptor.checkPassword(input, encryptedPassword);
    }
}