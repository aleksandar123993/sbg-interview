package com.ivanisevic.sbg.api.service;

import com.ivanisevic.sbg.model.FilesUpdateRequestBody;
import com.ivanisevic.sbg.responses.FilesDownloadResponse;
import com.ivanisevic.sbg.responses.FilesListResponse;
import com.ivanisevic.sbg.responses.FilesStatResponse;
import com.ivanisevic.sbg.responses.FilesUpdateResponse;
import com.ivanisevic.sbg.responses.ProjectsListResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface CGCApiRemote {

    @GET("projects")
    Call<ProjectsListResponse> listProjects(@Header("X-SBG-Auth-Token") String token,
                                            @QueryMap Map<String, String> queries);

    @GET("files")
    Call<FilesListResponse> listFiles(@Header("X-SBG-Auth-Token") String token,
                                      @QueryMap Map<String, String> queries);

    @GET("files/{file_id}")
    Call<FilesStatResponse> filesStat(@Header("X-SBG-Auth-Token") String token,
                                      @QueryMap Map<String, String> queries,
                                      @Path("file_id") String fileId);

    @PATCH("files/{file_id}")
    Call<FilesUpdateResponse> filesUpdate(@Header("X-SBG-Auth-Token") String token,
                                          @QueryMap Map<String, String> queries,
                                          @Path("file_id") String fileId,
                                          @Body FilesUpdateRequestBody requestBody);

    @GET("files/{file_id}/download_info")
    Call<FilesDownloadResponse> getDownloadInfo(@Header("X-SBG-Auth-Token") String token,
                                                @QueryMap Map<String, String> queries,
                                                @Path("file_id") String fileId);
}
