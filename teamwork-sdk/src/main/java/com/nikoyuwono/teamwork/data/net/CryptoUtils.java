package com.nikoyuwono.teamwork.data.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.util.RandomString;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by niko-yuwono on 17/01/08.
 */

class CryptoUtils {

    private static final String KEY_PREFERENCE_KEY = "KEY_PREFERENCE_KEY";
    private static final String IV_PREFERENCE_KEY = "IV_PREFERENCE_KEY";
    private static final String CREDENTIAL_PREFERENCE_KEY = "CREDENTIAL_PREFERENCE_KEY";

    private CryptoUtils() throws IllegalAccessException {
        throw new AssertionError("This class shouldn't be initialized");
    }

    public static void encryptCredential(String credential) {
        final SharedPreferences sharedPreferences = Teamwork.getSharedPreferences();
        final RandomString randomString = new RandomString();
        final String randomKey = randomString.getRandomString(128);
        final String initVector = randomString.getRandomString(16);

        final String encryptedCredential = encrypt(randomKey, initVector, credential);

        sharedPreferences.edit()
                .putString(KEY_PREFERENCE_KEY, randomKey)
                .putString(IV_PREFERENCE_KEY, initVector)
                .putString(CREDENTIAL_PREFERENCE_KEY, encryptedCredential)
                .commit();
    }

    public static String getSavedCredential() {
        final SharedPreferences sharedPreferences = Teamwork.getSharedPreferences();
        final String encryptedCredential = sharedPreferences.getString(CREDENTIAL_PREFERENCE_KEY, null);
        if (encryptedCredential == null) {
            return null;
        }

        final String key = sharedPreferences.getString(KEY_PREFERENCE_KEY, null);
        final String initVector = sharedPreferences.getString(IV_PREFERENCE_KEY, null);

        return decrypt(key, initVector, encryptedCredential);
    }

    private static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeToString(encrypted, Base64.DEFAULT));

            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
