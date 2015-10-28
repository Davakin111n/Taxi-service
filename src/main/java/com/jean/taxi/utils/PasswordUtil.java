package com.jean.taxi.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class PasswordUtil {

    private static byte[] dataRegisterSet = {0x11, 0x3e, 0x33, 0x41, 0x0e, 0x0B, 0x57, 0x78};

    /**
     * Шифрование пароля
     */
    public static String encryptPassword(String password) {
        byte[] keyArray = new byte[24];
        byte[] temporarySetKey;
        byte[] encryptionArray;

        String encodeKey = "ertyuiopdfghjklcvbxcviq";
        String encryptedPasswordValue = "";
        try {
            encryptionArray = password.getBytes("UTF-8");
            MessageDigest message = MessageDigest.getInstance("MD5");
            temporarySetKey = message.digest(encodeKey.getBytes("UTF-8"));
            if (temporarySetKey.length < 24) {
                int index = 0;

                for (int i = temporarySetKey.length; i < 24; i++) {
                    keyArray[i] = temporarySetKey[index];
                }
            }
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(dataRegisterSet));
            byte[] encryptionOutput = cipher.doFinal(encryptionArray);
            encryptedPasswordValue = Base64.encodeBase64String(encryptionOutput);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encryptedPasswordValue;
    }

    /**
     * Расшифровка пароля
     */
    public static String decryptPassword(String decodePassword) {
        byte[] keyArray = new byte[24];
        byte[] temporarySetKey;

        String key = "ertyuiopdfghjklcvbxcviq";
        String decryptedPasswordValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            temporarySetKey = messageDigest.digest(key.getBytes("UTF-8"));

            if (temporarySetKey.length < 24) {
                int index = 0;
                for (int i = temporarySetKey.length; i < 24; i++) {
                    keyArray[i] = temporarySetKey[index];
                }
            }
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(dataRegisterSet));

            byte[] decrypted = cipher.doFinal(Base64.decodeBase64(decodePassword));

            decryptedPasswordValue = new String(decrypted, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return decryptedPasswordValue;
    }
}
