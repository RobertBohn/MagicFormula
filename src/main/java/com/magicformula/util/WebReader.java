package com.magicformula.util;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

public class WebReader {

    private static final int TIMEOUT = 6000;
    private static final int RETRIES = 3;

    public static String read(String url) {
        InputStream inputStream = null;
        String results = "";

        try {
            for (int attempts = 1; attempts <= RETRIES; attempts++) {
                inputStream = readAttempt(url);
                if (inputStream != null) {
                    results = IOUtils.toString(inputStream, Charsets.UTF_8);
                    break;
                } else {
                    System.out.println("retry #" + attempts);
                }
            }
            return results;
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            safeClose(inputStream);
        }

        return results;
    }

    public static InputStream readAttempt(String url) throws Exception {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setReadTimeout(TIMEOUT);
            connection.connect();
            return connection.getInputStream();
        } catch (SocketTimeoutException ex) {
            return null;
        }
    }

    private static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
