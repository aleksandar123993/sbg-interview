package com.ivanisevic.sbg.api.service.impl;

import com.ivanisevic.sbg.api.service.CGCApiRemote;
import com.ivanisevic.sbg.api.service.CGCService;
import com.ivanisevic.sbg.model.FilesUpdateRequestBody;
import com.ivanisevic.sbg.responses.FilesDownloadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CGCServiceImpl implements CGCService {

    private final CGCApiRemote cgcApiRemote;

    @Override
    public void listProjects(String token, Map<String, String> queries) {
        executeCall(cgcApiRemote.listProjects(token, queries));
    }

    @Override
    public void listFiles(String token, Map<String, String> queries) {
        executeCall(cgcApiRemote.listFiles(token, queries));
    }

    @Override
    public void filesStat(String token, Map<String, String> queries, String fileId) {
        executeCall(cgcApiRemote.filesStat(token, queries, fileId));
    }

    @Override
    public void filesUpdate(String token, Map<String, String> queries, String fileId, FilesUpdateRequestBody requestBody) {
        executeCall(cgcApiRemote.filesUpdate(token, queries, fileId, requestBody));
    }

    @Override
    public FilesDownloadResponse getDownloadUrl(String token, Map<String, String> queries, String fileId) {
        Response<FilesDownloadResponse> response = executeCall(cgcApiRemote.getDownloadInfo(token, queries, fileId));
        return response != null ? response.body() : null;
    }

    private <T extends Object>  Response<T> executeCall(Call call) {
        try {
            Response response = call.execute();
            if(response.code() != HttpStatus.OK.value()) {
                System.out.println(response.errorBody().string());
            }
            else{
                System.out.println(response.body());
            }
            return response;
        }
        catch (IOException exception) {
            System.out.println("CGC service is unavailable.");
        }
        return null;
    }
}
