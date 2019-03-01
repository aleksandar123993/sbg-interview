package com.ivanisevic.sbg.responses;

import lombok.Data;

import java.util.List;

@Data
public class FilesListResponse {

    private String href;
    private List<FileItem> items;
    private List<LinkItem> links;
}
