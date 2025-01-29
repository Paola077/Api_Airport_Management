package com.example.Airport.encryption;

import org.springframework.security.crypto.password.PasswordEncoder;

public class BCrypt {

    PasswordEncoder encoder;

    public BCrypt(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encrypt(String data) {
        return encoder.encode(data);
    }
}
