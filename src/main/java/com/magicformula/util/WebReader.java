package com.magicformula.util;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebReader {

    private static Logger logger = Logger.getLogger(WebReader.class);

    private static final int TIMEOUT = 3000;

    public static String read(String url) {

        InputStream inputStream = null;
        String results = "";
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setReadTimeout(TIMEOUT);
            connection.connect();
            inputStream = connection.getInputStream();
            results = IOUtils.toString(inputStream, Charsets.UTF_8);
        } catch (final Exception e) {
            logger.error(url, e);
        } finally {
            safeClose(inputStream);
        }

        return results;
    }

    private static void  safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException ex) {
                logger.error("InputStream close error.", ex);
            }
        }
    }
}
