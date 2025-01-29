package com.example.Airport.encryption;

import java.util.Base64;

public class Base64Decoder {

    public static String decode(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new String(decodedBytes);
    }
}
