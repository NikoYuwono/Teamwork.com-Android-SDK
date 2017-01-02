package com.nikoyuwono.teamwork.service;

import java.io.IOException;

import okhttp3.Response;

public class Util {

    public static String getContent(final Response response) {
        try {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
