package com.ivanisevic.sbg.services.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class BackgroundFileDownloader implements Runnable {

    private String fileUrl;
    private String destination;

    @Override
    public void run() {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        int length;
        int size = 0;
        try {
            URL url = new URL(fileUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();

            log.info("Started downloading file: " + destination + " time: " + Instant.now());

            /**
             * absolute path - if starts with "/"
             */
            if (destination.startsWith("/")) {
                String home = System.getProperty("user.home");
                destination = home + destination;
            }

            File file = new File(destination);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fos = new FileOutputStream(destination);
            byte[] buffer = new byte[4096];
            while ((length = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
                size += length;
            }

            log.info("Downloaded file: " + destination + " size: " + size + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStreamsAndConnection(inputStream, fos, urlConnection);
        }
    }

    private void closeStreamsAndConnection(InputStream inputStream, FileOutputStream fos, HttpURLConnection urlConnection) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }
}
