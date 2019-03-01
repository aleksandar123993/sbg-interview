package com.ivanisevic.sbg.services;

public interface FileService {

    /**
     * Create thread in background for downloading file parallel with user inputs.
     * @param url - File's download url
     * @param destination - absolute or relative path of file requested by user.
     */
    void downloadFile(String url, String destination);
}
