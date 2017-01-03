package com.y4d3.services.encryption;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by semo on 23.12.16.
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor strongEncryptor;

    @Autowired
    public void setStrongEncryptor(StrongPasswordEncryptor strongEncryptor) {
        this.strongEncryptor = strongEncryptor;
    }

    @Override
    public String encryptString(String input) {
        return strongEncryptor.encryptPassword(input);
    }

    @Override
    public boolean validatePassword(String input, String encryptedPassword) {
        return strongEncryptor.checkPassword(input, encryptedPassword);
    }
}
