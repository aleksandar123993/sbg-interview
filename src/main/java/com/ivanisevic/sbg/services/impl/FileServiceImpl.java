package com.ivanisevic.sbg.services.impl;

import com.ivanisevic.sbg.services.FileService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileServiceImpl implements FileService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void downloadFile(String url, String destination) {
        BackgroundFileDownloader task = new BackgroundFileDownloader(url, destination);
        executorService.submit(task);
    }
}

